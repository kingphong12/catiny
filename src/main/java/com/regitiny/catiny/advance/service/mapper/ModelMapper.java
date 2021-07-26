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
  D m2d(M model);


  M d2m(D dto);


  List<D> m2d(List<M> modelList);


  List<M> d2m(List<D> dtoList);


  D e2d(E entity);


  E d2e(D dto);


  List<D> e2d(List<E> entityList);


  List<E> d2e(List<D> dtoList);


  E m2e(M model);


  M e2m(E entity);


  List<E> m2e(List<M> modelList);


  List<M> e2m(List<E> entityList);
}
