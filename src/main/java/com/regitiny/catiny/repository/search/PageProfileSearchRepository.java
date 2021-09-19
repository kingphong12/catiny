package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PageProfile;
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
 * Spring Data Elasticsearch repository for the {@link PageProfile} entity.
 */
@GeneratedByJHipster
public interface PageProfileSearchRepository extends ElasticsearchRepository<PageProfile, Long>, PageProfileSearchRepositoryInternal {}

@GeneratedByJHipster
interface PageProfileSearchRepositoryInternal {
  Page<PageProfile> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class PageProfileSearchRepositoryInternalImpl implements PageProfileSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  PageProfileSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<PageProfile> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<PageProfile> hits = elasticsearchTemplate
      .search(nativeSearchQuery, PageProfile.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
