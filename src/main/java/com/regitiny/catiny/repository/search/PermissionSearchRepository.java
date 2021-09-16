package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.Permission;
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
 * Spring Data Elasticsearch repository for the {@link Permission} entity.
 */
@GeneratedByJHipster
public interface PermissionSearchRepository extends ElasticsearchRepository<Permission, Long>, PermissionSearchRepositoryInternal {}

@GeneratedByJHipster
interface PermissionSearchRepositoryInternal {
  Page<Permission> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class PermissionSearchRepositoryInternalImpl implements PermissionSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  PermissionSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<Permission> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<Permission> hits = elasticsearchTemplate
      .search(nativeSearchQuery, Permission.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
