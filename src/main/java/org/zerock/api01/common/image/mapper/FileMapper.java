package org.zerock.api01.common.image.mapper;

import org.zerock.api01.common.image.dto.FileDTO;

import java.util.Set;

public interface FileMapper {
    int addFile(FileDTO fileDTO);

    int setRollingId(Long id, Set<String> names);

    Set<String> getImagePaths(Long id);

    int deleteImageByRollingId(Long rollingId);

    Set<String> getAllFileNames();
}
