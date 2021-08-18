package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.ImageManagement;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ImageManagementImpl implements ImageManagement
{
  private final ImageAdvanceService imageAdvanceService;

  @Override
  public ResponseEntity<Object> uploadImages(List<MultipartFile> imageData, String desiredName)
  {
    log.debug("REST request to save Image : {}", imageData.size());
    var result = new JSONObject();
    var imagesSavedJSONArray = new JSONArray();
    var totalSaved = imageData.stream().map(multipartFile ->
      {
        log.info(multipartFile.getSize());
        return imageAdvanceService.upload(multipartFile, desiredName);
      })
      .filter(imageDTOOption -> imageDTOOption.isDefined() || !imageDTOOption.isEmpty())
      .peek(imageDTOOption -> imageDTOOption.forEach(imageDTO ->
      {
        imagesSavedJSONArray.put(new JSONObject()
          .put("imageName", imageDTO.getName())
          .put("link", ImageManagement.BASE_PATH + ImageManagement.IMAGE_PATH_UUID.replace("{uuid}", imageDTO.getUuid().toString()))
          .put("linkByName", ImageManagement.BASE_PATH + ImageManagement.IMAGE_PATH_NAME.replace("{name}", imageDTO.getName()))
          .put("staticLink", "https://catiny.com" + ImageManagement.BASE_PATH + ImageManagement.IMAGE_PATH_UUID.replace("{uuid}", imageDTO.getName()))
          .put("staticLinkByName", "https://catiny.com" + ImageManagement.BASE_PATH + ImageManagement.IMAGE_PATH_NAME.replace("{name}", imageDTO.getName()))
          .put("option", "")
          .put("image", new JSONObject(imageDTO)));
      })).count();
    result.put("imagesSaved", imagesSavedJSONArray);
    result.put("total", totalSaved);
    return ResponseEntity.ok(result.toString());
  }

  @Override
  public ResponseEntity<byte[]> getImage(UUID uuid, String option)
  {
    log.debug("request get image whit uuid is :{} and option is : {}", uuid, option);
    var result = imageAdvanceService.fetchImage(uuid.toString());
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, result._1.getFileInfo().getTypeFile()).body(result._2());
  }

  @Override
  public ResponseEntity<byte[]> getImageByName(String name, String option)
  {
    log.debug("request get image whit name is :{} and option is : {}", name, option);
    var result = imageAdvanceService.fetchImage(name);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, result._1.getFileInfo().getTypeFile()).body(result._2());
  }
}
