package org.zerock.b2.handler;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.zerock.b2.security.JWTUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Log4j2
public class JWTLoginSuccessHandler implements AuthenticationSuccessHandler {

    private JWTUtil jwtUtil;

    public JWTLoginSuccessHandler(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // 추상 클래스가 두 개 지만 interface 의 Default 기능을 이용해서 하나만 등록해 줘도 되는거 임
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("[JWTLoginSuccessHandler]: onAuthenticationSuccess");

        log.info(authentication.getName());
        log.info(authentication.getDetails());
        log.info(authentication);

        String mid = authentication.getName();
        String access = jwtUtil.generateToken(Map.of("mid", mid), 2);
        String refresh = jwtUtil.generateToken(Map.of("mid", mid), 10);

        log.info(access);
        log.info(refresh);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        Gson gson = new Gson();
        String jsonStr = gson.toJson(Map.of("access", access, "refresh", refresh));
        out.write(jsonStr);
        out.flush();
        out.close();
    }
}
