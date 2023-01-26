package org.plateer.backlms.rolling.service;

import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.dto.ReplyDTO;
import org.plateer.backlms.rolling.dto.SaveReplyRequestDTO;
import org.plateer.backlms.rolling.dto.UpdateReplyDTO;

import java.util.List;

public interface ReplyService {
    List<ReplyDTO> getReplyList(Long rollingId);

    /*
      2023.01.26 주호승 / 댓글 저장 service
   */
    void saveReply(SaveReplyRequestDTO saveReplyRequestDTO);
    
    /*
      2023.01.26 주호승 / 댓글 삭제 service
   */
    void deleteReply(long id);
    
    /*
      2023.01.26 주호승 / 댓글 수정 service
   */
    ReplyDTO updateReply(long id, UpdateReplyDTO updateReplyDTO);
}
