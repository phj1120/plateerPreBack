package org.plateer.backlms.common.dto;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
public class PageReqDTO {

    private int page;
    private int size;

    public PageReqDTO(){
        this.page = 1;
        this.size = 10;
    }

    public void setPage(int page){
        this.page = page<= 0 ? 1: page;
    }


    public void setSize(int size){
        this.size = size <= 10 ? 10 : size >= 100 ? 50 : size;
    }

    public Pageable getPageable(Sort sort) {
        Pageable pageable = PageRequest.of(this.page-1, this.size, sort);

        return pageable;
    }


}
