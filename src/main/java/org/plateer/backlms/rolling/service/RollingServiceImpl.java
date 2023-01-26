package org.plateer.backlms.rolling.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.plateer.backlms.common.dto.PageReqDTO;
import org.plateer.backlms.common.dto.PageResultDTO;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.ReplyDTO;
import org.plateer.backlms.rolling.dto.ReplyListDTO;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.dto.RollingSearchDTO;
import org.plateer.backlms.rolling.repository.RollingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
@Service
public class RollingServiceImpl implements RollingService {
    private final ModelMapper modelMapper;
    private final RollingRepository rollingRepository;

    @Override
    public PageResultDTO<RollingDTO> getRollingList(PageReqDTO pageReqDTO) {
        Pageable pageable = pageReqDTO.getPageable(Sort.by("id").descending());
        // querydsl 로
        Page<Rolling> result = rollingRepository.findAll(pageable);

        List<RollingDTO> list = result.stream().map(arr -> {
            RollingDTO rollingDTO = modelMapper.map(arr, RollingDTO.class);
            return rollingDTO;
        }).collect(Collectors.toList());

        PageResultDTO<RollingDTO> pageResultDTO =
                new PageResultDTO<>(list, pageable, result.getTotalElements(), result.getTotalPages());

        return pageResultDTO;
    }

    @Override
    public List<RollingDTO> getRollingAllList() {
        List<Rolling> rolling = rollingRepository.findAll();
        List<RollingDTO> list = rolling.stream().map(arr -> {
            RollingDTO rollingDTO = modelMapper.map(arr, RollingDTO.class);
            return rollingDTO;
        }).collect(Collectors.toList());

        return list;
    }

    @Override
    public Long addPaper(RollingDTO rollingDTO) {

        Rolling rolling = modelMapper.map(rollingDTO, Rolling.class);

        Long id = rollingRepository.save(rolling).getId();

        return id;
    }

    public PageResultDTO<RollingDTO> getSearchRollingList(PageReqDTO pageReqDTO, RollingSearchDTO rollingSearchDTO) {
        Pageable pageable = pageReqDTO.getPageable(Sort.by("id").descending());
        Page<RollingDTO> result = rollingRepository.searchList(pageable, rollingSearchDTO);

        List<RollingDTO> list = result.stream().map(arr -> {
            RollingDTO rollingDTO = modelMapper.map(arr, RollingDTO.class);
            return rollingDTO;
        }).collect(Collectors.toList());


        PageResultDTO<RollingDTO> pageResultDTO =
                new PageResultDTO<>(list, pageable, result.getTotalElements(), result.getTotalPages());

        return pageResultDTO;
    }

    @Override
    public RollingDTO getRolling(Long id) {
        Rolling rolling = rollingRepository.findById(id).orElseThrow();

        return RollingDTO.builder()
                .id(rolling.getId())
                .title(rolling.getTitle())
                .writer(rolling.getWriter())
                .target(rolling.getTarget())
                .imgSrc(rolling.getImgSrc())
                .build();
    }

}
