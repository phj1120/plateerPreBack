package org.zerock.api01.common.image;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.api01.common.util.ImageUtil;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ImageController {

    private final ImageUtil imageUtil;

    // 이미지 조회
    @GetMapping(value = "/image/{imagePath}",
            produces = {MediaType.IMAGE_JPEG_VALUE})
    public Resource getImage(@PathVariable("imagePath") String imagePath) {
        Resource file = imageUtil.readImage(imagePath);

        return file;
    }
}
