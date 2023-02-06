package org.zerock.api01.todo.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.api01.todo.mapper.TimeMapper;

@Service
@RequiredArgsConstructor
@Log4j2
@Transactional
public class TimeServiceImpl implements TimeService {

    private final TimeMapper timeMapper;

    @Override
    public String getNow() {
        return timeMapper.getTime();
    }
}
