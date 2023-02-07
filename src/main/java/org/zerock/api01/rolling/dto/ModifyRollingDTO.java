package org.zerock.api01.rolling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyRollingDTO {
    private Long id;
    private String title;
    private String target;
    private List<MultipartFile> images;
    private LocalDate updateDt;
    private Integer thumbnailIndex; // 썸네일 인덱스

    public ModifyRollingDTO(Long id, ModifyRollingRequest modifyRollingRequest) {
        this.id = id;
        this.title = modifyRollingRequest.getTitle();
        this.target = modifyRollingRequest.getTarget();
        this.images = modifyRollingRequest.getImages();
        this.thumbnailIndex = modifyRollingRequest.getThumbnailIndex();
        this.updateDt = LocalDate.now();
    }

    public RollingDTO convert() {
        return RollingDTO.builder()
                .rollingId(this.id)
                .title(this.title)
                .target(this.target)
                .updateDt(this.updateDt)
                .build();
    }

    public RollingDTO convert(String thumbnailPath) {
        RollingDTO rollingDTO = this.convert();
        rollingDTO.setImgSrc(thumbnailPath);

        return rollingDTO;
    }
}
