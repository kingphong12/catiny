package com.regitiny.catiny.web.websocket;

import com.regitiny.catiny.advance.service.AccountStatusAdvanceService;
import com.regitiny.catiny.web.websocket.dto.ActivityDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;
import java.time.Instant;

import static com.regitiny.catiny.config.WebsocketConfiguration.IP_ADDRESS;

@Controller
@RequiredArgsConstructor
public class ActivityService implements ApplicationListener<SessionDisconnectEvent> {

  private static final Logger log = LoggerFactory.getLogger(ActivityService.class);

  private final SimpMessageSendingOperations messagingTemplate;
  private final AccountStatusAdvanceService accountStatusAdvanceService;

  @MessageMapping("/topic/activity")
  @SendTo("/topic/tracker")
  public ActivityDTO sendActivity(@Payload ActivityDTO activityDTO, StompHeaderAccessor stompHeaderAccessor, Principal principal) {
    accountStatusAdvanceService.onlineStatus(null);
    activityDTO.setUserLogin(principal.getName());
    activityDTO.setSessionId(stompHeaderAccessor.getSessionId());
    activityDTO.setIpAddress(stompHeaderAccessor.getSessionAttributes().get(IP_ADDRESS).toString());
    activityDTO.setTime(Instant.now());
    log.debug("Sending user tracking data {}", activityDTO);
    return activityDTO;
  }

  @Override
  public void onApplicationEvent(SessionDisconnectEvent event)
  {
    var upat = (UsernamePasswordAuthenticationToken) event.getUser();
    SecurityContextHolder.getContext().setAuthentication(upat);
    accountStatusAdvanceService.offlineStatus();
    ActivityDTO activityDTO = new ActivityDTO();
    activityDTO.setSessionId(event.getSessionId());
    activityDTO.setPage("logout");
    messagingTemplate.convertAndSend("/topic/tracker", activityDTO);
  }
}
