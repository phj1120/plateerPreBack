package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RollingMultipartDTO {

    private String title;           // 제목

    private String target;          // 타겟

    private Integer thumbnailIndex; // 썸네일 번호

    private List<MultipartFile> images = new ArrayList<>();  // 이미지

}
