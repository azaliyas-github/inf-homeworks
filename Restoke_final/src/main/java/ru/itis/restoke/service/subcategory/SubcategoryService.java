package ru.itis.restoke.service.subcategory;

import ru.itis.restoke.dto.*;

import java.util.*;

public interface SubcategoryService {
    List<SubcategoryDto> getByName(String name);

}
