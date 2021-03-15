package ru.itis.restoke.servlets;

import com.zaxxer.hikari.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.repository.category.*;
import ru.itis.restoke.servlets.helpers.*;

@Controller
@RequestMapping(name = "/main", produces={"text/html; charset=UTF-8"})
public class MainPageController {

    @GetMapping
    public String mainPage(Model model) {
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
        dataSource.close();
        model.addAttribute("categories", categories);

        return ("main_page");


    }
}
