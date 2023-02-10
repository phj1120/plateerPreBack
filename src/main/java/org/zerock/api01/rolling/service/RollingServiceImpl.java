package org.zerock.api01.rolling.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.rolling.dto.RollingDTO;
import org.zerock.api01.rolling.dto.RollingFileDTO;
import org.zerock.api01.rolling.dto.RollingPageRequestDTO;
import org.zerock.api01.rolling.mapper.FileMapper;
import org.zerock.api01.rolling.mapper.RollingMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log4j2
public class RollingServiceImpl implements RollingService {

    private final RollingMapper rollingMapper;

    private final FileMapper fileMapper;

    @Override
    public PageResultDTO<RollingDTO> getRollingList(RollingPageRequestDTO rollingPageRequestDTO) {

        int count = rollingMapper.getCount(rollingPageRequestDTO);

        List<RollingDTO> dtoList = rollingMapper.getList(rollingPageRequestDTO);

        return PageResultDTO.<RollingDTO>withAll().dtoList(dtoList).total(count).pageRequestDTO(rollingPageRequestDTO).build();
    }

    @Override
    public RollingDTO getRolling(Long id) {
        RollingDTO rolling = rollingMapper.getRolling(id);
        List<RollingFileDTO> rollingFileList = fileMapper.getRollingFileList(id);

        rolling.setFiles(rollingFileList);

        return rolling;
    }

    @Override
    public Long addRolling(RollingDTO rollingDTO) {
        // 롤링 저장
        rollingMapper.addRolling(rollingDTO);
        log.info("====================================");
        log.info("====================================");
        log.info("====================================");
        log.info(rollingDTO);
        Long rollingId = rollingDTO.getRollingId();
        log.info(rollingId);
        // 파일 저장
        rollingDTO.getFiles().stream().forEach(rollingFileDTO -> {
            rollingFileDTO.setRno(rollingId);
            fileMapper.addFile(rollingFileDTO);
        });

        return rollingId;
    }

    @Override
    public Long modifyRolling(RollingDTO rollingDTO) {

        // 롤링 DB 저장
        rollingMapper.modifyRolling(rollingDTO);

        Long rollingId = rollingDTO.getRollingId();

        // 파일 저장
        rollingDTO.getFiles().stream().forEach(rollingFileDTO -> {
            rollingFileDTO.setRno(rollingId);
            fileMapper.addFile(rollingFileDTO);
        });

        return rollingId;
    }

    public Long deleteFile(Long rollingId) {

        return fileMapper.deleteImageByRollingId(rollingId);
    }

    @Override
    public Long deleteRolling(Long id) {
        deleteFile(id);

        return rollingMapper.deleteRolling(id);
    }
}
