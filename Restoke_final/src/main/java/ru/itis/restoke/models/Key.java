package ru.itis.restoke.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Key {
    private long id;
    private long subCategoryId;
    private String name;

    public Key(long id, long subCategoryId, String name) {
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.name = name;
    }
}
