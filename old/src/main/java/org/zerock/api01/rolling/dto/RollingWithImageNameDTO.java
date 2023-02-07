package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RollingWithImageNameDTO {
    private Long id;
    private String title;
    private String target;
    private Set<String> imgSrcs;
    private Long writer;
    private LocalDate createDt;
    private LocalDate updateDt;

    public RollingWithImageNameDTO(RollingDTO rollingDTO) {
        this.id = rollingDTO.getRollingId();
        this.title = rollingDTO.getTitle();
        this.target = rollingDTO.getTarget();
        this.writer = rollingDTO.getWriterMemberId();
        this.createDt = rollingDTO.getCreateDt();
        this.updateDt = rollingDTO.getUpdateDt();
    }

    public RollingWithImageNameDTO(RollingDTO rollingDTO, Set<String> imgSrcs) {
        this(rollingDTO);
        this.imgSrcs = imgSrcs;
    }
}
