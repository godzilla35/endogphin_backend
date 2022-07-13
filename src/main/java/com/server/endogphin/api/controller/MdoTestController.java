package com.server.endogphin.api.controller;

import com.server.endogphin.constants.Constants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MdoTestController {

    private final Constants constants;

    @PostMapping("/mdo")
    public String test() {

        System.out.println("jwt : " + constants.JWT_SECRET);
        return "OK";
    }
}
