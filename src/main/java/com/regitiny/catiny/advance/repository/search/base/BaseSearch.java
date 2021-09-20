package com.regitiny.catiny.advance.repository.search.base;

import com.regitiny.catiny.common.utils.StringPool;
import io.vavr.control.Option;
import org.elasticsearch.index.query.QueryBuilder;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;
import java.util.stream.Collectors;

import static com.regitiny.catiny.util.ApplicationContextUtils.getApplicationContext;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

public interface BaseSearch<T>
{

  default Page<T> search(String query, Pageable pageable)
  {
    return search(queryStringQuery(query), pageable);
  }


  default Page<T> search(QueryBuilder query, Pageable pageable)
  {
    var log = LoggerFactory.getLogger(this.getClass());
    var elasticsearchTemplate = getApplicationContext().getBean(ElasticsearchRestTemplate.class);
    var nativeSearchQuery = new NativeSearchQuery(query);
    nativeSearchQuery.setPageable(pageable);
    var entityClassName = Option.of(this.getClass().getGenericInterfaces()).map(genericInterfaces ->
      {
        for (var genericInterface :genericInterfaces)
        {
          var name = genericInterface.getTypeName();
          if (name.matches("com.regitiny.catiny.advance.repository.search.base.[A-Z]+[\\w]+BaseSearch"))
            return name.replace("com.regitiny.catiny.advance.repository.search.base.", StringPool.BLANK)
              .replace("BaseSearch", StringPool.BLANK);
          if (name.matches("com.regitiny.catiny.advance.repository.search.[A-Z]+[\\w]+AdvanceSearch"))
            return name.replace("com.regitiny.catiny.advance.repository.search.", StringPool.BLANK)
              .replace("AdvanceSearch", StringPool.BLANK);
        }
        return null;
      })
      .map(entityName -> "com.regitiny.catiny.domain." + entityName)
      .toTry()
      .mapTry(Class::forName)
      .getOrElse(() ->
      {
        log.warn("don't know class name: {}", this.getClass().getName());
        return null;
      });
    //noinspection unchecked
    List<T> hits = elasticsearchTemplate
      .search(nativeSearchQuery, entityClassName)
      .map(SearchHit::getContent).map(o -> (T) o)
      .stream()
      .collect(Collectors.toList());

    return new PageImpl<>(hits, pageable, hits.size());

  }
}
