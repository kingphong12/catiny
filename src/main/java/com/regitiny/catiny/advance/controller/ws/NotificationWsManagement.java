package com.regitiny.catiny.advance.controller.ws;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.security.Principal;
import java.util.UUID;

import static com.regitiny.catiny.util.WebSocketUtils.TOPIC_PRODUCER;

@MessageMapping(TOPIC_PRODUCER + "")
public interface NotificationWsManagement
{
  @MessageMapping("/video.data")
  void producerVideoStreamData(@Payload String body, StompHeaderAccessor stompHeaderAccessor, Principal principal);


  @MessageMapping("/notifications/video-call.incoming")
  void producerVideoCallIncoming(@Payload String body);


  @MessageMapping("/livestream.data/{id}")
  void producerVideoLivestreamData(@Payload String body, @DestinationVariable UUID id, StompHeaderAccessor stompHeaderAccessor, Principal principal);
}