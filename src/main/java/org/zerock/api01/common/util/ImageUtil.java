package org.zerock.api01.common.util;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.rolling.dto.RollingFileDTO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Component
public class ImageUtil {

    @Value("${basePath}")
    private String basePath;

    // 이미지 경로에 해당하는 이미지 조회
    public Resource readImage(String storedName) {
        String imagePath = basePath + "/" + storedName;
        try {
            FileSystemResource file = new FileSystemResource(imagePath);
            log.info("[Read] : {}", storedName);

            return file;
        } catch (NullPointerException npe) {
            throw new IllegalArgumentException("[존재하지 않는 파일]: " + imagePath);
        }
    }

    // 이미지 저장 후 저장 결과 반환
    public List<RollingFileDTO> saveImages(List<MultipartFile> files) {
        initFolder();

        List<RollingFileDTO> fileNames = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            try {
                RollingFileDTO rollingFileDTO = saveImage(file);
                fileNames.add(rollingFileDTO);
            } catch (IllegalArgumentException e) {
                log.info("[파일 저장 실패]: " + file.getOriginalFilename());
            }
        }
        return fileNames;
    }

    // 이미지 저장 후 저장 된 이름 반환
    private RollingFileDTO saveImage(MultipartFile file) {
        validImage(file);
        String storedName = generateStoredName(file);

        try {
            // 이미지 지정 위치에 저장
            String imagePath = basePath + "/" + storedName;
            FileSystemResource resource = new FileSystemResource(imagePath);
            resource.getOutputStream().write(file.getBytes());

            // 썸네일 생성
            Thumbnails.of(new File(imagePath))
                    .forceSize(160, 160)
                    .toFile(new File(basePath + "/s_" + storedName));
            log.info("[Save] : {} -> {}", file.getOriginalFilename(), storedName);

            return new RollingFileDTO(storedName, file.getOriginalFilename(), file.getSize());
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 저장 실패");
        }
    }

    private void initFolder() {
        File folder = new File(basePath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    private void validImage(MultipartFile file) {
        if (!isImage(file)) {
            throw new IllegalArgumentException("이미지 파일 아님");
        }
    }

    private boolean isImage(MultipartFile file) {
        String contentType = file.getContentType();

        return contentType != null && contentType.startsWith("image/");
    }

    private String generateStoredName(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String[] data = originalFilename.split("[.]");
        String extension = data[data.length - 1];
        String storedName = UUID.randomUUID() + "." + extension;

        return storedName;
    }
}
