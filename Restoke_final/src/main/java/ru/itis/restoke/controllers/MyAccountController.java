package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;
import ru.itis.restoke.service.user.*;
import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping(value = "/my_account")
public class MyAccountController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    @GetMapping
    public String doGet(HttpSession httpSession, Model model) {
        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

        if (httpSession.getAttribute("user_id") == null) {
            return "redirect:/main";
        }

        String users_id = httpSession.getAttribute("user_id").toString();
        List<UserDto> user = userService.getUserById(Long.parseLong(users_id));

        model.addAttribute("searchQuery", "");
        model.addAttribute("categories", categories);
        model.addAttribute("email", user.get(0).getEmail());
        model.addAttribute("name", user.get(0).getFirst_name());
        model.addAttribute("lastname", user.get(0).getLast_name());
        model.addAttribute("date", user.get(0).getDate_of_registration());

        return "my_account";
    }
}
