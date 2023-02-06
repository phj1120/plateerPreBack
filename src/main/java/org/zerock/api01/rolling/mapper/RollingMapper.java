package org.zerock.api01.rolling.mapper;

import org.zerock.api01.rolling.dto.RollingDTO;
import org.zerock.api01.rolling.dto.RollingPageRequestDTO;
import org.zerock.api01.rolling.dto.RollingsWithFileDTO;

import java.util.List;

public interface RollingMapper {
    int getCount(RollingPageRequestDTO rollingPageRequestDTO);

    List<RollingDTO> getList(RollingPageRequestDTO rollingPageRequestDTO);
    List<RollingsWithFileDTO> getRollingsWithFile(RollingPageRequestDTO rollingPageRequestDTO);

    RollingDTO getRolling(Long id);

    int addRolling(RollingDTO rollingDTO);

    int modifyRolling(RollingDTO rollingDTO);

    int deleteRolling(Long rollingId);
}
