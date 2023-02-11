package org.zerock.api01.rolling.service;

import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.rolling.dto.RollingDTO;
import org.zerock.api01.rolling.dto.RollingPageRequestDTO;

import java.util.Set;

public interface RollingService {

    PageResultDTO<RollingDTO> getRollingList(RollingPageRequestDTO rollingPageRequestDTO);

    RollingDTO getRolling(Long id);

    Long addRolling(RollingDTO rollingDTO);

    Long modifyRolling(RollingDTO rollingDTO);

    Long deleteRolling(Long id);

    Long deleteFile(Long rollingId);

    Set<String> getAllFileNames();
}
