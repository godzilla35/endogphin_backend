package com.server.endogphin.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MemberSaveResponseDto {
    private Long id;
    private String loginId;
}
