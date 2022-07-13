package com.server.endogphin.domain.member;

import com.server.endogphin.api.dto.MemberSaveResponseDto;
import com.server.endogphin.domain.dog.Dog;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String loginId;

    @Column (nullable = false)
    private String name;

    @Column (nullable = false)
    private String email;

    @Column (nullable = false)
    private String password;

    @Column
    private String picture;

    @OneToMany(mappedBy = "owner")
    private List<Dog> dogs;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public Member(String loginId, String name, String email, String password, String picture, Role role) {
        this.loginId = loginId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.picture = picture;
        this.role = role;
    }

    public Member update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public Member update(String name, String password, String picture) {
        this.name = name;
        this.picture = picture;
        this.password = password;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public MemberSaveResponseDto toMemberSaveResponseDto() {
        MemberSaveResponseDto responseDto = new MemberSaveResponseDto();
        responseDto.setId(this.id);
        responseDto.setLoginId(this.loginId);

        return responseDto;
    }

}
