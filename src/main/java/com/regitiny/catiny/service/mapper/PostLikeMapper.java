package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.PostLikeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link PostLike} and its DTO {@link PostLikeDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class, PostMapper.class, PostCommentMapper.class })
@GeneratedByJHipster
public interface PostLikeMapper extends EntityMapper<PostLikeDTO, PostLike> {
  @Mapping(target = "info", source = "info", qualifiedByName = "id")
  @Mapping(target = "post", source = "post", qualifiedByName = "id")
  @Mapping(target = "comment", source = "comment", qualifiedByName = "id")
  PostLikeDTO toDto(PostLike s);
}
