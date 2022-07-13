package com.server.endogphin.api.dto;

import com.server.endogphin.domain.dog.Sex;
import com.server.endogphin.domain.member.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
public class DogSaveRequestDto {

    private String name;

    private String sex;

    private boolean isNeutered;

    private Integer ageByMonth;

    private Long ownerId;

    private String breed;

}
