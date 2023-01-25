package org.plateer.backlms.rolling.service;

import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.dto.RollingSearchDTO;

import java.util.List;
import java.util.Map;

public interface RollingService {
    // 페이징 처리된 롤링페이퍼 리스트 서비스
    PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO);

    // 페이징 없이 전체 롤링페이퍼 리스트 서비스
    List<RollingDTO> getRollingAllList();

<<<<<<< HEAD
    Long addPaper(RollingDTO rollingDTO);
=======
    // 롤링페이퍼 검색 리스트 서비스
    PageResultDTO<RollingDTO> getSearchRollingList(PageReqDTO pageReqDTO, RollingSearchDTO rollingSearchDTO);
>>>>>>> 89367a0e31aadca67e214e0b4e2d2b96cd1a115c
}
