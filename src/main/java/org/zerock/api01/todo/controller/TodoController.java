package org.zerock.api01.todo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.api01.common.dto.PageRequestDTO;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/todos/")
public class TodoController {


    @GetMapping("list")
    public String[] getList(PageRequestDTO pageRequestDTO) {
        log.info(pageRequestDTO);
        return new String[]{"AAA", "BBB", "CCC"};
    }

}
