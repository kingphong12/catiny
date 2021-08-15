package com.regitiny.catiny.advance.repository;

import com.regitiny.catiny.advance.repository.base.MasterUserBaseRepository;
import com.regitiny.catiny.domain.MasterUser;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.MasterUser} entity.
 * <p>
 * here contains complex queries with pure SQL or HQL syntax.
 * if you want to write simple query then you should write to :
 * {@link MasterUserBaseRepository}
 */
@Repository
@SuppressWarnings("unused")
public interface MasterUserAdvanceRepository extends MasterUserBaseRepository
{
  @Query("select mu " +
    "from MasterUser mu left join mu.permissions permissions left join MessageGroup mg on mg.info=permissions.baseInfo " +
    "where mg.uuid =?1")
  List<MasterUser> findAllByMessageGroupUuid(UUID messageGroupUuid);


  /**
   * to not need to check permission
   */
  @Override
  Option<MasterUser> findOneByUuid(UUID uuid);
}
