package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.ImageManagement;
import com.regitiny.catiny.advance.controller.rest.VideoManagement;
import com.regitiny.catiny.advance.service.VideoAdvanceService;
import com.regitiny.catiny.common.utils.StringPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VideoManagementImpl implements VideoManagement
{
  private final VideoAdvanceService videoAdvanceService;

  @Override
  public ResponseEntity<Object> uploadVideos(HttpServletRequest httpServletRequest, List<MultipartFile> videoData, String desiredName)
  {
    // example -> http://localhost:80
    var serverLink = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
    log.debug("REST request to save video : {}", videoData.size());
    var result = new JSONObject();
    var videoSavedJSONArray = new JSONArray();
    var totalSaved = videoData.stream().map(multipartFile ->
      {
        log.info(multipartFile.getSize());
        return videoAdvanceService.upload(multipartFile, desiredName);
      })
      .filter(videoDTOOption -> videoDTOOption.isDefined() || !videoDTOOption.isEmpty())
      .peek(videoDTOOption -> videoDTOOption.forEach(videoDTO ->
      {
        videoSavedJSONArray.put(new JSONObject()
          .put("name", videoDTO.getName())
          .put("link", ImageManagement.BASE_PATH + StringPool.SLASH + videoDTO.getName())
          .put("backupLink", ImageManagement.BASE_PATH + StringPool.SLASH + videoDTO.getUuid().toString())
          .put("url", serverLink + ImageManagement.BASE_PATH + StringPool.SLASH + videoDTO.getName())
          .put("backupUrl", serverLink + ImageManagement.BASE_PATH + StringPool.SLASH + videoDTO.getUuid().toString())
//          .put("details", new JSONObject(videoDTO))
          .put("option", ""));
      })).count();
    result.put("videoSaved", videoSavedJSONArray)
      .put("totalSaved", totalSaved)
      .put("totalReceived", videoData.size());

    return ResponseEntity.ok(result.toString());
  }

  @Override
  public ResponseEntity<ResourceRegion> fetchVideo(String name, Boolean download, String option, String range)
  {
    if (Boolean.FALSE.equals(download))
      return videoAdvanceService.getResourceRegion(name, range)
        .apply((resourceRegion, fileType) ->
          ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaType.parseMediaType(fileType)).body(resourceRegion));
    else
      return videoAdvanceService.getResourceRegion(name, null)
        .apply((resourceRegion, fileType) -> ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_OCTET_STREAM).body(resourceRegion));
  }
}
