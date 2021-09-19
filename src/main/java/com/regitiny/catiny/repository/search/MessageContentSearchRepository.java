package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.MessageContent;
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
 * Spring Data Elasticsearch repository for the {@link MessageContent} entity.
 */
@GeneratedByJHipster
public interface MessageContentSearchRepository
  extends ElasticsearchRepository<MessageContent, Long>, MessageContentSearchRepositoryInternal {}

@GeneratedByJHipster
interface MessageContentSearchRepositoryInternal {
  Page<MessageContent> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class MessageContentSearchRepositoryInternalImpl implements MessageContentSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  MessageContentSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<MessageContent> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<MessageContent> hits = elasticsearchTemplate
      .search(nativeSearchQuery, MessageContent.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
