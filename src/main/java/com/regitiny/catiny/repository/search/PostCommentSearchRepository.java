package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.PostComment;
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
 * Spring Data Elasticsearch repository for the {@link PostComment} entity.
 */
@GeneratedByJHipster
public interface PostCommentSearchRepository extends ElasticsearchRepository<PostComment, Long>, PostCommentSearchRepositoryInternal {}

@GeneratedByJHipster
interface PostCommentSearchRepositoryInternal {
  Page<PostComment> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class PostCommentSearchRepositoryInternalImpl implements PostCommentSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  PostCommentSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<PostComment> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<PostComment> hits = elasticsearchTemplate
      .search(nativeSearchQuery, PostComment.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
