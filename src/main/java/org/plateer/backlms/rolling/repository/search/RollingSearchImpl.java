package org.plateer.backlms.rolling.repository.search;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.plateer.backlms.rolling.domain.QRolling;
import org.plateer.backlms.rolling.domain.Rolling;
import org.plateer.backlms.rolling.dto.RollingDTO;
import org.plateer.backlms.rolling.dto.RollingSearchDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

@Log4j2
public class RollingSearchImpl extends QuerydslRepositorySupport implements RollingSearch {
    public RollingSearchImpl() {
        super(Rolling.class);
    }

    @Override
    public Page<RollingDTO> searchList(Pageable pageable, RollingSearchDTO searchDTO) {
        QRolling rolling = QRolling.rolling;

        JPQLQuery<Rolling> query = from(rolling);

        BooleanBuilder builder = new BooleanBuilder();

        String keyword = searchDTO.getSearchValue();
        if (!keyword.isEmpty() && keyword != null) {
            String type = searchDTO.getSearchType();
            if (type.equals("Title")) {
                builder.and(rolling.title.contains(keyword));
            }

            if (type.equals("Name")) {
                builder.and(rolling.target.contains(keyword));
            }
        }

        query.where(builder);
        query.orderBy(rolling.id.asc());
        getQuerydsl().applyPagination(pageable, query);

        JPQLQuery<RollingDTO> projectJPQLQuery = query.select(Projections.bean(
                RollingDTO.class,
                rolling.id,
                rolling.title,
                rolling.writer,
                rolling.target,
                rolling.imgSrc,
                rolling.createDt,
                rolling.updateDt
        ));

        List<RollingDTO> dtoList = projectJPQLQuery.fetch();
        long count = projectJPQLQuery.fetchCount();

        return new PageImpl<>(dtoList, pageable, count);
    }
}