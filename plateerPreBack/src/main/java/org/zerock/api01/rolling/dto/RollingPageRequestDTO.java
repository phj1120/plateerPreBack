package org.zerock.api01.rolling.dto;

import lombok.Data;
import org.zerock.api01.common.dto.PageRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class RollingPageRequestDTO extends PageRequestDTO {

    private String keyword;

    private List<String> types = new ArrayList<>();

    public RollingPageRequestDTO(int page, int size, String keyword, List<String> types) {
        super(page, size);
        this.keyword = keyword;
        this.types = types;
    }

}
