package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.ClassInfo;
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
 * Spring Data Elasticsearch repository for the {@link ClassInfo} entity.
 */
@GeneratedByJHipster
public interface ClassInfoSearchRepository extends ElasticsearchRepository<ClassInfo, Long>, ClassInfoSearchRepositoryInternal {}

@GeneratedByJHipster
interface ClassInfoSearchRepositoryInternal {
  Page<ClassInfo> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class ClassInfoSearchRepositoryInternalImpl implements ClassInfoSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  ClassInfoSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<ClassInfo> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<ClassInfo> hits = elasticsearchTemplate
      .search(nativeSearchQuery, ClassInfo.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
