package com.regitiny.catiny.advance.controller.impl;

import com.regitiny.catiny.advance.controller.rest.VideoManagement;
import com.regitiny.catiny.advance.controller.ws.VideoWsManagement;
import com.regitiny.catiny.advance.service.VideoAdvanceService;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.regitiny.catiny.util.WebSocketUtils.topicConsumer;

@Log4j2
@RestController
@RequiredArgsConstructor
public class VideoManagementImpl implements VideoManagement, VideoWsManagement
{
  private final VideoAdvanceService videoAdvanceService;
  private final SimpMessageSendingOperations messagingTemplate;

  @Override
  public ResponseEntity<Object> uploadVideos(HttpServletRequest httpServletRequest, List<MultipartFile> videoData, String desiredName)
  {
    // example -> http://localhost:80
    var serverLink = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort();
    log.debug("REST request to save video : {}", videoData.size());
    var result = new JSONObject();
    var videoSavedJSONArray = new JSONArray();
    videoData.stream().map(multipartFile ->
      {
        log.info(multipartFile.getSize());
        return videoAdvanceService.upload(multipartFile, desiredName);
      })
      .filter(videoDTOOption -> videoDTOOption.isDefined() || !videoDTOOption.isEmpty())
      .forEach(videoDTOOption -> videoDTOOption.forEach(videoDTO ->
        videoSavedJSONArray.put(new JSONObject()
          .put("name", videoDTO.getName())
          .put("link", BASE_PATH + StringPool.SLASH + videoDTO.getName())
          .put("backupLink", BASE_PATH + StringPool.SLASH + videoDTO.getUuid().toString())
          .put("url", serverLink + BASE_PATH + StringPool.SLASH + videoDTO.getName())
          .put("backupUrl", serverLink + BASE_PATH + StringPool.SLASH + videoDTO.getUuid().toString())
//          .put("details", new JSONObject(videoDTO))
          .put("option", ""))));
    result.put("videoSaved", videoSavedJSONArray)
      .put("totalSaved", videoSavedJSONArray.length())
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

  @Override
  public ResponseEntity<Object> initVideoLivestream()
  {
    var result = new JSONObject();
    MasterUserUtil.getCurrentMasterUserDTO().forEach(masterUserDTO -> result.put("from", new JSONObject(masterUserDTO)));
    videoAdvanceService.createVideoLivestream().forEach(videoDTO -> result.put("info",new JSONObject(videoDTO)));
    return ResponseEntity.status(HttpStatus.CREATED).body(result.toString());
  }

  @Override
  public void producerVideoStreamData(@Payload String body, StompHeaderAccessor stompHeaderAccessor, Principal principal)
  {
    Option.of(new JSONObject(body))
      .forEach(jsonBody ->
      {
        if (jsonBody.has("data") && jsonBody.has("sequenceNumber"))
        {
          var base64Data = jsonBody.getString("data");
          var sequenceNumber = jsonBody.getInt("sequenceNumber");
          Try.of(() ->
          {
            var bytes = Base64Utils.decodeFromString(base64Data.split(",")[2]);
//        var outputStream = new FileOutputStream("D:/catinyx/aaa-Stream.webm", true);
//        outputStream.write(bytes);
//        outputStream.flush();
//        outputStream.close();
            MasterUserUtil.getMasterUserDTOByLogin(null).forEach(masterUser ->
              messagingTemplate.convertAndSendToUser(masterUser.getUuid().toString(), topicConsumer("/videos.data"), new JSONObject()
                .put("data", base64Data)
                .put("fromUser", new JSONObject(masterUser))
                .put("sequenceNumber", sequenceNumber).toString()));
            return null;
          });
        }
      });
  }


  @Override
  public void producerVideoCallIncoming(@Payload String body)
  {
    MasterUserUtil.getMasterUserDTOByLogin(null)
      .forEach(masterUser -> Option.of(new JSONObject(body))
        .forEach(jsonObject ->
        {
          if (jsonObject.has("userIds"))
            jsonObject.getJSONArray("userIds").toList().stream()
              .map(Object::toString)
              .forEach(userId ->
              {
                var result = new JSONObject()
                  .put("fromUser", new JSONObject(masterUser));
                if (jsonObject.has("videoGroupId") && Objects.nonNull(jsonObject.getString("videoGroupId")))
                  result.put("videoGroupId", jsonObject.getString("videoCallId"));
                else
                  result.put("videoGroupId", UUID.randomUUID());
                messagingTemplate.convertAndSendToUser(masterUser.getUuid().toString(), topicConsumer("/videos.incoming"), result.toString());
              });
        }));
  }

  @Override
  public void producerVideoLivestreamData(@Payload String body, @DestinationVariable UUID id, StompHeaderAccessor stompHeaderAccessor, Principal principal)
  {
    Option.of(new JSONObject(body))
      .forEach(jsonBody ->
      {
        if (jsonBody.has("data") && jsonBody.has("sequenceNumber"))
        {
          var base64Data = jsonBody.getString("data");
          var base64DataSplit = base64Data.split(",");
          var sequenceNumber = jsonBody.getInt("sequenceNumber");
          var currentTime = jsonBody.getDouble("currentTime");
          Try.of(() ->
          {
            var bytes = Base64Utils.decodeFromString(base64DataSplit[base64DataSplit.length - 1]);
//            var outputStream = new FileOutputStream("D:/catinyx/aaa-Stream.webm", true);
//            outputStream.write(bytes);
//            outputStream.flush();
//            outputStream.close();
            return null;
          }).forEach(o -> MasterUserUtil.getMasterUserDTOByLogin(null).forEach(masterUser ->
            messagingTemplate.convertAndSendToUser(masterUser.getUuid().toString(), topicConsumer("/livestream.data/" + id.toString()), new JSONObject()
              .put("data", base64Data)
              .put("fromUser", new JSONObject(masterUser))
              .put("sequenceNumber", sequenceNumber)
              .put("id", id)
              .put("currentTime", currentTime)
              .toString())));
        }
      });
  }
}
