package org.zerock.api01.rolling.mapper;

import org.zerock.api01.rolling.dto.RollingFileDTO;

import java.util.List;
import java.util.Set;

public interface FileMapper {

    Long addFile(RollingFileDTO rollingFileDTO);

    List<RollingFileDTO> getRollingFileList(Long id);

    Long deleteImageByRollingId(Long id);

    Set<String> getAllFileNames();
}
