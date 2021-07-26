package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.advance.controller.model.AccountStatusModel;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.service.dto.AccountStatusDTO;
import com.regitiny.catiny.service.mapper.AccountStatusMapper;
import com.regitiny.catiny.service.mapper.AccountStatusMapperImpl;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
  componentModel = "spring",
  uses = {}
)
public interface AccountStatusAdvanceMapper extends EntityAdvanceMapper<AccountStatusModel, AccountStatusDTO, AccountStatus>
{
  AccountStatusMapper baseMapper = new AccountStatusMapperImpl();

  AccountStatusAdvanceMapper thisMapper = new AccountStatusAdvanceMapperImpl();


  AccountStatusDTO request2d(AccountStatusModel.Request request);


  List<AccountStatusDTO> request2d(List<AccountStatusModel.Request> request);


  AccountStatusModel.Response d2Response(AccountStatusDTO dto);


  List<AccountStatusModel.Response> d2Response(List<AccountStatusDTO> dto);


  @Override
  default AccountStatusModel e2m(AccountStatus entity)
  {
    return thisMapper.d2m(baseMapper.toDto(entity));
  }


  @Override
  default List<AccountStatusModel> e2m(List<AccountStatus> entityList)
  {
    return thisMapper.d2m(baseMapper.toDto(entityList));
  }


  @Override
  default AccountStatus d2e(AccountStatusDTO dto)
  {
    return baseMapper.toEntity(dto);
  }


  @Override
  default List<AccountStatus> d2e(List<AccountStatusDTO> dtoList)
  {
    return baseMapper.toEntity(dtoList);
  }


  @Override
  default AccountStatusDTO e2d(AccountStatus entity)
  {
    return baseMapper.toDto(entity);
  }


  @Override
  default List<AccountStatusDTO> e2d(List<AccountStatus> entityList)
  {
    return baseMapper.toDto(entityList);
  }
}
