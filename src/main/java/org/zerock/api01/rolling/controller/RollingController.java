package org.zerock.api01.rolling.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import org.zerock.api01.common.dto.PageResultDTO;
import org.zerock.api01.common.dto.ResultDTO;
import org.zerock.api01.rolling.dto.*;
import org.zerock.api01.rolling.service.RollingService;

// TODO Request, 계층간의 데이터 전송을 위한 DTO, Response 어떻게 나누는 것이 좋은지
//  지금은 나누면 좋겠지 라는 생각으로 대부분 나눴는데,
//  변환도 잦고, 비슷한 DTO 가 많아 너무 복잡함.
@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/rollings")
public class RollingController {
    private final RollingService rollingService;

    // 목록 조회
    @GetMapping("list")
    public PageResultDTO<RollingInfoDTO> getRollingList(RollingPageRequestDTO rollingPageRequestDTO) {
        return rollingService.getList(rollingPageRequestDTO);
    }

    // 단건 조회
    @GetMapping("{id}")
    public ResultDTO<RollingWithImageNameDTO> getRolling(@PathVariable("id") Long id) {
        RollingWithImageNameDTO rollingWithImageNameDTO = rollingService.getRolling(id);

        return ResultDTO.<RollingWithImageNameDTO>builder()
                .data(rollingWithImageNameDTO)
                .build();
    }

    // 추가
    @PostMapping
    public ResultDTO<AddRollingResponse> addRolling(@ModelAttribute AddRollingRequest request) {
        AddRollingDTO addRollingDTO = new AddRollingDTO(request);
        RollingInfoDTO rollingInfoDTO = rollingService.addRolling(addRollingDTO);

        return ResultDTO.<AddRollingResponse>builder()
                .data(new AddRollingResponse(rollingInfoDTO))
                .build();
    }

    // 수정
    @PutMapping("{rollingId}")
    public ResultDTO<RollingWithImageNameDTO> modifyRolling(@PathVariable("rollingId") Long id,
                                                            @ModelAttribute ModifyRollingRequest request) {
        ModifyRollingDTO modifyRollingDTO = new ModifyRollingDTO(id, request);
        RollingWithImageNameDTO rollingWithImageNameDTO = rollingService.modifyRolling(modifyRollingDTO);

        return ResultDTO.<RollingWithImageNameDTO>builder()
                .data(rollingWithImageNameDTO)
                .build();
    }

    // 삭제
    @DeleteMapping("{rollingId}")
    public ResultDTO<Long> deleteRolling(@PathVariable("rollingId") Long id) {
        rollingService.deleteRolling(id);

        return ResultDTO.<Long>builder()
                .data(id)
                .build();
    }
}
