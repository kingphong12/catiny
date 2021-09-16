package com.regitiny.catiny.repository.search;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.User;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.stream.Stream;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

/**
 * Spring Data Elasticsearch repository for the User entity.
 */
@GeneratedByJHipster
public interface UserSearchRepository extends ElasticsearchRepository<User, Long>, UserSearchRepositoryInternal {}

@GeneratedByJHipster
interface UserSearchRepositoryInternal {
  Stream<User> search(String query);
}

@GeneratedByJHipster
class UserSearchRepositoryInternalImpl implements UserSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  UserSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Stream<User> search(String query) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    return elasticsearchTemplate.search(nativeSearchQuery, User.class).map(SearchHit::getContent).stream();
  }
}
