package com.server.endogphin.api.controller;

import com.server.endogphin.api.BasicResponse;
import com.server.endogphin.api.CommonResponse;
import com.server.endogphin.api.ErrorResponse;
import com.server.endogphin.api.dto.DogSaveRequestDto;
import com.server.endogphin.api.dto.DogSaveResponseDto;
import com.server.endogphin.api.dto.MemberSaveRequestDto;
import com.server.endogphin.domain.dog.Dog;
import com.server.endogphin.domain.dog.DogService;
import com.server.endogphin.domain.member.Member;
import com.server.endogphin.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
public class DogController {

    private final DogService dogService;

    @PostMapping("/api/v1/dogs")
    public ResponseEntity<BasicResponse> createDog(@RequestBody DogSaveRequestDto requestDto) {

        Dog dog = dogService.createDog(requestDto);

        if(dog == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse("Invalid Request", HttpStatus.BAD_REQUEST.toString()));
        }

        DogSaveResponseDto responseDto = DogSaveResponseDto.builder()
                .id(dog.getId())
                .ageByMonth(dog.getAgeByMonth())
                .name(dog.getName())
                .breed(dog.getBreed().toString())
                .isNeutered(dog.isNeutered())
                .ownerId(dog.getOwner().getId())
                .sex(dog.getSex().toString())
                .build();

        return ResponseEntity.ok().body(new CommonResponse<>(responseDto));
    }

    @GetMapping("/api/v1/dogs/members/{memberId}")
    public ResponseEntity<BasicResponse> getDog(@PathVariable Long memberId) {
        List<Dog> dogByOwnerId = dogService.findDogByOwnerId(memberId);
        List<DogSaveResponseDto> responseDtos = dogByOwnerId.stream().map(dog -> DogSaveResponseDto.builder()
                .id(dog.getId())
                .ageByMonth(dog.getAgeByMonth())
                .name(dog.getName())
                .breed(dog.getBreed().toString())
                .isNeutered(dog.isNeutered())
                .ownerId(dog.getOwner().getId())
                .sex(dog.getSex().toString())
                .build()).collect(Collectors.toList());

        return ResponseEntity.ok().body(new CommonResponse<>(responseDtos));
    }
}
