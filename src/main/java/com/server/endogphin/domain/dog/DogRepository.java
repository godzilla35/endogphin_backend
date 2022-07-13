package com.server.endogphin.domain.dog;

import com.server.endogphin.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByOwnerId(Long ownerId);
}
