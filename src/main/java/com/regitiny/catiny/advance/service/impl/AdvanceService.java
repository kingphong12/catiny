package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.tools.utils.StringPool;
import io.vavr.control.Option;
import io.vavr.control.Try;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @param <S>  EntityService
 * @param <Q>  EntityQueryService
 * @param <AM> EntityAdvanceMapper
 * @param <AR> EntityAdvanceRepository
 * @param <AS> EntityAdvanceSearch
 */
@Log4j2
@Component
public class AdvanceService<E, S, Q, AM, AR, AS>
{
  @Autowired
  private ApplicationContext applicationContext;

  private S localService;
  private Q localQueryService;
  private AM localAdvanceMapper;
  private AR localAdvanceRepository;
  private AS localAdvanceSearch;

  private Local local = null;
//  private LocalProtected localProtected =null;
//  private LocalPublic localPublic = null;

  private String entityNameFU()
  {
    return Option.of(getClass().getSimpleName()).filter(s -> s.contains("AdvanceServiceImpl"))
      .map(s -> s.replace("AdvanceServiceImpl", StringPool.BLANK)).getOrElseThrow(() -> new RuntimeException(""));
  }

  private String entityNameFL()
  {
    var entityNameFU = entityNameFU();
    var matcher = Pattern.compile("^[A-Z]+").matcher(entityNameFU);
    matcher.find();
    var firstUpper = matcher.group(0);
    return entityNameFU.replaceFirst("^[A-Z]+", firstUpper.toLowerCase());
  }

  public Local local()
  {
    if (Objects.isNull(local))
      setup();
    return local;
  }

  @SuppressWarnings("unchecked")
  private void setup()
  {
    var entityNameFL = entityNameFL();
    localService = (S) Option.when(Objects.isNull(localService), entityNameFL + "ServiceImpl")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalService");
        return null;
      });
    localQueryService = (Q) Option.when(Objects.isNull(localQueryService), entityNameFL + "QueryService")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalQueryService");
        return null;
      });
    localAdvanceMapper = (AM) Option.when(Objects.isNull(localAdvanceMapper), entityNameFL + "AdvanceMapperImpl")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalAdvanceMapper");
        return null;
      });

    localAdvanceRepository = (AR) Option.when(Objects.isNull(localAdvanceRepository), entityNameFL + "AdvanceRepository")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalAdvanceRepository");
        return null;
      });

    localAdvanceSearch = (AS) Option.when(Objects.isNull(localAdvanceSearch), entityNameFL + "AdvanceSearch")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalAdvanceSearch");
        return null;
      });
    local = new Local();
//    localPublic=new LocalPublic();
//    localProtected = new LocalProtected();
  }

  public E create()
  {
    return (E) Try.of(() -> Class.forName("")).get();
  }

  class Local
  {
    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localAdvanceRepository;
    public final AS advanceSearch = localAdvanceSearch;
  }

//  public LocalPublic localPublic()
//  {
//    if (Objects.isNull(local))
//      setup();
//    return localPublic;
//  }

  protected class LocalProtected
  {

    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localAdvanceRepository;
    public final AS advanceSearch = localAdvanceSearch;
  }

  public class LocalPublic
  {
    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localAdvanceRepository;
    public final AS advanceSearch = localAdvanceSearch;
  }
}
