package org.zerock.b2.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/sample")
@RequiredArgsConstructor
public class SampleRestController {

    @PreAuthorize("permitAll()")
    @GetMapping("/ex1")
    public Map<String, String> ex1() {

        return Map.of(
                "v1", "aaa",
                "v2", "bbb");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/ex2")
    public Map<String, String> ex2() {

        return Map.of(
                "k1", "aaa",
                "k2", "bbb");
    }

    @PreAuthorize("hasRole('XXX')")
    @GetMapping("/ex3")
    public Map<String, String> ex3() {

        return Map.of(
                "c1", "aaa",
                "c2", "bbb");
    }
}
