package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.UserProfile;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link UserProfile} entity.
 */
@GeneratedByJHipster
public interface UserProfileSearchRepository extends ElasticsearchRepository<UserProfile, Long>, UserProfileSearchRepositoryInternal {}

@GeneratedByJHipster
interface UserProfileSearchRepositoryInternal {
  Page<UserProfile> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class UserProfileSearchRepositoryInternalImpl implements UserProfileSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  UserProfileSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<UserProfile> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<UserProfile> hits = elasticsearchTemplate
      .search(nativeSearchQuery, UserProfile.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
