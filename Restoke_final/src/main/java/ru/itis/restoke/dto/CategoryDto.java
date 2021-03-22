package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.dbo.*;

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

    public static List<CategoryDto> toCategoryDtoList(List<CategoryDbo> categoryDboList) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        if (categoryDboList.size() == 0) {
            return categoryDtoList;
        }
        for (CategoryDbo categoryDbo : categoryDboList) {
            categoryDtoList.add(CategoryDto.builder()
                    .id(categoryDbo.getId())
                    .name(categoryDbo.getName())
                    .str_id(categoryDbo.getStr_id()).build());
        }
        return categoryDtoList;
    }
}
