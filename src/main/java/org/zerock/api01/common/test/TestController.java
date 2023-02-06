package org.zerock.api01.common.test;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class TestController {

    @Getter
    static class Request {
        private String name;
        private Integer age;
        private String contry;

        public Request(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    @Getter
    static class Response {
        private String info;
        private LocalDateTime now = LocalDateTime.now();

        public Response(String info) {
            this.info = info;
        }
    }

    @GetMapping("/api/test/info")
    public Response getInfos(@RequestBody Request request) {
        String info = request.getName() + " / " + request.getAge();

        return new Response(info);
    }
}
