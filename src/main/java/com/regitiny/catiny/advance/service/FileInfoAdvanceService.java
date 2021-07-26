package com.regitiny.catiny.advance.service;

import com.regitiny.catiny.advance.repository.FileInfoAdvanceRepository;
import com.regitiny.catiny.advance.repository.search.FileInfoAdvanceSearch;
import com.regitiny.catiny.advance.service.mapper.FileInfoAdvanceMapper;
import com.regitiny.catiny.domain.FileInfo;
import com.regitiny.catiny.service.FileInfoQueryService;
import com.regitiny.catiny.service.FileInfoService;

/**
 * AdvanceService layer for {@link FileInfo}.
 *
 * @see FileInfoService is base service generate by jhipster
 */
public interface FileInfoAdvanceService extends BaseSrvice<FileInfo, FileInfoService, FileInfoQueryService, FileInfoAdvanceMapper, FileInfoAdvanceRepository, FileInfoAdvanceSearch>
{
}
