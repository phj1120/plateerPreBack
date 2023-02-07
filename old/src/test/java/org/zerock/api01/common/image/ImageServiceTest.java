package org.zerock.api01.common.image;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.image.dto.SaveResult;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

@Transactional
@SpringBootTest
class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @Test
    @Rollback(value = true)
    void saveImage() throws IOException {
        MockMultipartFile file = new MockMultipartFile("image",
                "test.png",
                "image/png",
                new FileInputStream("images/7e84b9ef-264c-4235-a79e-226da77904d0.jpg"));
        ArrayList<MultipartFile> files = new ArrayList<>();
        files.add(file);
        SaveResult saveResult = imageService.saveImages(files);

        System.out.println(saveResult);
    }

    @Test
    void getAllFileNames() {
        Set<String> allFileNames = imageService.getAllFileNames();

        System.out.println(allFileNames);
    }

    @Test
    void getImagePaths() {
        long rollingId = 10L;
        Set<String> imagePaths = imageService.getImagePaths(rollingId);

        System.out.println(imagePaths);
    }
}