package org.plateer.backlms.rolling.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.plateer.backlms.rolling.domain.Member;
import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.ReplyDTO;
import org.plateer.backlms.rolling.dto.SaveReplyRequestDTO;
import org.plateer.backlms.rolling.dto.UpdateReplyDTO;
import org.plateer.backlms.rolling.repository.MemberRepository;
import org.plateer.backlms.rolling.repository.ReplyRepository;
import org.plateer.backlms.rolling.repository.RollingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final RollingRepository rollingRepository;
    private final MemberRepository memberRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<ReplyDTO> getReplyList(Long rollingId) {
        Rolling rolling = rollingRepository.findById(rollingId).orElseThrow();
        List<Reply> replyList = replyRepository.findAllByRolling(rolling);
        List<ReplyDTO> replyDTOList = replyList.stream()
                .map((reply -> new ReplyDTO(reply)))
                .collect(Collectors.toList());

        return replyDTOList;
    }

    /*
     2023.01.26 주호승 / 댓글 저장 service
    */
    public void saveReply(SaveReplyRequestDTO saveReplyRequestDTO){
        long rollingId = saveReplyRequestDTO.getRollingId();
        Rolling rolling = rollingRepository.findById(rollingId).orElseThrow();
        long writerId = saveReplyRequestDTO.getWriterId();
        Member writer = memberRepository.findById(writerId).orElseThrow();

        Reply reply = new Reply(saveReplyRequestDTO.getTitle(), saveReplyRequestDTO.getContent(), writer, rolling);

        replyRepository.save(reply);

    }
    
    /*
     2023.01.26 주호승 / 댓글 삭제 service
    */
    @Override
    public void deleteReply(long id) {
        replyRepository.deleteById(id);
    }

    /*
     2023.01.26 주호승 / 댓글 수정 service
    */
    @Override
    public ReplyDTO updateReply(long id, UpdateReplyDTO updateReplyDTO) {
        Reply reply =  replyRepository.findById(id).orElseThrow();



        return new ReplyDTO(reply.updateReply(updateReplyDTO));
    }


}
