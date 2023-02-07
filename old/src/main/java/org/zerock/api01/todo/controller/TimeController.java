package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.api01.todo.service.TimeService;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("api/time")
public class TimeController {

    private final TimeService timeService;

    @GetMapping("/serverTime")
    public Map<String, String> getTime() {

        return Map.of("NOW", timeService.getNow());
    }

}
