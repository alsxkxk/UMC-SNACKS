package com.example.demo.config;

import com.example.demo.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;


@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final MemberService memberService;
    private final String SecretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authroization = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info("authroization log :{}", authroization);

        // /ws/chat 경로에 대해서 토큰 검사를 하지 않음
        String requestPath = request.getRequestURI();
        if (requestPath.equals("/ws/chat/info") || requestPath.equals("/ws/chat")) {
            log.info("/ws/chat or /ws/chat/info 경로에 대해서 토큰 검사를 하지 않음.  " + requestPath);
            filterChain.doFilter(request, response);
            return;
        }

        // token 안보내면 block
        if (authroization == null || !authroization.startsWith("Bearer ")) {
            log.error("authroization is null or not Bearer ");
            filterChain.doFilter(request, response);
            return;
        }

        //Token 꺼내기
        String token = authroization.split(" ")[1];

        if (JwtTokenProvider.isExpired(token, SecretKey)) {
            filterChain.doFilter(request, response);
            return;
        }
        //UserName Token 꺼내기!!

        Long username = JwtTokenProvider.getUserName(token, SecretKey);
        log.info("username : ", username);

        //권한 부여
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority("user")));

        //Detail을 넣어줍니다.
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }
}