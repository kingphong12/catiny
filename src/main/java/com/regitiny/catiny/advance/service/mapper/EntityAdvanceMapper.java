package com.regitiny.catiny.advance.service.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

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
  D e2d(E entity);


  E d2e(D dto);


  List<D> e2d(List<E> entityList);


  List<E> d2e(List<D> dtoList);


  @Named("partialUpdate")
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void partialUpdate(@MappingTarget E entity, D dto);
}
