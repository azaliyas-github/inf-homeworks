package ru.itis.restoke.service.subcategory;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.subcategory.*;

import java.util.*;

@Component
public class SubcategoryServiceImpl implements SubcategoryService{
    @Autowired
    SubcategoriesRepository subcategoriesRepository;

    @Override
    public List<SubcategoryDto> getByName(String name) {

        return SubcategoryDto.toSubcategoryDtoList(subcategoriesRepository.findByName(name));
    }
}
