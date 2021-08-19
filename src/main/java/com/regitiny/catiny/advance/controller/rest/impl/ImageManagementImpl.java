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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class ImageManagementImpl implements ImageManagement
{
  private final ImageAdvanceService imageAdvanceService;

  @Override
  public ResponseEntity<Object> uploadImages(HttpServletRequest httpServletRequest, List<MultipartFile> imageData, String desiredName)
  {
    //http://localhost:80
    var serverLink = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
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
          .put("linkByUuid", ImageManagement.BASE_PATH + ImageManagement.PATH_UUID.replace("{uuid}", imageDTO.getUuid().toString()))
          .put("link", ImageManagement.BASE_PATH + ImageManagement.PATH_NAME.replace("{name}", imageDTO.getName()))
          .put("fullLinkByUuid", serverLink +
            ImageManagement.BASE_PATH +
            ImageManagement.PATH_UUID
              .replace("{uuid}", imageDTO.getUuid().toString()))
          .put("fullLink", serverLink +
            ImageManagement.BASE_PATH +
            ImageManagement.PATH_NAME
              .replace("{name}", imageDTO.getName()))
          .put("option", "")
          .put("image", new JSONObject(imageDTO)));
      })).count();
    result.put("imagesSaved", imagesSavedJSONArray)
      .put("totalSaved", totalSaved)
      .put("totalReceived", imageData.size());

    return ResponseEntity.ok(result.toString());
  }

//  @Override
//  public ResponseEntity<Object> downloadImages(UUID uuid, String imageName, String option)
//  {
//    log.debug("request get image whit uuid is :{} and option is : {}", uuid, option);
//    var result = imageAdvanceService.fetchImage(uuid.toString());
//    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, result._1.getFileInfo().getTypeFile()).body(result._2());
//  }

  @Override
  public ResponseEntity<byte[]> fetchImageByUuid(UUID uuid, String option)
  {
    log.debug("request get image whit uuid is :{} and option is : {}", uuid, option);
    var result = imageAdvanceService.fetchImage(uuid.toString());
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, result._1.getFileInfo().getTypeFile()).body(result._2());
  }

  @Override
  public ResponseEntity<byte[]> fetchImage(String name, String option)
  {
    log.debug("request get image whit name is :{} and option is : {}", name, option);
    var result = imageAdvanceService.fetchImage(name);
    return ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, result._1.getFileInfo().getTypeFile()).body(result._2());
  }
}
