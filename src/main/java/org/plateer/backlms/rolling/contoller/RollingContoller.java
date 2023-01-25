package org.plateer.backlms.rolling.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.service.RollingService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rolling/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class RollingContoller {
    private final RollingService rollingService;
    
    @GetMapping("getRollingAllList")
    public List<RollingDTO> getRollingAllList() {
        return rollingService.getRollingAllList();
    }

    @GetMapping("getRollingList")
    public PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO) {
        return rollingService.getRollingList(pageReqDTO);
    }
}
