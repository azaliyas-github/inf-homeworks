package ru.itis.restoke.service.category;

import ru.itis.restoke.dto.CategoryDto;
import java.util.*;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    List<CategoryDto> getBySubcategoryName(String subcategoryName);
}
