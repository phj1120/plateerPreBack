package org.plateer.backlms.rolling.repository;

import org.plateer.backlms.rolling.domain.Reply;
import org.plateer.backlms.rolling.domain.Rolling;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findAllByRolling(Rolling rolling);

}
