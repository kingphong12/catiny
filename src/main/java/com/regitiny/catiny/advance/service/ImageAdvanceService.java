package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.ImageAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.ImageAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.ImageAdvanceMapper;
import com.regitiny.catiny.domain.Image;
import com.regitiny.catiny.service.ImageQueryService;
import com.regitiny.catiny.service.ImageService;

/**
 * AdvanceService layer for {@link Image}.
 *
 * @see ImageService is base service generate by jhipster
 */
public interface ImageAdvanceService extends BaseSrvice<Image, ImageService, ImageQueryService, ImageAdvanceMapper, ImageAdvanceRepository, ImageAdvanceSearch>
{
}
