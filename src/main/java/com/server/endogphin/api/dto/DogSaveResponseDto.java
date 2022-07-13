package com.server.endogphin.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class DogSaveResponseDto {

    private Long id;

    private String name;

    private String sex;

    private boolean isNeutered;

    private Integer ageByMonth;

    private Long ownerId;

    private String breed;

    @Builder
    public DogSaveResponseDto(Long id, String name, String sex, boolean isNeutered, Integer ageByMonth, Long ownerId, String breed) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.isNeutered = isNeutered;
        this.ageByMonth = ageByMonth;
        this.ownerId = ownerId;
        this.breed = breed;
    }
}
