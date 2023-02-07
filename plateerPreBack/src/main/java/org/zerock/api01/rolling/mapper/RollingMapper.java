package org.zerock.api01.rolling.mapper;

import org.zerock.api01.rolling.dto.RollingDTO;
import org.zerock.api01.rolling.dto.RollingPageRequestDTO;

import java.util.List;

public interface RollingMapper {

    List<RollingDTO> getList(RollingPageRequestDTO rollingPageRequestDTO);

    int getCount(RollingPageRequestDTO rollingPageRequestDTO);

    RollingDTO getRolling(Long id);

    Long addRolling(RollingDTO rollingDTO);

    Long modifyRolling(RollingDTO rollingDTO);

    Long deleteRolling(Long id);

}
