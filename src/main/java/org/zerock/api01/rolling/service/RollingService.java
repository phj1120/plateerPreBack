package org.zerock.api01.rolling.service;

import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.rolling.dto.*;

public interface RollingService {
    PageResultDTO<RollingInfoDTO> getList(RollingPageRequestDTO rollingPageRequestDTO);

    RollingWithImageNameDTO getRolling(Long id);

    RollingInfoDTO addRolling(AddRollingDTO addRollingDTO);

    RollingWithImageNameDTO modifyRolling(ModifyRollingDTO modifyRollingDTO);

    void deleteRolling(Long id);
}
