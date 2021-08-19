package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;
import com.regitiny.catiny.service.dto.ImageDTO;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
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

  @Autowired
  private FileInfoAdvanceServiceImpl fileInfoAdvanceService;

  @Value("${catiny.server.ffmpeg.data.image.folder-original}")
  private String folderImageOriginal;

  @Override
  public Option<ImageDTO> upload(MultipartFile imageData, String desiredName)
  {
    var image = Try.of(() -> ImageIO.read(imageData.getInputStream()))
      .onFailure(throwable -> log.warn("error when read image.", throwable))
      .map(bufferedImage -> new Image()
        .height(bufferedImage.getHeight())
        .width(bufferedImage.getWidth())
        .pixelSize(bufferedImage.getWidth() * bufferedImage.getHeight()))
      .getOrElse(() ->
        {
          if (Objects.requireNonNull(imageData.getContentType()).contains("svg"))
            return new Image();
          log.warn("this isn't a image. \n" +
            "-> 1: rất có thể đây không phải file ảnh \n" +
            "-> 2: hay là file không tồn tại nhỉ\n" +
            "-> 3: định dạng file này không đọc đc");
          return null;
        }
      );
    if (Objects.isNull(image))
      return Option.none();

    var fileInfo = fileInfoAdvanceService.createAndSaveToDisk(desiredName, imageData, folderImageOriginal);
    if (Objects.isNull(fileInfo))
      return Option.none();
    var imageSaved = imageAdvanceRepository.save(image.uuid(fileInfo.getUuid())
      .fileInfo(fileInfo)
      .dataSize(imageData.getSize())
      .quality(1F)
      .name(fileInfo.getNameFile()));
    return imageAdvanceMapper.e2o_d(imageSaved);
  }

  @Override
  public Tuple2<ImageDTO, byte[]> fetchImage(String uuidOrName)
  {
    if (Objects.isNull(uuidOrName))
      return Tuple.of(null, null);
    var imageInfoOption = Try.of(() -> UUID.fromString(uuidOrName)).map(imageAdvanceRepository::findOneByUuid).getOrElse(imageAdvanceRepository.findOneByNameAndOriginalIsNull(uuidOrName));
    return imageInfoOption.map(Image::getFileInfo)
      .map(fileInfo ->
      {
        try (var fileInputStream = new FileInputStream(fileInfo.getPath()))
        {
          var imageDTO = imageAdvanceMapper.e2d(imageInfoOption.get());
          imageDTO.setFileInfo(fileInfoAdvanceService.publicLocal().advanceMapper.e2d(fileInfo));
          return Tuple.of(imageDTO, fileInputStream.readAllBytes());
        }
        catch (IOException e)
        {
          log.warn("err when read file in disk:", e);
          return null;
        }
      }).get();
  }
}
