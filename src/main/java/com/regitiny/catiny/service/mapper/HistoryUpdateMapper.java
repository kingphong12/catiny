package com.regitiny.catiny.service.mapper;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.*;
import com.regitiny.catiny.service.dto.HistoryUpdateDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link HistoryUpdate} and its DTO {@link HistoryUpdateDTO}.
 */
@Mapper(componentModel = "spring", uses = { BaseInfoMapper.class })
@GeneratedByJHipster
public interface HistoryUpdateMapper extends EntityMapper<HistoryUpdateDTO, HistoryUpdate> {
  @Mapping(target = "baseInfo", source = "baseInfo", qualifiedByName = "id")
  HistoryUpdateDTO toDto(HistoryUpdate s);
}
