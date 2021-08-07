package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.FollowUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link FollowUser} and its DTO {@link FollowUserDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, MasterUserMapper.class })
@GeneratedByJHipster
public interface FollowUserMapper extends EntityMapper<FollowUserDTO, FollowUser> {
  @Mapping(target = "info", source = "info", qualifiedByName = "id")
  @Mapping(target = "follow", source = "follow", qualifiedByName = "id")
  FollowUserDTO toDto(FollowUser s);
}
