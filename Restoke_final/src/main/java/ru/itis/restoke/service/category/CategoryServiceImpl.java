package ru.itis.restoke.service.category;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.category.*;
import ru.itis.restoke.repository.subcategory.*;
import java.util.*;

@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoriesRepository categoriesRepository;

    @Autowired
    SubcategoriesRepository subcategoriesRepository;

    @Override
    public List<CategoryDto> getAllCategories() {
        List<CategoryDbo> allCategories = categoriesRepository.findAll();
        List<SubcategoryDbo> allSubcategories = subcategoriesRepository.findAll();
        List<CategoryDto> allCategoriesWithSubcategories = new ArrayList<>();

        for (CategoryDbo categoryDbo : allCategories) {
            String categoryName = categoryDbo.getName();
            String str_id = categoryDbo.getStr_id();
            List<String> subcategoriesOfEachCategory = new ArrayList<>();
            for (SubcategoryDbo subcategoryDbo : allSubcategories) {
                if (subcategoryDbo.category_id.equals(categoryDbo.getId())) {

                    subcategoriesOfEachCategory.add(subcategoryDbo.getName());
                }
            }
            CategoryDto categoryDto = CategoryDto.builder()
                    .name(categoryName)
                    .str_id(str_id)
                    .subcategoriesNames(subcategoriesOfEachCategory)
                    .build();
            allCategoriesWithSubcategories.add(categoryDto);
        }
        return allCategoriesWithSubcategories;
    }

    @Override
    public List<CategoryDto> getBySubcategoryName(String subcategoryName) {
        List<CategoryDbo> categoryDbos = categoriesRepository
                .findBySubcategoryName(subcategoryName);

        return CategoryDto.toCategoryDtoList(categoryDbos);
     }

}
