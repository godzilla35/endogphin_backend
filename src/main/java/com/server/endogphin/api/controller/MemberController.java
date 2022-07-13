package com.server.endogphin.api.controller;

import com.server.endogphin.api.BasicResponse;
import com.server.endogphin.api.CommonResponse;
import com.server.endogphin.api.ErrorResponse;
import com.server.endogphin.api.dto.MemberSaveRequestDto;
import com.server.endogphin.api.dto.MemberUpdateRequestDto;
import com.server.endogphin.domain.member.Member;
import com.server.endogphin.domain.member.MemberService;
import com.server.endogphin.domain.member.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/v1/members")
    public ResponseEntity<BasicResponse> createMember(@RequestBody MemberSaveRequestDto requestDto) {

        Member createMember = memberService.createMember(requestDto);

        if(createMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(new ErrorResponse("already exist loginId", HttpStatus.BAD_REQUEST.toString()));
        }

        return ResponseEntity.ok().body(new CommonResponse<>(createMember.toMemberSaveResponseDto()));
    }

    @GetMapping("/api/v1/members/{memberId}")
    public ResponseEntity<BasicResponse> getMember(@PathVariable Long memberId) {

        Member getMember = memberService.getMember(memberId);

        if(getMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("not exist loginId", HttpStatus.BAD_REQUEST.toString()));
        }

        return ResponseEntity.ok().body(new CommonResponse<>(getMember.toMemberSaveResponseDto()));
    }

    @PatchMapping("/api/v1/members")
    public ResponseEntity<BasicResponse> updateMember(@RequestBody MemberUpdateRequestDto requestDto) {
        Member updateMember = memberService.updateMember(requestDto);

        if(updateMember == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("not exist member", HttpStatus.BAD_REQUEST.toString()));
        }

        return ResponseEntity.ok().body(new CommonResponse<>(updateMember.toMemberSaveResponseDto()));
    }


    @DeleteMapping("/api/v1/members/{memberId}")
    public ResponseEntity<BasicResponse> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);

        return ResponseEntity.ok().build();
    }
}
