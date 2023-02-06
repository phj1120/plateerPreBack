package org.zerock.api01.common.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.image.dto.FileDTO;
import org.zerock.api01.common.image.dto.SaveResult;
import org.zerock.api01.common.image.mapper.FileMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final FileMapper fileMapper;

    @Value("${basePath}")
    private String basePath;


    // Rolling ID에 해당하는 이미지 Path 조회
    public Set<String> getImagePaths(Long rollingId) {
        return fileMapper.getImagePaths(rollingId);
    }

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

    // 해당하는 사진에 rolling id 저장
    public void setRollingId(Long id, Set<String> names) {
        fileMapper.setRollingId(id, names);
    }

    // Rolling Id 에 해당하는 사진 DB 에서 삭제
    public void deleteImage(Long rollingId) {
        fileMapper.deleteImageByRollingId(rollingId);
    }

    // 이미지 저장 후 저장 결과 반환
    public SaveResult saveImages(List<MultipartFile> files) {
        initFolder();

        SaveResult saveResult = new SaveResult();
        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            try {
                String savedPath = saveImage(file);
                saveResult.success(i, savedPath);
            } catch (IllegalArgumentException e) {
                saveResult.fail(i);
            }
        }

        return saveResult;
    }

    // 저장 된 모든 파일 이름 조회
    public Set<String> getAllFileNames() {
        return fileMapper.getAllFileNames();
    }

    // 이미지 저장 후 저장 된 이름 반환
    private String saveImage(MultipartFile file) {
        validImage(file);

        String storedName = generateStoredName(file);
        FileDTO fileDTO = new FileDTO(file, storedName);
        fileMapper.addFile(fileDTO);
        try {
            String imagePath = basePath + "/" + storedName;

            FileSystemResource resource = new FileSystemResource(imagePath);
            resource.getOutputStream().write(file.getBytes());

            Thumbnails.of(new File(imagePath))
                    .forceSize(160, 160)
                    .toFile(new File(basePath + "/s_" + storedName));
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 저장 실패");
        }

        log.info("[Save] : {} -> {}", file.getOriginalFilename(), storedName);

        return storedName;
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
