package com.server.endogphin.domain.dog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Sex {
    FEMALE("FEMALE", "암컷"),
    MALE("MALE", "수컷");

    private final String key;
    private final String type;
}
