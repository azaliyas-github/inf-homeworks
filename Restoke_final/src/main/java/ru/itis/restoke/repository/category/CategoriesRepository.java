package ru.itis.restoke.repository.category;

import org.springframework.data.jpa.repository.*;
import ru.itis.restoke.dbo.*;

import java.util.*;

public interface CategoriesRepository extends JpaRepository<CategoryDbo, Long> {

    @Query(value = "SELECT * FROM category", nativeQuery = true)
    List<CategoryDbo> findAll();


    @Query(value = "SELECT * FROM category JOIN subcategory s on category.id = " +
            "s.category_id WHERE s.name = :name", nativeQuery = true)
    CategoryDbo findBySubcategoryName(String name);
}
