package com.regitiny.catiny.advance.service.mapper;

import io.vavr.collection.List;
import io.vavr.control.Option;


/**
 * future features
 * Contract for a generic dto to entity mapper.
 *
 * @param <M>  - Model type parameter.
 * @param <D>  - DTO type parameter.
 * @param <E>> - Entity type parameter.
 */
public interface VavrMapper<M, D, E>
{
  //  entity <-> dto
  List<E> d2e(List<D> dtoList);


  List<D> e2d(List<E> entityList);


  Option<E> d2e(Option<D> dtoList);


  Option<D> e2d(Option<E> entityList);

//  // model <-> dto
//  Option<D> m2d(Option<M> model);
//
//
//  Option<M> d2m(Option<D> dto);
//
//
//  List<D> m2d(List<M> modelList);
//
//
//  List<M> d2m(List<D> dtoList);


//  // model <-> entity
//  Option<E> m2e(Option<M> model);
//
//
//  Option<M> e2m(Option<E> entity);
//
//
//  List<E> m2e(List<M> modelList);
//
//
//  List<M> e2m(List<E> entityList);
}
