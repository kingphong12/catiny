package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.VideoStream;
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
 * Spring Data Elasticsearch repository for the {@link VideoStream} entity.
 */
@GeneratedByJHipster
public interface VideoStreamSearchRepository extends ElasticsearchRepository<VideoStream, Long>, VideoStreamSearchRepositoryInternal {}

@GeneratedByJHipster
interface VideoStreamSearchRepositoryInternal {
  Page<VideoStream> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class VideoStreamSearchRepositoryInternalImpl implements VideoStreamSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  VideoStreamSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<VideoStream> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<VideoStream> hits = elasticsearchTemplate
      .search(nativeSearchQuery, VideoStream.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
