package org.zerock.api01.rolling.mapper;

import org.zerock.api01.rolling.dto.RollingFileDTO;

import java.util.List;

public interface FileMapper {

    Long addFile(RollingFileDTO rollingFileDTO);

    List<RollingFileDTO> getRollingFileList(Long id);

    Long deleteImageByRollingId(Long id);
}
