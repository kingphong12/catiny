package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import com.regitiny.catiny.service.dto.ImageDTO;
import com.regitiny.catiny.tools.utils.StringPool;
import com.regitiny.catiny.tools.utils.StringUtils;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
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
public class ImageAdvanceServiceImpl extends AdvanceService<Image, ImageService, ImageQueryService, ImageAdvanceMapper, ImageAdvanceRepository, ImageAdvanceSearch> implements ImageAdvanceService
{
  private final ImageAdvanceRepository imageAdvanceRepository;

  private final ImageAdvanceSearch imageAdvanceSearch;

  private final ImageAdvanceMapper imageAdvanceMapper;

  private final FileInfoAdvanceRepository fileInfoAdvanceRepository;

  //todo:
  @Value("${catiny.server.ffmpeg.data.image.folder-original}")
  private String folderImageOriginal;

  @Override
  public Option<ImageDTO> upload(MultipartFile imageData, String desiredName)
  {
    var uuid = UUID.randomUUID();
    var nameFile = Option.of(desiredName)
      .map(StringUtils::clean)
      .orElse(Option.of(uuid.toString()))
      .map(s ->
      {
        var originalFileNameExtension = Option.of(imageData.getOriginalFilename())
          .map(s1 -> s1.split(StringPool.DOT_IN_REGEX))
          .map(strings -> List.of(strings)
            .findLast(s1 -> s1.length() < 5)
            .map(s1 -> StringPool.PERIOD + s1).get())
          .getOrElse(Objects.requireNonNull(imageData.getContentType())
            .toLowerCase()
            .replace("image/", "."));
        return s + "-number-" + new Random().nextLong() + originalFileNameExtension;
      }).get();
    var pathFile = folderImageOriginal + StringPool.SLASH + nameFile;
    var fileInfo = fileInfoAdvanceRepository.save(new FileInfo()
      .nameFile(nameFile)
      .uuid(uuid)
      .dataSize(imageData.getSize())
      .typeFile(imageData.getContentType())
      .path(pathFile));
    try (var fileOutputStream = new FileOutputStream(pathFile))
    {
      fileOutputStream.write(imageData.getBytes());
    }
    catch (IOException e)
    {
      log.warn("err when write file to disk:", e);
    }
    var imageSaved = imageAdvanceRepository.save(new Image()
      .fileInfo(fileInfo)
      .dataSize(imageData.getSize())
      .quality(1F)
      .name(nameFile));
    return imageAdvanceMapper.e2o_d(imageSaved);
  }

  @Override
  public Tuple2<ImageDTO, Byte[]> fetchImage(String uuidOrName)
  {
    return null;
  }
}
