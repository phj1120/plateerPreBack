package org.plateer.backlms.rolling.service;

import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {
    List<ReplyDTO> getReplyList(Long rollingId);
}
