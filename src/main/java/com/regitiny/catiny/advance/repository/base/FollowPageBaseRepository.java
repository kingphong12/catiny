package com.regitiny.catiny.advance.repository.base;

import com.regitiny.catiny.repository.FollowPageRepository;

/**
 * Spring Data SQL repository for the {@link com.regitiny.catiny.domain.FollowPage} entity.
 * <p>
 * here contains simple query JPA syntax.
 * if you want to write complex query pure (SQL or HQL) then you should write to :
 * {@link com.regitiny.catiny.advance.repository.FollowPageAdvanceRepository}
 */
public interface FollowPageBaseRepository extends FollowPageRepository
{
}
