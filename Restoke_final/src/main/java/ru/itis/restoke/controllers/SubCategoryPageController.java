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
                           @CookieValue(value = "user_id", required = false) String user_id,
                           HttpSession httpSession) {

        //блок отвечаающий за вывод всех объявлений исходя из фильтров
        Map<String, String> checkBoxAttributes = new HashMap<>();
        checkBoxAttributes.put("check_one", "");
        checkBoxAttributes.put("check_two", "");
        checkBoxAttributes.put("check_three", "");
        if (sellersRole == null)
            checkBoxAttributes.put("check_one", "checked");
        else if (sellersRole.equals("0"))
            checkBoxAttributes.put("check_two", "checked");
        else if (sellersRole.equals("1"))
            checkBoxAttributes.put("check_three", "checked");
        else
            checkBoxAttributes.put("check_one", "checked");
        model.addAllAttributes(checkBoxAttributes);

        //получение всех дто категорий содержащее название подкатегорий
        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

        // Получаем все объявления данной категории из БД
        List<PostingDto> postings = postingService
                .getPostingsBySubcategoryPriceAndSellersRole(minPrice, maxPrice,
                        sellersRole, subcategory);

        // Если сессии или куки нет то некоторых иконок нет
        if (user_id != null)
            httpSession.setAttribute("user_id", user_id);
        model.addAttribute("hidden", httpSession.getAttribute("user_id") != null ?
                "" :  "hidden");
        model.addAttribute("title", subcategory);
        model.addAttribute("categories", categories);
        model.addAttribute("summaryPostings", postings);

        return "category";
    }
}
