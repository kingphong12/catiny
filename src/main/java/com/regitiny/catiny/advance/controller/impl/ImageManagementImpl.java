package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.ImageManagement;
import com.regitiny.catiny.advance.service.ImageAdvanceService;
import com.regitiny.catiny.common.utils.StringPool;
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
import java.util.Objects;

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
          .put("link", BASE_PATH + StringPool.SLASH + imageDTO.getName())
          .put("backupLink", BASE_PATH + StringPool.SLASH + imageDTO.getUuid().toString())
          .put("url", serverLink + BASE_PATH + StringPool.SLASH + imageDTO.getName())
          .put("backupUrl", serverLink + BASE_PATH + StringPool.SLASH + imageDTO.getUuid().toString())
//          .put("details", new JSONObject(imageDTO))
          .put("option", ""));
      })).count();
    result.put("imagesSaved", imagesSavedJSONArray)
      .put("totalSaved", totalSaved)
      .put("totalReceived", imageData.size());
    return ResponseEntity.ok(result.toString());
  }

  @Override
  public ResponseEntity<byte[]> fetchImage(String uuidOrName, String option, Boolean byUuid)
  {
    log.debug("request get image whit uuid  or name :{} and option is : {}", uuidOrName, option);
    var result = imageAdvanceService.fetchImage(uuidOrName);
    if (Objects.nonNull(result))
      return result.apply((imageDTO, bytes) -> ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, imageDTO.getFileInfo().getTypeFile()).body(bytes));
    return ResponseEntity.notFound().build();
  }
}
