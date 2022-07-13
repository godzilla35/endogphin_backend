package com.server.endogphin.domain.dog;

import com.server.endogphin.api.dto.DogSaveRequestDto;
import com.server.endogphin.api.dto.MemberSaveRequestDto;
import com.server.endogphin.domain.member.Member;
import com.server.endogphin.domain.member.MemberRepository;
import com.server.endogphin.domain.member.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DogService {
    private final DogRepository dogRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Dog createDog(DogSaveRequestDto requestDto) {

        Optional<Member> findMember = memberRepository.findById(requestDto.getOwnerId());

        if(findMember.isEmpty()) {
            return null;
        }

        Dog dog = Dog.builder()
                .name(requestDto.getName())
                .ageByMonth(requestDto.getAgeByMonth())
                .isNeutered(requestDto.isNeutered())
                .sex(Sex.valueOf(requestDto.getSex()))
                .breed(Breed.valueOf(requestDto.getBreed()))
                .owner(findMember.get())
                .build();

        return dogRepository.save(dog);
    }

    public List<Dog> findDogByOwnerId(Long ownerId) {
        return dogRepository.findByOwnerId(ownerId);
    }
}
