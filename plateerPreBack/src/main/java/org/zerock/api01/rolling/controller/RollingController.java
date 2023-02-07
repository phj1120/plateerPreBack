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
    public Long addRolling(RollingMultipartDTO rollingMultipartDTO) {

        List<RollingFileDTO> fileNames = imageUtil.saveImages(rollingMultipartDTO.getImages());

        RollingDTO rollingDTO = new RollingDTO(rollingMultipartDTO, fileNames);

        return rollingService.addRolling(rollingDTO);
    }

    @PutMapping("/{id}")
    public Long modifyRolling(@PathVariable Long id, RollingMultipartDTO rollingMultipartDTO) {

        RollingDTO rollingDTO = null;

        return rollingService.modifyRolling(rollingDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteRolling(@PathVariable Long id) {

        return rollingService.deleteRolling(id);
    }


}
