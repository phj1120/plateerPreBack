package org.plateer.backlms.rolling.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.service.RollingService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        log.info(rollingService.getRollingAllList());

        return rollingService.getRollingAllList();
    }

    @GetMapping("getRollingList")
    public PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO) {
        return rollingService.getRollingList(pageReqDTO);
    }

    //    01.25 이수영 롤링페이퍼 추가
    @PostMapping("postRollingPaper")
    public Long addPost(@Validated @RequestBody RollingDTO rollingDTO) {
        log.info("========== postRollingPaper ==========");

        log.info(rollingDTO);

        return rollingService.addPaper(rollingDTO);
    }
}
