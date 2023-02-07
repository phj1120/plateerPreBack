package org.zerock.api01.rolling.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.common.image.ImageService;
import org.zerock.api01.common.image.dto.SaveResult;
import org.zerock.api01.rolling.dto.*;
import org.zerock.api01.rolling.mapper.RollingMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class RollingServiceImpl implements RollingService {
    private final RollingMapper rollingMapper;
    private final ImageService imageService;

    @Override
    public PageResultDTO<RollingInfoDTO> getList(RollingPageRequestDTO rollingPageRequestDTO) {
        int total = rollingMapper.getCount(rollingPageRequestDTO);
        List<RollingInfoDTO> dtoList = rollingMapper.getList(rollingPageRequestDTO).stream()
                .map(rollingDTO -> new RollingInfoDTO(rollingDTO))
                .collect(Collectors.toList());

        PageResultDTO<RollingInfoDTO> pageResponseDTO = PageResultDTO.<RollingInfoDTO>withAll()
                .dtoList(dtoList)
                .total(total)
                .pageRequestDTO(rollingPageRequestDTO)
                .build();

        return pageResponseDTO;
    }

    @Override
    public RollingWithImageNameDTO getRolling(Long id) {
        RollingDTO rollingDTO = rollingMapper.getRolling(id);
        if (rollingDTO == null) {
            throw new IllegalArgumentException("조회 실패");
        }
        Set<String> imagePaths = imageService.getImagePaths(rollingDTO.getRollingId());

        return new RollingWithImageNameDTO(rollingDTO, imagePaths);
    }

    // TODO 추가와 수정 중복코드 불어오는 메서드만 다른데 깔끔하게 처리할 방법 없나?
    // 추가
    @Override
    public RollingInfoDTO addRolling(AddRollingDTO addRollingDTO) {
        RollingDTO rollingDTO;
        // 이미지가 비어있는 경우 이미지 제외하고 요청 전송
        if (addRollingDTO.getImages().isEmpty()) {
            rollingDTO = addRollingDTO.convert();
            int count = rollingMapper.addRolling(rollingDTO);
            if (count != 1) {
                throw new IllegalArgumentException("추가 실패");
            }

            return new RollingInfoDTO(rollingDTO);
        }

        // 사진 저장
        List<MultipartFile> images = addRollingDTO.getImages();
        SaveResult saveResult = imageService.saveImages(images);

        // 썸네일 포함해 저장
        String thumbnailPath = "s_" + saveResult.getPath(addRollingDTO.getThumbnailIndex());
        rollingDTO = addRollingDTO.convert(thumbnailPath);
        int count = rollingMapper.addRolling(rollingDTO);
        if (count != 1) {
            throw new IllegalArgumentException("추가 실패");
        }

        // 사진 DB 에 게시글 외래키로 추가
        Set<String> paths = saveResult.getPaths();
        imageService.setRollingId(rollingDTO.getRollingId(), paths);

        return new RollingInfoDTO(rollingDTO);
    }

    // 수정
    @Override
    public RollingWithImageNameDTO modifyRolling(ModifyRollingDTO modifyRollingDTO) {
        // 기존 사진 삭제
        imageService.deleteImage(modifyRollingDTO.getId());

        // 사진이 없는 경우 사진 저장 하지 않고 바로 수정
        RollingDTO rollingDTO;
        if (modifyRollingDTO.getImages().isEmpty()) {
            rollingDTO = modifyRollingDTO.convert();
            int count = rollingMapper.modifyRolling(rollingDTO);
            if (count != 1) {
                throw new IllegalArgumentException("추가 실패");
            }

            return new RollingWithImageNameDTO(rollingDTO);
        }

        // 받아온 사진 저장
        List<MultipartFile> images = modifyRollingDTO.getImages();
        SaveResult saveResult = imageService.saveImages(images);

        // Rolling 에 썸네일 정보 추가
        String thumbnailPath = "s_" + saveResult.getPath(modifyRollingDTO.getThumbnailIndex());
        rollingDTO = modifyRollingDTO.convert(thumbnailPath);

        // Rolling 저장
        int count = rollingMapper.modifyRolling(rollingDTO);
        if (count != 1) {
            throw new IllegalArgumentException("추가 실패");
        }

        // 저장된 사진 외래키 업데이트
        Set<String> paths = saveResult.getPaths();
        imageService.setRollingId(rollingDTO.getRollingId(), paths);

        return new RollingWithImageNameDTO(rollingDTO, paths);
    }

    @Override
    public void deleteRolling(Long rollingId) {
        int count = rollingMapper.deleteRolling(rollingId);
        imageService.deleteImage(rollingId);

        if (count != 1) {
            throw new IllegalArgumentException("삭제 실패");
        }
    }
}
