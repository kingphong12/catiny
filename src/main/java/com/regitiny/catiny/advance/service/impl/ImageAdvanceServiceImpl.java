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
import io.vavr.collection.List;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
  @Value("${catiny.server.ima}")
  private String folderImage;

  @Override
  public Option<ImageDTO> upload(MultipartFile imageData, String desiredName)
  {

    var uuid = UUID.randomUUID();
    var nameFile = Option
      .of(desiredName)
      .orElse(Option.of(uuid.toString()))
      .map(s ->
      {
        s = s + "-number-" + new Random().nextLong();
        var originalFileNameExtension = Option.of(imageData.getOriginalFilename().split(StringPool.DOT_IN_REGEX))
          .map(strings -> List.of(strings)
            .findLast(s1 -> s1.length() < 5)
            .map(s1 -> s1 + StringPool.PERIOD).get())
          .getOrElse(Objects.requireNonNull(imageData.getContentType())
            .toLowerCase()
            .replace("image/", "."));
        return s + originalFileNameExtension;
      })
      .get();
    var pathFile = folderImage + StringPool.SLASH + uuid;
    var fileInfo = fileInfoAdvanceRepository.save(new FileInfo()
      .nameFile(nameFile)
      .uuid(uuid)
      .dataSize(imageData.getSize())
      .typeFile(imageData.getContentType())
      .path(pathFile));
    imageAdvanceRepository.save(new Image().fileInfo(fileInfo).)
    return null;
  }
}
