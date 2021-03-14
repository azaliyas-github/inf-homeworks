package ru.itis.restoke.repository.subcategory;

import ru.itis.restoke.models.Category;
import ru.itis.restoke.models.SubCategory;
import ru.itis.restoke.repository.CrudRepository;

import java.util.List;

public interface SubcategoriesRepository extends CrudRepository {
    SubCategory findByName(String name);
}
