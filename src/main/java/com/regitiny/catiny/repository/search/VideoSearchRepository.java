package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Video;
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
 * Spring Data Elasticsearch repository for the {@link Video} entity.
 */
@GeneratedByJHipster
public interface VideoSearchRepository extends ElasticsearchRepository<Video, Long>, VideoSearchRepositoryInternal {}

@GeneratedByJHipster
interface VideoSearchRepositoryInternal {
  Page<Video> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class VideoSearchRepositoryInternalImpl implements VideoSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  VideoSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<Video> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<Video> hits = elasticsearchTemplate
      .search(nativeSearchQuery, Video.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
