package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.HanhChinhVN;
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
 * Spring Data Elasticsearch repository for the {@link HanhChinhVN} entity.
 */
@GeneratedByJHipster
public interface HanhChinhVNSearchRepository extends ElasticsearchRepository<HanhChinhVN, Long>, HanhChinhVNSearchRepositoryInternal {}

@GeneratedByJHipster
interface HanhChinhVNSearchRepositoryInternal {
  Page<HanhChinhVN> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class HanhChinhVNSearchRepositoryInternalImpl implements HanhChinhVNSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  HanhChinhVNSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<HanhChinhVN> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<HanhChinhVN> hits = elasticsearchTemplate
      .search(nativeSearchQuery, HanhChinhVN.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
