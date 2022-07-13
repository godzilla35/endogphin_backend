package com.server.endogphin.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.server.endogphin.constants.Constants;
import com.server.endogphin.domain.member.Member;
import com.server.endogphin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Service;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final MemberRepository memberRepository;
    private AuthenticationManager authenticationManager;
    private final Constants constants;


    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, MemberRepository memberRepository, Constants constants) {
        super(authenticationManager);
        this.memberRepository = memberRepository;
        this.constants = constants;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("JwtAuthorizationFilter.doFilterInternal");
        String jwtHeader = request.getHeader("Authorization");
        System.out.println("authorization = " + jwtHeader);

        if(jwtHeader == null || !jwtHeader.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }

        String token = jwtHeader.replace("Bearer ", "");
        String loginId = JWT.require(Algorithm.HMAC512(constants.JWT_SECRET)).build().verify(token).getClaim("loginId").asString();

        if(loginId == null) {
            chain.doFilter(request, response);
            return;
        }

        Member byLoginId = memberRepository.findByLoginId(loginId);

        if(byLoginId == null) {
            chain.doFilter(request, response);
            return;
        }

        PrincipalDetails principalDetails = new PrincipalDetails(byLoginId);
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetails, null, principalDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }
}
