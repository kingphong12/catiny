package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.domain.DeviceStatus;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.DeviceStatusRepository;
import io.vavr.collection.List;

/**
 * Spring Data SQL repository for the {@link DeviceStatus} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.DeviceStatusAdvanceRepository}
 */
public interface DeviceStatusBaseRepository extends BaseRepository<DeviceStatus>, CommonRepository<DeviceStatus>, DeviceStatusRepository
{
  List<DeviceStatus> findAllByAccountStatus(AccountStatus accountStatus);


  List<DeviceStatus> findAllByInfoOwner(MasterUser owner);
}
