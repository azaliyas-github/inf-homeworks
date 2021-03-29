package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;

import javax.servlet.http.*;

@Controller
@RequestMapping(produces={"text/html; charset=UTF-8"})
public class MainPageController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/main")
    public String mainPage(Model model, HttpSession httpSession,
                           @CookieValue(value = "user_id", required = false) String user_id) {

        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

        // Если сессии или куки нет то некоторых иконок нет
        if (user_id != null)
            httpSession.setAttribute("user_id", user_id);
        if (httpSession.getAttribute("user_id") != null)
            model.addAttribute("hidden", "");
        else {
            model.addAttribute("hidden", "hidden");
        }
        model.addAttribute("categories", categories);

        return "main_page";


    }
}
