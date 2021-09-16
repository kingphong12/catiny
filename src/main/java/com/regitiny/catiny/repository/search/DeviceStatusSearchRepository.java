package com.regitiny.catiny.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import com.regitiny.catiny.GeneratedByJHipster;
import com.regitiny.catiny.domain.DeviceStatus;
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
 * Spring Data Elasticsearch repository for the {@link DeviceStatus} entity.
 */
@GeneratedByJHipster
public interface DeviceStatusSearchRepository extends ElasticsearchRepository<DeviceStatus, Long>, DeviceStatusSearchRepositoryInternal {}

@GeneratedByJHipster
interface DeviceStatusSearchRepositoryInternal {
  Page<DeviceStatus> search(String query, Pageable pageable);
}

@GeneratedByJHipster
class DeviceStatusSearchRepositoryInternalImpl implements DeviceStatusSearchRepositoryInternal {

  private final ElasticsearchRestTemplate elasticsearchTemplate;

  DeviceStatusSearchRepositoryInternalImpl(ElasticsearchRestTemplate elasticsearchTemplate) {
    this.elasticsearchTemplate = elasticsearchTemplate;
  }

  @Override
  public Page<DeviceStatus> search(String query, Pageable pageable) {
    NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
    nativeSearchQuery.setPageable(pageable);
    List<DeviceStatus> hits = elasticsearchTemplate
      .search(nativeSearchQuery, DeviceStatus.class)
      .map(SearchHit::getContent)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());
  }
}
