package com.regitiny.catiny.advance.controller.rest.impl;

import com.regitiny.catiny.advance.controller.rest.VideoManagement;
import com.regitiny.catiny.advance.service.VideoAdvanceService;
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
import java.util.UUID;

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
          .put("videoName", videoDTO.getName())
          .put("linkByUuid", VideoManagement.BASE_PATH + VideoManagement.PATH_UUID.replace("{uuid}", videoDTO.getUuid().toString()))
          .put("link", VideoManagement.BASE_PATH + VideoManagement.PATH_NAME.replace("{name}", videoDTO.getName()))
          .put("fullLinkByUuid", serverLink +
            VideoManagement.BASE_PATH +
            VideoManagement.PATH_UUID
              .replace("{uuid}", videoDTO.getUuid().toString()))
          .put("fullLink", serverLink +
            VideoManagement.BASE_PATH +
            VideoManagement.PATH_NAME
              .replace("{name}", videoDTO.getName()))
          .put("option", "")
          .put("video", new JSONObject(videoDTO)));
      })).count();
    result.put("videoSaved", videoSavedJSONArray)
      .put("totalSaved", totalSaved)
      .put("totalReceived", videoData.size());

    return ResponseEntity.ok(result.toString());
  }

  @Override
  public ResponseEntity<ResourceRegion> fetchVideoByUuid(UUID uuid, Boolean download, String option, String range)
  {
    if (Boolean.FALSE.equals(download))
      return videoAdvanceService.getResourceRegion(uuid.toString(), range)
        .apply((resourceRegion, fileType) ->
          ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaType.parseMediaType(fileType)).body(resourceRegion));
    else
      return videoAdvanceService.getResourceRegion(uuid.toString(), null)
        .apply((resourceRegion, fileType) -> ResponseEntity.status(HttpStatus.PARTIAL_CONTENT).contentType(MediaType.parseMediaType(fileType)).body(resourceRegion));
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
