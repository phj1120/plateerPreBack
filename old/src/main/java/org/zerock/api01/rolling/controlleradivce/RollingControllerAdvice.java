package org.zerock.api01.rolling.controlleradivce;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zerock.api01.common.dto.ResultDTO;

@RestControllerAdvice
public class RollingControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ResultDTO> runtimeException(IllegalArgumentException e) {

        return ResponseEntity
                .badRequest()
                .body(ResultDTO.builder().error(e.getMessage()).build());
    }
}
