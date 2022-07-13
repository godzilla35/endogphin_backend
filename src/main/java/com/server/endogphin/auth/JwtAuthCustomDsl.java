package com.server.endogphin.auth;

import com.server.endogphin.constants.Constants;
import com.server.endogphin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@RequiredArgsConstructor
public class JwtAuthCustomDsl extends AbstractHttpConfigurer<JwtAuthCustomDsl, HttpSecurity> {

    private final MemberRepository memberRepository;
    private final Constants constants;

    @Override
    public void configure(HttpSecurity http) {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        http.addFilter(new JwtAuthenticationFilter(authenticationManager, constants))
                .addFilter(new JwtAuthorizationFilter(authenticationManager, memberRepository, constants));


    }
}