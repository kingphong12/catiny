package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.BaseInfo;
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
 * Spring Data Elasticsearch repository for the {@link BaseInfo} entity.
 */
@GeneratedByJHipster
public interface BaseInfoSearchRepository extends ElasticsearchRepository<BaseInfo, Long>, BaseInfoSearchRepositoryInternal {}

@GeneratedByJHipster
interface BaseInfoSearchRepositoryInternal {
  Page<BaseInfo> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class BaseInfoSearchRepositoryInternalImpl implements BaseInfoSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  BaseInfoSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<BaseInfo> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<BaseInfo> hits = elasticsearchTemplate
      .search(nativeSearchQuery, BaseInfo.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
