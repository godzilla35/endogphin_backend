package com.server.endogphin.domain.dog;

import com.server.endogphin.domain.BaseEntity;
import com.server.endogphin.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Dog extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Breed breed;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private boolean isNeutered;

    private Integer ageByMonth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DOG_OWNER_MEMBER_ID")
    private Member owner;

    @Builder
    public Dog(String name, Breed breed, Sex sex, boolean isNeutered, Integer ageByMonth, Member owner) {
        this.name = name;
        this.breed = breed;
        this.sex = sex;
        this.isNeutered = isNeutered;
        this.ageByMonth = ageByMonth;
        this.owner = owner;
    }
}
