package com.regitiny.catiny.advance.service.mapper;

import java.util.List;


/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <M>  - Model type parameter.
 * @param <D>  - DTO type parameter.
 * @param <E>> - Entity type parameter.
 */

public interface ModelMapper<M, D, E>
{
  // model <-> dto
  D m2d(M model);


  M d2m(D dto);


  List<D> m2d(List<M> modelList);


  List<M> d2m(List<D> dtoList);


  // model <-> entity
  E m2e(M model);


  M e2m(E entity);


  List<E> m2e(List<M> modelList);


  List<M> e2m(List<E> entityList);
}
