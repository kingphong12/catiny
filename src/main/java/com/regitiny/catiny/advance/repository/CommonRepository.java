package com.regitiny.catiny.advance.repository;

import io.vavr.control.Option;

import java.util.List;
import java.util.UUID;

public interface CommonRepository<E>
{
  Option<E> findOneByUuid(UUID uuid);


  void deleteOneByUuid(UUID uuid);


  void deleteAllByUuid(UUID... uuid);


  void deleteAllByUuid(List<UUID> uuid);

}
