package com.regitiny.catiny.advance.service.impl;

import com.regitiny.catiny.tools.utils.StringPool;
import com.regitiny.catiny.util.ApplicationContextUtil;
import com.regitiny.catiny.util.ReflectUtil;
import io.vavr.control.Option;
import lombok.extern.log4j.Log4j2;

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
public abstract class AdvanceService<E, S, Q, AM, AR, AS>
{
  private S localService;
  private Q localQueryService;
  private AM localAdvanceMapper;
  private AR localRepository;
  private AS localSearch;

  private Local local = null;
  private ProtectedLocal protectedLocal = null;
  private PublicLocal publicLocal = null;

  /**
   * entityNameFirstUpper
   *
   * @return String
   */
  private String entityNameFU()
  {
    return Option.of(getClass().getSimpleName()).filter(s -> s.contains("AdvanceServiceImpl"))
      .map(s -> s.replace("AdvanceServiceImpl", StringPool.BLANK)).getOrElseThrow(() -> new RuntimeException(""));
  }

  /**
   * entityNameFirstLower
   *
   * @return String
   */
  private String entityNameFL()
  {
    var entityNameFU = entityNameFU();
    var matcher = Pattern.compile("^[A-Z]+").matcher(entityNameFU);
    matcher.find();
    var firstUpper = matcher.group(0);
    return entityNameFU.replaceFirst("^[A-Z]+", firstUpper.toLowerCase());
  }

  Local local()
  {
    if (Objects.isNull(local))
      setup();
    return local;
  }

  protected ProtectedLocal protectedLocal()
  {
    if (Objects.isNull(protectedLocal))
      setup();
    return protectedLocal;
  }

  public PublicLocal publicLocal()
  {
    if (Objects.isNull(publicLocal))
      setup();
    return publicLocal;
  }

  @SuppressWarnings("unchecked")
  private void setup()
  {
    final var applicationContext = ApplicationContextUtil.getApplicationContext();
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

    localRepository = (AR) Option.when(Objects.isNull(localRepository), entityNameFL + "AdvanceRepository")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalAdvanceRepository");
        return null;
      });

    localSearch = (AS) Option.when(Objects.isNull(localSearch), entityNameFL + "AdvanceSearch")
      .map(applicationContext::getBean).getOrElse(() ->
      {
        log.debug("not found LocalAdvanceSearch");
        return null;
      });
    local = new Local();
    publicLocal = new PublicLocal();
    protectedLocal = new ProtectedLocal();
  }

  public E create(E entity)
  {
    //noinspection unchecked
    return (E) ReflectUtil.methodInvoke(local().advanceRepository, "save", entity).getOrNull();
  }


  class Local
  {
    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localRepository;
    public final AS advanceSearch = localSearch;
  }

//  public LocalPublic localPublic()
//  {
//    if (Objects.isNull(local))
//      setup();
//    return localPublic;
//  }

  protected class ProtectedLocal
  {
    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localRepository;
    public final AS advanceSearch = localSearch;
  }

  public class PublicLocal
  {
    public final S service = localService;
    public final Q queryService = localQueryService;
    public final AM advanceMapper = localAdvanceMapper;
    public final AR advanceRepository = localRepository;
    public final AS advanceSearch = localSearch;
  }
}
