package org.zerock.api01.common.image.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class SaveImageDto {
    private final MultipartFile file;
    private final String encodedImage;

    public SaveImageDto(MultipartFile file, String encodedImage) {
        this.file = file;
        this.encodedImage = encodedImage;
    }
}
