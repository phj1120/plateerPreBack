package org.plateer.backlms.rolling.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SaveReplyRequestDTO {

    /*
      2023.01.26 주호승 / 댓글 저장 시 사용하는 DTO
   */
    private String title;

    private String content;

    private long rollingId;
    private long writerId;
}
