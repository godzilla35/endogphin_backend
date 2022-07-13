package com.server.endogphin.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberUpdateRequestDto {
    private Long id;
    private String password;
    private String name;
    private String picture;
}
