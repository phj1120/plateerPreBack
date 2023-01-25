package org.plateer.backlms.rolling.service;

import lombok.RequiredArgsConstructor;
import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.ReplyDTO;
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

    @Override
    public List<ReplyDTO> getReplyList(Long rollingId) {
        Rolling rolling = rollingRepository.findById(rollingId).orElseThrow();
        List<Reply> replyList = replyRepository.findAllByRolling(rolling);
        List<ReplyDTO> replyDTOList = replyList.stream()
                .map((reply -> new ReplyDTO(reply)))
                .collect(Collectors.toList());

        return replyDTOList;
    }
}
