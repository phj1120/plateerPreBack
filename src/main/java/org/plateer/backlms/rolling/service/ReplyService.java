package org.plateer.backlms.rolling.service;

import org.plateer.backlms.rolling.dto.ReplyListDTO;

public interface ReplyService {

    ReplyListDTO getReplyList(Long rollingId);
}
