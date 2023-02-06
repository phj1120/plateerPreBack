package org.zerock.api01.rolling.dto;

import lombok.*;

import java.time.LocalDate;

//@Data
@Getter
@ToString
//@NoArgsConstructor
//@AllArgsConstructor
public class RollingsWithFileDTO {
    private Long id;
    private String target;
    private String title;
    private LocalDate createDt;
    private LocalDate updateDt;
    private Long writer;
    private String thumbnail;
}
