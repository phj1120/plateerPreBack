package org.plateer.backlms.rolling.repository.search;

import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.dto.RollingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RollingSearch {
    Page<RollingDTO> searchList(Pageable pageable, RollingSearchDTO rollingSearchDTO);
}
