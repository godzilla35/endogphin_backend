package com.server.endogphin.config;

import com.server.endogphin.auth.CustomOAuth2UserService;
import com.server.endogphin.auth.JwtAuthCustomDsl;
import com.server.endogphin.constants.Constants;
import com.server.endogphin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final CustomOAuth2UserService customOAuth2UserService;
    private final MemberRepository memberRepository;
    private final Constants constants;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션을 안쓰겠다.
                .and()
                .formLogin().disable() // 둘다 안쓸꺼라 비활성화
                .httpBasic().disable() // 둘다 안쓸꺼라 비활성화
                .headers().frameOptions().disable() // h2-console 사용을 위한 설정
                .and()
                .apply(new JwtAuthCustomDsl(memberRepository, constants))
                .and()
                .authorizeRequests()
                .antMatchers("/mdo/**").authenticated()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .build();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

