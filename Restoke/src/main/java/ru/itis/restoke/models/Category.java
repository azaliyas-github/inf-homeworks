package ru.itis.restoke.models;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class Category {
    private Long id;
    private String name;
    private String str_id;
}
