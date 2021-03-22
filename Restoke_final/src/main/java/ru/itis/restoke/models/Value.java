package ru.itis.restoke.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Value {
    private long id;
    private long bulletinId;
    private long keyId;
    private String value;

    public Value(long id, long bulletinId, long keyId, String value) {
        this.id = id;
        this.bulletinId = bulletinId;
        this.keyId = keyId;
        this.value =value;
    }
}
