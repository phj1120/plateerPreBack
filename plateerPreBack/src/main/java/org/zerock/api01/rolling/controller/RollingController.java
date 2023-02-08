package org.zerock.api01.rolling.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.common.util.ImageUtil;
import org.zerock.api01.rolling.dto.RollingDTO;
import org.zerock.api01.rolling.dto.RollingFileDTO;
import org.zerock.api01.rolling.dto.RollingMultipartDTO;
import org.zerock.api01.rolling.dto.RollingPageRequestDTO;
import org.zerock.api01.rolling.service.RollingService;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/rollings")
public class RollingController {

    private final RollingService rollingService;

    private final ImageUtil imageUtil;

    @GetMapping("/list")
    public PageResultDTO<RollingDTO> getRollingList(RollingPageRequestDTO rollingPageRequestDTO) {

        log.info(rollingPageRequestDTO);
        return rollingService.getRollingList(rollingPageRequestDTO);
    }

    @GetMapping("/{id}")
    public RollingDTO getRolling(@PathVariable("id") Long id) {
        return rollingService.getRolling(id);
    }

    @PostMapping
    public Map<String,Long> addRolling(RollingMultipartDTO rollingMultipartDTO) {

        List<RollingFileDTO> fileNames = imageUtil.saveImages(rollingMultipartDTO.getImages());

        RollingDTO rollingDTO = new RollingDTO(rollingMultipartDTO, fileNames);

        Long result =  rollingService.addRolling(rollingDTO);

        return Map.of("result",result);
    }

    @PutMapping("/{id}")
    public Map<String, Long> modifyRolling(@PathVariable Long id, RollingMultipartDTO rollingMultipartDTO) {

        // 이전 파일 삭제
        rollingService.deleteFile(id);

        // 새로운 파일 저장
        List<RollingFileDTO> fileNames = imageUtil.saveImages(rollingMultipartDTO.getImages());

        RollingDTO rollingDTO = new RollingDTO(rollingMultipartDTO, fileNames);

        // id 추가
        rollingDTO.setRollingId(id);

        Long result =  rollingService.modifyRolling(rollingDTO);

        return Map.of("result", result);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteRolling(@PathVariable Long id) {

        Long result = rollingService.deleteRolling(id);

        return Map.of("result", result);
    }


}
