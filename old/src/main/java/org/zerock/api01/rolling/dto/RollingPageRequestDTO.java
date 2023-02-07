package org.zerock.api01.rolling.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.zerock.api01.common.dto.PageRequestDTO;

@Data
@NoArgsConstructor
public class RollingPageRequestDTO extends PageRequestDTO {
    String keyword;
    RollingSearchType[] types;

    public RollingPageRequestDTO(int page, int size) {
        super(page, size);
    }

    public RollingPageRequestDTO(int page, int size, String keyword, RollingSearchType[] types) {
        super(page, size);
        this.keyword = keyword;
        this.types = types;
    }

    public RollingPageRequestDTO(String keyword, RollingSearchType[] types) {
        this.keyword = keyword;
        this.types = types;
    }
}
