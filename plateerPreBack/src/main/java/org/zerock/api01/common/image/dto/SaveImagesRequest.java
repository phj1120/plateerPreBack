package org.zerock.api01.common.image.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SaveImagesRequest {
    private List<MultipartFile> files;

    public SaveImagesDto convert() {
        return new SaveImagesDto(this.files);
    }

}
