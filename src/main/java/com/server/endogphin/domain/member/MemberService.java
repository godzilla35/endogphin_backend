package com.server.endogphin.domain.member;

import com.server.endogphin.api.dto.MemberSaveRequestDto;
import com.server.endogphin.api.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(readOnly = true)
    public Member getMember(Long id) {
        return memberRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public Member getMember(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }

    @Transactional
    public Member createMember(MemberSaveRequestDto requestDto) {

        Member findMember = getMember(requestDto.getLoginId());

        if(findMember != null) {
            return null;
        }

        // TODO : 회원가입 다른 필드 추가
        Member member = Member.builder()
                .loginId(requestDto.getLoginId())
                .password( bCryptPasswordEncoder.encode(requestDto.getPassword()))
                .name("testName")
                .email("test@test.com")
                .role(Role.USER)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(MemberUpdateRequestDto requestDto) {
        Member updateMember = getMember(requestDto.getId());
        if(updateMember == null) {
            return null;
        }

        return updateMember.update(requestDto.getName(), bCryptPasswordEncoder.encode(requestDto.getPassword()), requestDto.getPicture());
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member deleteMember = getMember(memberId);
        if(deleteMember == null) {
            return;
        }
        memberRepository.delete(deleteMember);
    }
}
