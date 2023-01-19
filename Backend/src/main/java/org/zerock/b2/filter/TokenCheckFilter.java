package org.zerock.b2.filter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import org.zerock.b2.security.JWTUtil;
import org.zerock.b2.security.exception.AccessTokenException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("[TokenCheckFilter: doFilterInternal]");

        try {
            validateAccessToken(request); // 토큰 검증
            filterChain.doFilter(request, response); // 다음 필터로 넘겨주기
        } catch (AccessTokenException ate) {
            ate.sendResponseError(response);
        }
    }

    private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {

        String headerStr = request.getHeader("Authorization");

        if (headerStr == null || headerStr.length() < 8) {
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
        }

        //Bearer 생략
        String tokenType = headerStr.substring(0, 6);
        String tokenStr = headerStr.substring(7);

        if (tokenType.equalsIgnoreCase("Bearer") == false) {
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
        }

        try {
            Map<String, Object> payload = jwtUtil.validateToken(tokenStr);

            //mid
            String mid = (String) payload.get("mid");

            log.info("mid: " + mid);

            UserDetails userDetails = userDetailsService.loadUserByUsername(mid);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());

            // DB 에 접근해야 되기 때문에 지금 성능은 떨어지지만, Spring session, 캐시, redis 이런거 붙이면 개선 가능
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return payload;
        } catch (MalformedJwtException malformedJwtException) {
            log.error("MalformedJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
        } catch (SignatureException signatureException) {
            log.error("SignatureException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("ExpiredJwtException----------------------");
            throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
        }
    }
}
