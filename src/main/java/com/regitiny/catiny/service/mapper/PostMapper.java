package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PostDTO;
import java.util.Set;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Post} and its DTO {@link PostDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, GroupPostMapper.class, PagePostMapper.class })
@GeneratedByJHipster
public interface PostMapper extends EntityMapper<PostDTO, Post> {
  @Mapping(target = "info", source = "info", qualifiedByName = "id")
  @Mapping(target = "group", source = "group", qualifiedByName = "id")
  @Mapping(target = "page", source = "page", qualifiedByName = "id")
  @Mapping(target = "parent", source = "parent", qualifiedByName = "id")
  PostDTO toDto(Post s);

  @Named("id")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  PostDTO toDtoId(Post post);

  @Named("idSet")
  @BeanMapping(ignoreByDefault = true)
  @Mapping(target = "id", source = "id")
  Set<PostDTO> toDtoIdSet(Set<Post> post);
}
