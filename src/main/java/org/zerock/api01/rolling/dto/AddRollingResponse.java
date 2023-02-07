package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRollingResponse {
    private String title;
    private String target;
    private Long writer;
    private String thumbnailPath; // 썸네일 인덱스

    public AddRollingResponse(RollingInfoDTO rollingInfoDTO) {
        this.title = rollingInfoDTO.getTitle();
        this.target = rollingInfoDTO.getTarget();
        this.writer = rollingInfoDTO.getId();
        this.thumbnailPath = rollingInfoDTO.getImgSrc();
    }
}
