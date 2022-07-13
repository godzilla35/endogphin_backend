package com.server.endogphin.auth;

import com.server.endogphin.domain.member.Member;
import com.server.endogphin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("PrincipalDetailsService.loadUserByUsername");
        Member byLoginId = memberRepository.findByLoginId(username);

        if(byLoginId == null) {
            throw new UsernameNotFoundException ("no user");
        }

        return new PrincipalDetails(byLoginId);
    }


}
