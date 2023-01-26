package org.plateer.backlms.rolling.contoller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.plateer.backlms.rolling.dto.SaveReplyRequestDTO;
import org.plateer.backlms.rolling.repository.ReplyRepository;
import org.plateer.backlms.rolling.service.ReplyService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Log4j2
public class ReplyController {
    private final ReplyService replyService;

    /*
      2023.01.26 주호승 / 롤링페이퍼 댓글 저장
   */
    @PostMapping
    public ResponseEntity saveReply(@RequestBody @Validated SaveReplyRequestDTO saveReplyRequestDTO) {
        replyService.saveReply(saveReplyRequestDTO);

        return ResponseEntity.ok("백앤드완료");
    }

}
