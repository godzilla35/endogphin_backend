package com.server.endogphin.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.server.endogphin.api.dto.LoginRequestDto;
import com.server.endogphin.constants.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

// 스프링 싴큐리티의 UsernamePasswordAuthenticationFilter 가 존재함
// /login 요청해서 username, password 전송하면 해당 필터 동작
// 현재 formlogin을 disable했기때문에 해당 필터는 동작안함
// 다시 등록해줘야함
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final Constants constants;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("JwtAuthenticationFilter.attemptAuthentication");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            LoginRequestDto loginRequestDto = objectMapper.readValue(request.getInputStream(), LoginRequestDto.class);

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(loginRequestDto.getLoginId(), loginRequestDto.getPassword());

            return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 인증이 정상적으로 되었으면 해당 메소드 실행
    // 여기서 jwt 토큰을 만들어서 response 넘겨줌
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        System.out.println("JwtAuthenticationFilter.successfulAuthentication");

        PrincipalDetails principal = (PrincipalDetails)authResult.getPrincipal();

        System.out.println("===###");
        //System.out.println(Constants.JWT_EXPIRE_MS);
        //System.out.println(Constants.JWT_SECRET);

        String jwtToken = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + Integer.parseInt(constants.JWT_EXPIRE_MS)))
                .withClaim("id", principal.getId())
                .withClaim("loginId", principal.getUsername())
                .sign(Algorithm.HMAC512(constants.JWT_SECRET));

        response.addHeader("Authorization", "Bearer " + jwtToken);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("JwtAuthenticationFilter.unsuccessfulAuthentication");
        // TODO : 로그인 실패시 response 정의
        response.setStatus(404);
    }
}
