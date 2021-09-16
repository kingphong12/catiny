package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.AccountStatus;
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
 * Spring Data Elasticsearch repository for the {@link AccountStatus} entity.
 */
@GeneratedByJHipster
public interface AccountStatusSearchRepository
  extends ElasticsearchRepository<AccountStatus, Long>, AccountStatusSearchRepositoryInternal {}

@GeneratedByJHipster
interface AccountStatusSearchRepositoryInternal {
  Page<AccountStatus> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class AccountStatusSearchRepositoryInternalImpl implements AccountStatusSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  AccountStatusSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<AccountStatus> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<AccountStatus> hits = elasticsearchTemplate
      .search(nativeSearchQuery, AccountStatus.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
