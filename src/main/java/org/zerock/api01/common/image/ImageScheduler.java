package org.zerock.api01.common.image;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.rolling.service.RollingService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Log4j2
@Component
@Transactional
@RequiredArgsConstructor
public class ImageScheduler {

    @Value("${basePath}")
    private String basePath;

    private final RollingService rollingService;

    // 데이터 베이스와 파일 시스템의 파일 목록 비교해
    // 데이터 베이스에는 있지만 파일 시스템에는 없는 이미지 삭제
    @Scheduled(cron = "0 0 4 * * *")
    public void deleteImages() {
        // DB 에 있는 이미지 목록 조회
        Set<String> fileNamesInDb = rollingService.getAllFileNames();

        // 이미지 목록 조회
        File dir = new File(basePath);
        String[] fileNamesInFileSystem = dir.list();

        List<String> deletedImages = new ArrayList<>();
        for (String fileName : fileNamesInFileSystem) {
            // 썸네일과 원본 이미지의 공통된 UUID 값
            String fileKey = fileName;

            // 썸네일의 경우 썸네일 prefix 제거
            if (fileKey.startsWith("s_")) {
                fileKey = fileKey.substring(2);
            }

            // 현재 파일이 DB 에 존재할 경우 다음 파일 확인
            if (fileNamesInDb.contains(fileKey)) {
                continue;
            }

            try {
                File deleteFile = new File(basePath + "/" + fileName);
                deleteFile.delete();
                deletedImages.add(fileName);
            } catch (NullPointerException npe) {
                log.info("[존재하지 않는 파일]: " + fileName);
            }
        }

        log.info("[삭제된 이미지]: {} 개", deletedImages.size());
        log.info(deletedImages);
    }
}
