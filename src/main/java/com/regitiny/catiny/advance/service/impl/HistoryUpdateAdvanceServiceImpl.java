package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.advance.repository.HistoryUpdateAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.HistoryUpdateAdvanceSearch;
import com.regitiny.catiny.advance.service.HistoryUpdateAdvanceService;
import com.regitiny.catiny.advance.service.mapper.HistoryUpdateAdvanceMapper;
import com.regitiny.catiny.domain.BaseInfo;
import com.regitiny.catiny.domain.HistoryUpdate;
import com.regitiny.catiny.service.HistoryUpdateQueryService;
import com.regitiny.catiny.service.HistoryUpdateService;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class HistoryUpdateAdvanceServiceImpl extends AdvanceService<HistoryUpdate, HistoryUpdateService, HistoryUpdateQueryService, HistoryUpdateAdvanceMapper, HistoryUpdateAdvanceRepository, HistoryUpdateAdvanceSearch> implements HistoryUpdateAdvanceService
{
  private final HistoryUpdateAdvanceRepository historyUpdateAdvanceRepository;

  private final HistoryUpdateAdvanceSearch historyUpdateAdvanceSearch;

  private final HistoryUpdateAdvanceMapper historyUpdateAdvanceMapper;

  private final BaseInfoAdvanceServiceImpl baseInfoAdvanceServiceImpl;


  public HistoryUpdate createFirstVersion(BaseInfo baseInfo)
  {
    var result = historyUpdateAdvanceRepository.save(new HistoryUpdate()
      .uuid(UUID.randomUUID())
      .baseInfo(baseInfo)
      .version(0)
      .content(null));
    historyUpdateAdvanceSearch.save(historyUpdateAdvanceMapper.cleanEntity(result));
    return result;
  }

  public Option<HistoryUpdate> updateChangeLog(BaseInfo baseInfoInput, String content)
  {
    if (Objects.isNull(content))
    {
      log.warn("content is null then update is meaningless.");
      return Option.none();
    }

    return baseInfoAdvanceServiceImpl.findOne(baseInfoInput)
      .map(baseInfo -> historyUpdateAdvanceRepository.lastVersion(baseInfo)
        .filter(integer -> !baseInfo.getHistories().isEmpty())
        .map(integer -> new HistoryUpdate().baseInfo(baseInfo)
          .version(++integer)
          .content(content))
        .orElse(Option.of(new HistoryUpdate()
          .uuid(UUID.randomUUID())
          .version(0)
          .baseInfo(baseInfo)))
        .map(historyUpdateAdvanceRepository::save)
        .get());
  }
}
