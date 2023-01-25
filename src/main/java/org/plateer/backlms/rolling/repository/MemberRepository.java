package org.plateer.backlms.rolling.repository;

import org.plateer.backlms.rolling.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
