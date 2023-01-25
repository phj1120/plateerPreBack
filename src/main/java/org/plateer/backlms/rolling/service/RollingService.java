package org.plateer.backlms.rolling.service;

import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;

import java.util.List;
import java.util.Map;

public interface RollingService {
    PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO);

    List<RollingDTO> getRollingAllList();

    Long addPaper(RollingDTO rollingDTO);
}
