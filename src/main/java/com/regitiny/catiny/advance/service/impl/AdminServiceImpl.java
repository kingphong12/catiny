package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.service.AdminService;
import com.regitiny.catiny.advance.service.mapper.EntityAdvanceMapper;
import com.regitiny.catiny.common.utils.StringPool;
import com.regitiny.catiny.util.ApplicationContextUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService
{
  @Override
  @SuppressWarnings("unchecked")
  public boolean reindexEntity(String entityName)
  {
    final var packageAdvanceSearch = "com.regitiny.catiny.advance.repository.search";
    final var packageAdvanceRepository = "com.regitiny.catiny.advance.repository";
    var advanceSearchName = packageAdvanceSearch + StringPool.PERIOD + entityName + "AdvanceSearch";
    var advanceRepositoryName = packageAdvanceRepository + StringPool.PERIOD + entityName + "AdvanceRepository";
    var advanceMapperName = "com.regitiny.catiny.advance.service.mapper." + entityName + "AdvanceMapper";
    try
    {
      var advanceMapper = (EntityAdvanceMapper<?, ?, Object>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceMapperName));
      var advanceRepository = (CrudRepository<?, ?>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceRepositoryName));
      var advanceSearch = (CrudRepository<Object, ?>) ApplicationContextUtil.getApplicationContext().getBean(Class.forName(advanceSearchName));
      advanceSearch.deleteAll();
      advanceSearch.saveAll(advanceMapper.cleanEntity((List<Object>) advanceRepository.findAll()));

      log.info("reindex all entity : {} Done!", entityName);
      return true;
    }
    catch (Exception e)
    {
      log.warn("error when reindex entity : {}", entityName, e);
      return false;
    }
  }
}
