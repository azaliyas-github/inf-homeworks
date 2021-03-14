package ru.itis.restoke.repository.category;

import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.models.Category;
import ru.itis.restoke.repository.CrudRepository;

import java.util.HashMap;
import java.util.List;

public interface CategoriesDtoRepository extends CrudRepository {
    List<CategoryDto> findAll();
    Category findByName(String name);
}
