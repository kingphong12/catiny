package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.advance.repository.base.AccountStatusBaseRepository;
import com.regitiny.catiny.domain.AccountStatus;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.enumeration.StatusName;
import io.vavr.control.Option;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.AccountStatus} entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link AccountStatusBaseRepository}
 */
@Repository
@SuppressWarnings("unused")
public interface AccountStatusAdvanceRepository extends AccountStatusBaseRepository
{
  Option<AccountStatus> findOneByInfoOwnerAndAccountStatus(MasterUser owner, StatusName statusName);
}
