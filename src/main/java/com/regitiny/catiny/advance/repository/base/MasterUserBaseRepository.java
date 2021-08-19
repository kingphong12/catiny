package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.config.CacheConfiguration;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.repository.MasterUserRepository;
import io.vavr.control.Option;
import org.springframework.cache.annotation.Cacheable;

import java.util.Optional;

/**
 * Spring Data SQL repository for the {@link MasterUser} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.MasterUserAdvanceRepository}
 */
public interface MasterUserBaseRepository extends BaseRepository<MasterUser>, CommonRepository<MasterUser>, MasterUserRepository
{
  @Cacheable(key = "#login", cacheNames = CacheConfiguration.CacheNameConstants.MASTER_USER_BY_LOGIN)
  Option<MasterUser> findOneByUserLogin(String login);


  @Override
  @Cacheable(key = "#id", cacheNames = CacheConfiguration.CacheNameConstants.MASTER_USER_BY_ID)
  Optional<MasterUser> findById(Long id);
}
