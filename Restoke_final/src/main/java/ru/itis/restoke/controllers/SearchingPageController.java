package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;
import ru.itis.restoke.service.posting.*;
import javax.servlet.http.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

@Controller
@RequestMapping(value = {"/searching_page"}, produces={"text/html; charset=UTF-8"})
public class SearchingPageController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    PostingService postingService;

    @GetMapping()
    public String doGet(Model model,
                        @RequestParam(value = "search", defaultValue = "") String search_query,
                        @RequestParam(value = "min_price", required = false) Integer minPrice,
                        @RequestParam(value = "max_price", required = false) Integer maxPrice,
                        @RequestParam(value = "radio", required = false) String sellersRole,
                        @CookieValue(value = "queryStringValue", required = false) String queryStringValue,
                        @CookieValue(value = "user_id", required = false) String user_id,
                        HttpServletResponse httpServletResponse,
                        HttpSession httpSession) {

        // Проверяем, есть ли кука содержащая значениие строки, если нет - создаем
        if (queryStringValue == null) {
            String cookieSearchQuery = URLEncoder.encode(search_query, StandardCharsets.UTF_8);
            httpServletResponse.addCookie(new Cookie("queryStringValue", cookieSearchQuery));
            queryStringValue = search_query;
        } else {
            queryStringValue = URLDecoder.decode(queryStringValue, StandardCharsets.UTF_8);
            if (!queryStringValue.equals(search_query)) {
                queryStringValue = search_query;
            }
        }
        String cookieSearchQuery = URLEncoder.encode(search_query, StandardCharsets.UTF_8);
        httpServletResponse.addCookie(new Cookie("queryStringValue", cookieSearchQuery));

        //блок отвечаающий за вывод всех объявлений исходя из фильтров
        model.addAttribute("check_one", "");
        model.addAttribute("check_two", "");
        model.addAttribute("check_three", "");
        if (sellersRole == null)
            model.addAttribute("check_one", "checked");
        else if (sellersRole.equals("0"))
            model.addAttribute("check_two", "checked");
        else if (sellersRole.equals("1"))
            model.addAttribute("check_three", "checked");
        else
            model.addAttribute("check_one", "checked");

        String[] words = queryStringValue.split(" ");
        // выводим сообщение об ошибке
        if (words.length == 0 || queryStringValue.equals("")) {
            model.addAttribute("varning", "Упс..Кажется, по вашему запросу ничего нет.");
            model.addAttribute("summaryPostings", new ArrayList<PostingDto>());
        } else {
            // Получаем Dto объявлений
            List<PostingDto> postingDtos = postingService.getPostingsByPriceAndSellersRoleAndQueryString
                    (minPrice, maxPrice, sellersRole, words);
            if (postingDtos.isEmpty()) {
                model.addAttribute("varning", "Упс..Кажется, по вашему запросу ничего нет.");
                model.addAttribute("summaryPostings", new ArrayList<PostingDto>());
            } else {
                model.addAttribute("summaryPostings", postingDtos);
                model.addAttribute("varning", "");
            }
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

        model.addAttribute("categories", categories);
        model.addAttribute("min_price", minPrice != null ? minPrice.toString() : "");
        model.addAttribute("max_price", maxPrice != null ? maxPrice.toString() : "");
        return "searching_page";
    }
}

