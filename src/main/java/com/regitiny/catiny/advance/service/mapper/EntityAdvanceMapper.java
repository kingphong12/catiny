package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.service.mapper.EntityMapper;
import com.regitiny.catiny.util.ApplicationContextUtil;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <M> - Model type parameter.
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityAdvanceMapper<M, D, E> extends ModelMapper<M, D, E>
{
  // dto <-> entity
  default E d2e(D dto)
  {
    return baseMapper().toEntity(dto);
  }


  default List<E> d2e(List<D> dtoList)
  {
    return baseMapper().toEntity(dtoList);
  }


  default D e2d(E entity)
  {
    return baseMapper().toDto(entity);
  }


  default List<D> e2d(List<E> entityList)
  {
    return baseMapper().toDto(entityList);
  }


  @Override
  default M e2m(E entity)
  {
    return thisMapper().d2m(baseMapper().toDto(entity));
  }


  @Override
  default List<M> e2m(List<E> entityList)
  {
    return thisMapper().d2m(baseMapper().toDto(entityList));
  }


  default void partialUpdate(E entity, D dto)
  {
    baseMapper().partialUpdate(entity, dto);
  }


  default E cleanEntity(E entity)
  {
    return d2e(e2d(entity));
  }


  default List<E> cleanEntity(List<E> entityList)
  {
    return d2e(e2d(entityList));
  }


  default EntityMapper<D, E> baseMapper()
  {
    var log = LoggerFactory.getLogger(this.getClass());
    var thisClassName = this.getClass().getSimpleName();
    if (thisClassName.contains("AdvanceMapperImpl"))
    {
      var baseMapperName = "com.regitiny.catiny.service.mapper." + thisClassName.replace("AdvanceMapperImpl", "Mapper");
      try
      {
        //noinspection unchecked
        return (EntityMapper<D, E>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(baseMapperName));
      }
      catch (ClassNotFoundException e)
      {
        log.warn("not found class whit name {}", baseMapperName);
      }
    }
    return null;
  }


  default EntityAdvanceMapper<M, D, E> thisMapper()
  {
    //noinspection unchecked
    return ApplicationContextUtil.getApplicationContext().getBean(this.getClass());
  }

}
