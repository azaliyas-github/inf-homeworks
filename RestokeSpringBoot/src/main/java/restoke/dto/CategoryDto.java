package restoke.dto;

import lombok.*;

import java.util.*;

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
