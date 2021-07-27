package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.AccountStatusModel;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * this is a custom mapper for each entity .
 * this mapper extend from Jhipster mapper
 * ( d=dto, e=entity , m=model and To=2 ) --> ( dtoToEntity = d2e , modelToDto = m2d ).
 */
@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface AccountStatusAdvanceMapper extends EntityAdvanceMapper<AccountStatusModel, AccountStatusDTO, AccountStatus>
{
  AccountStatusDTO request2d(AccountStatusModel.Request request);


  List<AccountStatusDTO> request2d(List<AccountStatusModel.Request> request);


  AccountStatusModel.Response d2Response(AccountStatusDTO dto);


  List<AccountStatusModel.Response> d2Response(List<AccountStatusDTO> dto);
}
