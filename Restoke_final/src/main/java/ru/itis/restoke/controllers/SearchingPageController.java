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
@RequestMapping(value = {"/searching_page/*"}, produces={"text/html; charset=UTF-8"})
public class SearchingPageController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    PostingService postingService;

    @GetMapping
    public String doGet(Model model,
                        @RequestParam(value = "search", defaultValue = "") String searchQuery,
                        @RequestParam(value = "min_price", required = false) Integer minPrice,
                        @RequestParam(value = "max_price", required = false) Integer maxPrice,
                        @RequestParam(value = "radio", required = false) String sellersRole,
                        @CookieValue(value = "queryStringValue", required = false) String queryStringValue,
                        HttpServletResponse httpServletResponse) {

        // Проверяем, есть ли кука содержащая значениие строки, если нет - создаем
        if (queryStringValue == null) {
            httpServletResponse.addCookie(new Cookie("queryStringValue", searchQuery));
            queryStringValue = searchQuery;
        } else {
            if (!queryStringValue.equals(searchQuery)) {
                queryStringValue = searchQuery;
            }
        }
        httpServletResponse.addCookie(new Cookie("queryStringValue", queryStringValue));


        //блок отвечаающий за вывод всех объявлений исходя из фильтров
        if (sellersRole.equals("0")) {
            model.addAttribute("check_one", "");
            model.addAttribute("check_two", "checked");
            model.addAttribute("check_three", "");
        } else if (sellersRole.equals("1")) {
            model.addAttribute("check_one", "");
            model.addAttribute("check_two", "");
            model.addAttribute("check_three", "checked");
        } else {
            model.addAttribute("check_one", "checked");
            model.addAttribute("check_two", "");
            model.addAttribute("check_three", "");
        }


        String[] words = queryStringValue.split(" ");
        // выводим сообщение об ошибке
        if (words.length == 0) {
            model.addAttribute("varning", "Упс..<br>Кажется, по вашему запросу ничего нет.");
            model.addAttribute("summaryPostings", new ArrayList<PostingDto>());
        } else {
            // Получаем Dto объявлений
            List<PostingDto> postingDtos = postingService.getPostingsByPriceAndSellersRoleAndQueryString
                    (minPrice, maxPrice, sellersRole, words);
            if (postingDtos.isEmpty()) {
                model.addAttribute("varning", "Упс..<br>Кажется, по вашему запросу ничего нет.");
                model.addAttribute("summaryPostings", new ArrayList<PostingDto>());
            } else {
                model.addAttribute("summaryPostings", postingDtos);
                model.addAttribute("varning", "");
            }
        }


        //получение всех дто категорий содержащее название подкатегорий
        CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

        model.addAttribute("hidden", "");
        model.addAttribute("categories", categories);
        model.addAttribute("searchQuery", searchQuery);
        return "searching_page";
    }
}

