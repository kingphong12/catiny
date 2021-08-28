package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.MessageGroupAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.MessageGroupAdvanceMapper;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.service.MessageGroupQueryService;
import com.regitiny.catiny.service.MessageGroupService;
import com.regitiny.catiny.service.dto.MasterUserDTO;
import com.regitiny.catiny.service.dto.MessageGroupDTO;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * AdvanceService layer for {@link MessageGroup}.
 *
 * @see MessageGroupService is base service generate by jhipster
 */
public interface MessageGroupAdvanceService extends BaseSrvice<MessageGroup, MessageGroupService, MessageGroupQueryService, MessageGroupAdvanceMapper, MessageGroupAdvanceRepository, MessageGroupAdvanceSearch>
{


  Page<MessageGroupDTO> getAllMessageGroupsJoined(Pageable pageable);


  Option<MessageGroupDTO> createMessageGroupAndAddUser(List<UUID> userIds, String desiredName);


  List<MasterUserDTO> getMasterUserDetailsPublicByMessageGroupId(UUID messageGroupId);
}
