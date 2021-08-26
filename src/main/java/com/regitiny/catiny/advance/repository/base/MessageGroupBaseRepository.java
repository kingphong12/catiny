package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.MasterUser;
import com.regitiny.catiny.domain.MessageGroup;
import com.regitiny.catiny.repository.MessageGroupRepository;
import io.vavr.control.Option;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Spring Data SQL repository for the {@link MessageGroup} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.MessageGroupAdvanceRepository}
 */
public interface MessageGroupBaseRepository extends BaseRepository<MessageGroup>, CommonRepository<MessageGroup>, MessageGroupRepository
{
  /**
   * tìm tất cả nhưng group mà user này đã tham gia ,có quyền đọc , chưa bị xóa
   */
  Page<MessageGroup> findAllByInfoPermissionsOwner(MasterUser masterUser, Pageable pageable);


  Option<MessageGroup> findByUuidAndInfoPermissionsOwner(UUID groupUuid, MasterUser owner);
}
