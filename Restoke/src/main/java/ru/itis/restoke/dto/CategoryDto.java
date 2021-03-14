package ru.itis.restoke.dto;

import lombok.*;

import java.util.ArrayList;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CategoryDto {
    public String str_id;
    public String name;
    public ArrayList<String> subcategoriesNames;
}
