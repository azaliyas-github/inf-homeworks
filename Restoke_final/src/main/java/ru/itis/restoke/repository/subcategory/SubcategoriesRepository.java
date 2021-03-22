package ru.itis.restoke.repository.subcategory;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;
import java.util.*;

@Repository
public interface SubcategoriesRepository extends JpaRepository<SubcategoryDbo, Long> {

    @Query(value = "SELECT * FROM subcategory", nativeQuery = true)
    List<SubcategoryDbo> findAll();

    @Query(value = "SELECT s.* FROM category c JOIN subcategory s on c.id " +
            "= s.category_id WHERE s.name = :name", nativeQuery = true)
    List<SubcategoryDbo> findByName(String name);


}
