package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.AlbumAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.AlbumAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.AlbumAdvanceMapper;
import com.regitiny.catiny.domain.Album;
import com.regitiny.catiny.service.AlbumQueryService;
import com.regitiny.catiny.service.AlbumService;

/**
 * AdvanceService layer for {@link Album}.
 *
 * @see AlbumService is base service generate by jhipster
 */
public interface AlbumAdvanceService extends BaseSrvice<Album, AlbumService, AlbumQueryService, AlbumAdvanceMapper, AlbumAdvanceRepository, AlbumAdvanceSearch>
{
}
