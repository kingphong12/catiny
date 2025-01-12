package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.Permission;
import com.regitiny.catiny.repository.PermissionRepository;
import io.vavr.control.Option;

/**
 * Spring Data SQL repository for the {@link Permission} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.PermissionAdvanceRepository}
 */
public interface PermissionBaseRepository extends BaseRepository<Permission>, CommonRepository<Permission>, PermissionRepository
{
  Option<Permission> findOneByBaseInfoAndOwner(BaseInfo baseInfo, MasterUser masterUser);
}
