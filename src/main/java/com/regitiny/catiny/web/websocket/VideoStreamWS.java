package com.regitiny.catiny.web.websocket;

import com.regitiny.catiny.util.MasterUserUtil;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;

import java.security.Principal;

@Log4j2
@Controller
@RequiredArgsConstructor
public class VideoStreamWS
{
  private final SimpMessageSendingOperations messagingTemplate;

  @MessageMapping("/topic/video-call")
  public void receiveVideoStreamData(@Payload String body, StompHeaderAccessor stompHeaderAccessor, Principal principal)
  {
    Option.of(new JSONObject(body))
      .map(jsonBody -> jsonBody.getString("data"))
      .toTry()
      .mapTry(base64Data ->
      {
        var bytes = Base64Utils.decodeFromString(base64Data.split(",")[2]);
        var topic = "/topic/users/${user}/video-call";
//        var outputStream = new FileOutputStream("D:/catinyx/aaa-Stream.webm", true);
//        outputStream.write(bytes);
//        outputStream.flush();
//        outputStream.close();
        MasterUserUtil.getMasterUserDTOByLogin(null).forEach(masterUser ->
          messagingTemplate.convertAndSend(topic.replace("${user}", masterUser.getUuid().toString()), new JSONObject().put("data", base64Data).toString()));
        return null;
      });
  }
}
