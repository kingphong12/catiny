package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FollowPage;
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
 * Spring Data Elasticsearch repository for the {@link FollowPage} entity.
 */
@GeneratedByJHipster
public interface FollowPageSearchRepository extends ElasticsearchRepository<FollowPage, Long>, FollowPageSearchRepositoryInternal {}

@GeneratedByJHipster
interface FollowPageSearchRepositoryInternal {
  Page<FollowPage> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class FollowPageSearchRepositoryInternalImpl implements FollowPageSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  FollowPageSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<FollowPage> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<FollowPage> hits = elasticsearchTemplate
      .search(nativeSearchQuery, FollowPage.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
