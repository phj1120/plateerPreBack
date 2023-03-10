package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RollingFileDTO {

    private Long fno;               // 파일 번호

    private String fileName;        // UUID

    private String originalName;    // 파일 이름

    private Long fileSize;          // 파일 사이즈

    private Long rno;               // 롤링 번호

    public RollingFileDTO(String fileName, String originalName, Long fileSize) {
        this.fileName = fileName;
        this.originalName = originalName;
        this.fileSize = fileSize;
    }
}
