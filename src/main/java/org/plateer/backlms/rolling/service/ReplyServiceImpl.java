package org.plateer.backlms.rolling.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.ReplyDTO;
import org.plateer.backlms.rolling.dto.ReplyListDTO;
import org.plateer.backlms.rolling.repository.ReplyRepository;
import org.plateer.backlms.rolling.repository.RollingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final RollingRepository rollingRepository;
    private final ModelMapper modelMapper;

    @Override
    public ReplyListDTO getReplyList(Long rollingId) {
        Rolling rolling = rollingRepository.findById(rollingId).orElseThrow();
        String target = rolling.getTarget();
        List<Reply> replyList = replyRepository.findAllByRolling(rolling);
        List<ReplyDTO> replyDTOList = replyList.stream()
//                .map((reply -> new ReplyDTO(reply)))
                .map((reply -> modelMapper.map(reply, ReplyDTO.class)))
                .collect(Collectors.toList());

        return new ReplyListDTO(target, replyDTOList);
    }
}
