package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.advance.repository.CommonRepository;
import com.regitiny.catiny.domain.MessageContent;
import com.regitiny.catiny.repository.MessageContentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

/**
 * Spring Data SQL repository for the {@link MessageContent} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.MessageContentAdvanceRepository}
 */
public interface MessageContentBaseRepository extends BaseRepository<MessageContent>, CommonRepository<MessageContent>, MessageContentRepository
{
  Page<MessageContent> findAllByGroupUuidOrderByIdDesc(UUID uuid, Pageable pageable);
}
