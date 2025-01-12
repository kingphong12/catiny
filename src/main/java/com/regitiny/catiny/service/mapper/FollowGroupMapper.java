package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.FollowGroupDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FollowGroup} and its DTO {@link FollowGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, GroupPostMapper.class })
@GeneratedByJHipster
public interface FollowGroupMapper extends EntityMapper<FollowGroupDTO, FollowGroup> {
  @Mapping(target = "info", source = "info", qualifiedByName = "id")
  @Mapping(target = "groupDetails", source = "groupDetails", qualifiedByName = "id")
  FollowGroupDTO toDto(FollowGroup s);
}
