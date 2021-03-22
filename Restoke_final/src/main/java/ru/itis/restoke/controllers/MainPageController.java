package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;

@Controller
@RequestMapping(produces={"text/html; charset=UTF-8"})
public class MainPageController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/main")
    public String mainPage(Model model) {
        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);
        model.addAttribute("searchQuery", "");
        model.addAttribute("hidden", "");
        model.addAttribute("categories", categories);

        return "main_page";


    }
}
