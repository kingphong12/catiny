package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.ImageManagement;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ImageManagementImpl implements ImageManagement
{
  private final ImageAdvanceService imageAdvanceService;

  @Override
  public ResponseEntity<Object> uploadImages(List<MultipartFile> imageDatas, String desiredName)
  {
    log.debug("REST request to save Image : {}", imageDatas.size());
    for (int i = 0; i < imageDatas.size(); i++)
    {
      var multipartFile = imageDatas.get(i);
      log.info(multipartFile.getSize());
      imageAdvanceService.upload(multipartFile, desiredName);
    }
    var a = imageDatas.stream().map(multipartFile ->
    {
      log.info(multipartFile.getSize());
      return imageAdvanceService.upload(multipartFile, desiredName);
    });
    return null;
  }
}
