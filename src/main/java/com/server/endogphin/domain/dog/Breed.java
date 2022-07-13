package com.server.endogphin.domain.dog;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Breed {
    DACHSHUND("DACHSHUND", "닥스훈트"),
    JINDO("JINDO", "진돗개"),
    SHIBA("SHIBA", "시바"),
    GOLDEN_RETRIEVER("GOLDEN_RETRIEVER", "골든 리트리버"),
    LABRADOR_RETRIEVER("LABRADOR_RETRIEVER", "래브라도 리트리버"),
    MALTESE("MALTESE", "말티즈"),
    BORDER_COLLIE("BORDER_COLLIE", "보더콜리"),
    BICHON("BICHON", "비숑 프리제"),
    WELSH_CORGI("WELSH_CORGI", "웰시코기"),
    CHIHUAHUA("CHIHUAHUA", "치와와"),
    POMERANIAN("POMERANIAN", "포메라니안"),
    POODLE("POODLE", "푸들");

    private final String key;
    private final String type;
}
