package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// RollingDTO 를 바로 반환시
// 테이블의 모든 데이터가 반환 되고,
// 테이블의 이름대로 반환 되기 떄문에
// 원하는 이름으로 원하는 정보만 반환하기 위해
// RollingInfoDTO 를 추가로 만들었음.
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RollingInfoDTO {
    private Long id;
    private String title;
    private String target;
    private String imgSrc;
    private Long writer;
    private LocalDate createDt;
    private LocalDate updateDt;

    public RollingInfoDTO(RollingDTO rollingDTO) {
        this.id = rollingDTO.getRollingId();
        this.title = rollingDTO.getTitle();
        this.target = rollingDTO.getTarget();
        this.writer = rollingDTO.getWriterMemberId();
        this.createDt = rollingDTO.getCreateDt();
        this.updateDt = rollingDTO.getUpdateDt();
        this.imgSrc = rollingDTO.getImgSrc();
        // 이미지 기본 경로 추가
        if (rollingDTO.getImgSrc() == null) {
            this.imgSrc = "s_0e1249ad-38c1-4fd9-a4f5-7360cb4464d4.jpg";
        }
    }
}
