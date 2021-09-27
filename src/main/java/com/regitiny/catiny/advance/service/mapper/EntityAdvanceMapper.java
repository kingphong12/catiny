package com.regitiny.catiny.advance.service.mapper;

import com.regitiny.catiny.common.quick.ReflectMapper;
import com.regitiny.catiny.service.mapper.EntityMapper;
import com.regitiny.catiny.util.ApplicationContextUtils;
import io.vavr.control.Option;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <M> - Model type parameter.
 * @param <D> - DTO type parameter.
 * @param <E> - Entity type parameter.
 */

public interface EntityAdvanceMapper<M, D, E> extends VavrMapper<M, D, E> // ModelMapper<M, D, E>
{
  /**
   * entity -> dto and add custom by regex.
   * read to use {@link ReflectMapper}.addByRegex()
   */
  default D e2dPlus(E entity, String customMappingRegex)
  {
    var dto = e2d(entity);
    ReflectMapper.of(entity, dto)
      .addByRegex(customMappingRegex);
    return dto;
  }


  /**
   * entity -> dto -> ReflectMapper.
   * read to use {@link ReflectMapper}
   */
  default ReflectMapper<E, D> e2Plus(E entity)
  {
    var dto = e2d(entity);
    return new ReflectMapper<>(entity, dto);
  }


  // dto -> entity
  default E d2e(D dto)
  {
    return baseMapper().toEntity(dto);
  }


  @Override
  default Option<E> d2e(Option<D> dto)
  {
    return dto.map(this::d2e);
  }


  @Override
  default io.vavr.collection.List<E> d2e(io.vavr.collection.List<D> dto)
  {
    return io.vavr.collection.List.ofAll(d2e(dto.toJavaList()));
  }


  default List<E> d2e(List<D> dtoList)
  {
    return baseMapper().toEntity(dtoList);
  }


  // entity -> dto
  default D e2d(E entity)
  {
    return baseMapper().toDto(entity);
  }


  @Override
  default Option<D> e2d(Option<E> entity)
  {
    return entity.map(this::e2d);
  }


  @Override
  default io.vavr.collection.List<D> e2d(io.vavr.collection.List<E> entity)
  {
    return io.vavr.collection.List.ofAll(e2d(entity.toJavaList()));
  }


  default List<D> e2d(List<E> entityList)
  {
    return baseMapper().toDto(entityList);
  }


  default Option<D> e2o_d(E entity)
  {
    return Option.of(e2d(entity));
  }


//  @Override
//  default M e2m(E entity)
//  {
//    return thisMapper().d2m(baseMapper().toDto(entity));
//  }
//
//
//  @Override
//  default List<M> e2m(List<E> entityList)
//  {
//    return thisMapper().d2m(baseMapper().toDto(entityList));
//  }


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
        return (EntityMapper<D, E>) ApplicationContextUtils.getApplicationContext().getBean(Class.forName(baseMapperName));
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
    return ApplicationContextUtils.getApplicationContext().getBean(this.getClass());
  }

}
