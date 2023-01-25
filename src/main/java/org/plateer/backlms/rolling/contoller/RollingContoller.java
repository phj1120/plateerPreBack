package org.plateer.backlms.rolling.contoller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.dto.ReplyDTO;
import org.plateer.backlms.rolling.dto.ReplyListDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.dto.RollingSearchDTO;
import org.plateer.backlms.rolling.service.ReplyService;
import org.plateer.backlms.rolling.service.RollingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rolling/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class RollingContoller {
    private final RollingService rollingService;
    private final ReplyService replyService;


    /*
        2022.01.24 정승현 / 페이징 없이 모든 롤링페이퍼 호출 ( Home 메뉴에 사용 )
     */
    @GetMapping("getRollingAllList")
    public List<RollingDTO> getRollingAllList() {
        return rollingService.getRollingAllList();
    }


    /*
        2022.01.24 정승현 / 페이징으로 롤링페이퍼 호출 ( Rollingpaper List 메뉴에 사용 )
     */
    @GetMapping("getRollingList")
    public PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO) {
        return rollingService.getRollingList(pageReqDTO);
    }


    /*
        2022.01.25 정승현 / 검색으로 롤링페이퍼 호출 ( Rollingpaper List 메뉴에서 사용 )
     */
    @GetMapping("getSearchRollingList")
    public PageResultDTO<RollingDTO> getSearchRollingList(PageReqDTO pageReqDTO, RollingSearchDTO rollingSearchDTO) {
        return rollingService.getSearchRollingList(pageReqDTO, rollingSearchDTO);
    }


    /*
     2022.01.24 박현준 / 페이징으로 롤링페이퍼 호출 ( Rollingpaper List 메뉴에 사용 )
  */
    @GetMapping("{id}")
    public ReplyListDTO getReplyList(@PathVariable("id") Long id) {
        RollingDTO rolling = rollingService.getRolling(id);
        String target = rolling.getTarget();
        List<ReplyDTO> replyList = replyService.getReplyList(id);

        return new ReplyListDTO(target, replyList);
    }

}
