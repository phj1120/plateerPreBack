package org.zerock.api01.common.image.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
public class SaveImagesDto {
    private final List<MultipartFile> files;

    public SaveImagesDto(List<MultipartFile> files) {
        this.files = files;
    }
}
