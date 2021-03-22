package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;
import ru.itis.restoke.service.posting.*;

import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping(produces={"text/html; charset=UTF-8"})
public class SubCategoryPageController {
    @Autowired
    PostingService postingService;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/subcategories/{subcategory}")
    protected String doGet(@RequestParam(value = "min_price", required = false) Integer minPrice,
                           @RequestParam(value = "max_price", required = false) Integer maxPrice,
                           @RequestParam(value = "radio", required = false) String sellersRole,
                           @PathVariable("subcategory") String subcategory, Model model,
                           @CookieValue("user_id") String user_id,
                           HttpSession httpSession) {

        List<PostingDto> postings = postingService.getPostingsBySubcategoryPriceAndSellersRole(minPrice, maxPrice, sellersRole, subcategory);
        if (sellersRole != null ) {
            if (sellersRole.equals("0")) {
                model.addAttribute("check_one", "");
                model.addAttribute("check_two", "checked");
                model.addAttribute("check_three", "");}
            else if (sellersRole.equals("1")) {
                model.addAttribute("check_one", "");
                model.addAttribute("check_two", "");
                model.addAttribute("check_three", "checked");
            } else {
                model.addAttribute("check_one", "checked");
                model.addAttribute("check_two", "");
                model.addAttribute("check_three", "");
            }
        } else {
            model.addAttribute("check_one", "checked");
            model.addAttribute("check_two", "");
            model.addAttribute("check_three", "");
        }


        //получение всех дто категорий содержащее название подкатегорий
        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

        // Если сессии или куки нет то некоторых иконок нет
        if (user_id != null)
            httpSession.setAttribute("user_id", user_id);
        if (httpSession.getAttribute("user_id") != null)
            model.addAttribute("hidden", "");
        else {
            model.addAttribute("hidden", "hidden");
        }

        model.addAttribute("title", subcategory);
        model.addAttribute("categories", categories);
        model.addAttribute("summaryPostings", postings);
        return "category";
    }
}
