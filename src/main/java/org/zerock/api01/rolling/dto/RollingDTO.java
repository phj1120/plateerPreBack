package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RollingDTO {
    private Long rollingId;
    private String title;
    private Long writerMemberId;
    private String target;
    private String imgSrc;
    private LocalDate createDt;
    private LocalDate updateDt;

}
