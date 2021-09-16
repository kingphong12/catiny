package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.FileInfo;
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
 * Spring Data Elasticsearch repository for the {@link FileInfo} entity.
 */
@GeneratedByJHipster
public interface FileInfoSearchRepository extends ElasticsearchRepository<FileInfo, Long>, FileInfoSearchRepositoryInternal {}

@GeneratedByJHipster
interface FileInfoSearchRepositoryInternal {
  Page<FileInfo> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class FileInfoSearchRepositoryInternalImpl implements FileInfoSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  FileInfoSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<FileInfo> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<FileInfo> hits = elasticsearchTemplate
      .search(nativeSearchQuery, FileInfo.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
