package org.zerock.api01.common.image.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDTO {
    private Long fno;               // 파일 번호
    private String originalName;    // 오리지널 파일명
    private String fileName;        // 파일명(UUID)
    private Long fileSize;          // 파일사이즈
    private LocalDate createDt;     // 생성일
    private LocalDate updateDt;     // 수정일
    private Long rno;               // 게시물 번호

    public FileDTO(MultipartFile file, String fileName) {
        this.originalName = file.getOriginalFilename();
        this.fileName = fileName;
        this.fileSize = file.getSize();
        this.createDt = LocalDate.now();
    }

}
