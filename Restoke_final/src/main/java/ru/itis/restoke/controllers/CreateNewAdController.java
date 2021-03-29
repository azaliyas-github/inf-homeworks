package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ru.itis.restoke.controllers.helpers.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.category.*;
import ru.itis.restoke.service.posting.*;
import ru.itis.restoke.service.seller.*;
import ru.itis.restoke.service.subcategory.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping(value = "create_newAd", produces = {"text/html; charset=UTF-8"})
public class CreateNewAdController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    SubcategoryService subCategoryService;

    @Autowired
    SellerService sellerService;

    @Autowired
    PostingService postingService;

    @GetMapping
    public String doGet(Model model, HttpSession httpSession,
                        @CookieValue(value = "user_id", required = false) String user_id){
        if (user_id != null)
            httpSession.setAttribute("user_id", user_id);
        if (httpSession.getAttribute("user_id") == null) {
            return "redirect:/login";
        } else {
            CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);

            model.addAttribute("hidden", "");
            model.addAttribute("categories", categories);
            return "create_new_ads";
        }
    }

    @PostMapping
    public String doPost(Model model, HttpSession httpSession,
                         @RequestParam("subcategory") String subcategory,
                         @RequestParam("products_phone") String sellersPhoneNumber,
                         @RequestParam("products_city") String city,
                         @RequestParam("products_price") Integer price,
                         @RequestParam("description") String description,
                         @RequestParam("products_name") String header,
                         @RequestParam("photo") MultipartFile photo,
                         @CookieValue(value = "user_id", required = false) String user_id) {
        List<CategoryDto> category = categoryService.getBySubcategoryName(subcategory);
        List<SubcategoryDto> subCategory = subCategoryService.getByName(subcategory);

        String fileName = FileNameHelper.getNewFullFileNames();

        try (OutputStream os = Files.newOutputStream(Path.of(fileName))) {
            os.write(photo.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        long userIdValue;
        if (user_id != null) {
            httpSession.setAttribute("user_id", user_id);
        }
        if (httpSession.getAttribute("user_id" ) != null) {
            userIdValue = Long.parseLong(httpSession.getAttribute("user_id" ).toString());
        }
        else {
            return "redirect:/login";
        }

        List<SellerDto> seller = sellerService.getSellerByUserId(userIdValue);
        if (seller.size() == 0) {
            sellerService.createSeller(SellerDto.builder()
                    .user_id(userIdValue)
                    .general_rating_of_a_seller(0)
                    .role(0)
                    .build());

            seller = sellerService.getSellerByUserId(userIdValue);
        }

        postingService.createPosting(PostingForm.builder()
                .header(header)
                .mobile_number(sellersPhoneNumber)
                .date(new Date(System.currentTimeMillis()))
                .city(city)
                .seller_id(seller.get(0).getId())
                .price(price)
                .category_id(category.get(0).getId())
                .photo(fileName)
                .description(description)
                .subcategory_id(subCategory.get(0).getId())
                .build());

        model.addAttribute("categories", categoryService.getAllCategories().toArray(new CategoryDto[0]));
        model.addAttribute("hidden", "");
        return "redirect:/create_newAd";
    }
}
