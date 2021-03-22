package ru.itis.restoke.dto;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {
    public Long id;
    public String str_id;
    public String name;
    public List<String> subcategoriesNames;
}
