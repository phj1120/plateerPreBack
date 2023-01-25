package org.plateer.backlms.rolling.repository;

import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.repository.search.RollingSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RollingRepository extends JpaRepository<Rolling, Long>, RollingSearch {
}
