package ru.itis.restoke.dto;

import lombok.*;
import ru.itis.restoke.dbo.*;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubcategoryDto {
    private Long id;
    public Long category_id;
    public String name;

    public static List<SubcategoryDto> toSubcategoryDtoList(List<SubcategoryDbo> subcategoryDbos) {
        List<SubcategoryDto> subcategoryDtoList = new ArrayList<>();
        for (SubcategoryDbo subcategoryDbo : subcategoryDbos) {
            subcategoryDtoList.add(toSubcategoryDto(subcategoryDbo));
        }
        return subcategoryDtoList;
    }

    public static SubcategoryDto toSubcategoryDto(SubcategoryDbo subcategoryDbo) {
        SubcategoryDto subcategoryDto = SubcategoryDto.builder()
                .id(subcategoryDbo.getId())
                .category_id(subcategoryDbo.getCategory_id())
                .name(subcategoryDbo.getName()).build();

        return subcategoryDto;
    }
}
