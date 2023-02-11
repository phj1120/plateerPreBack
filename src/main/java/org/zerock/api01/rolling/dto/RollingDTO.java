package org.zerock.api01.rolling.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RollingDTO {

    @JsonProperty("id")
    private Long rollingId;     // 롤링 번호

    private String title;       // 제목

    private String target;      // 타겟

    private Long writerMemberId;        // 작성자

    private LocalDate createDt; // 생성일

    private LocalDate updateDt; // 수정일

    private String imgSrc;      // 썸네일 주소

    private List<RollingFileDTO> files = new ArrayList<>();  // 파일

    public RollingDTO(RollingMultipartDTO rollingMultipartDTO, List<RollingFileDTO> rollingFileDTOList) {
        this.title = rollingMultipartDTO.getTitle();
        this.target = rollingMultipartDTO.getTarget();
        if (!rollingFileDTOList.isEmpty()) {
            this.imgSrc = "s_" + rollingFileDTOList.get(rollingMultipartDTO.getThumbnailIndex()).getFileName();
        }
        this.files = rollingFileDTOList;
    }

    public RollingDTO(RollingMultipartDTO rollingMultipartDTO, List<RollingFileDTO> rollingFileDTOList, Long rollingId) {
        this(rollingMultipartDTO, rollingFileDTOList);
        this.rollingId = rollingId;
    }
}
