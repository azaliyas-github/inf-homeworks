package ru.itis.restoke.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class SubCategory {
    private long id;
    private long categoryId;
    private String name;

    public SubCategory(long id, long categoryId, String name) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
    }
}
