package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FileInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.FileInfoAdvanceService;
import com.regitiny.catiny.advance.service.mapper.FileInfoAdvanceMapper;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.common.utils.StringUtils;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.service.FileInfoQueryService;
import com.regitiny.catiny.service.FileInfoService;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class FileInfoAdvanceServiceImpl extends AdvanceService<FileInfo, FileInfoService, FileInfoQueryService, FileInfoAdvanceMapper, FileInfoAdvanceRepository, FileInfoAdvanceSearch> implements FileInfoAdvanceService
{
  private final FileInfoAdvanceRepository fileInfoAdvanceRepository;

  private final FileInfoAdvanceSearch fileInfoAdvanceSearch;

  private final FileInfoAdvanceMapper fileInfoAdvanceMapper;

  public FileInfo createAndSaveToDisk(String desiredName, MultipartFile data, String folder)
  {
    var uuid = UUID.randomUUID();
    var nameFile = Option.of(desiredName)
      .map(StringUtils::clean)
      .orElse(Option.of(uuid.toString()))
      .map(s ->
      {
        var originalFileNameExtension = Option.of(data.getOriginalFilename())
          .map(s1 -> s1.split(StringPool.DOT_IN_REGEX))
          .map(strings -> List.of(strings)
            .findLast(s1 -> s1.length() < 5)
            .map(s1 -> StringPool.PERIOD + s1).getOrNull())
          .getOrElse(Objects.requireNonNull(data.getContentType())
            .toLowerCase()
            .replaceAll("[a-z0-9]+/", "."));
        return s + "-number-" + new Random().nextLong() + originalFileNameExtension;
      }).get();
    var pathFile = folder + StringPool.SLASH + nameFile;
    var fileInfoSaved = fileInfoAdvanceRepository.save(new FileInfo()
      .uuid(uuid)
      .nameFile(nameFile)
      .dataSize(data.getSize())
      .typeFile(data.getContentType())
      .path(pathFile));
    try (var fileOutputStream = new FileOutputStream(pathFile))
    {
      fileOutputStream.write(data.getBytes());
      return fileInfoSaved;
    }
    catch (IOException e)
    {
      log.warn("err when write file to disk:", e);
      return null;
    }
  }
}
