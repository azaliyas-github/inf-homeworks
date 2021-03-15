package ru.itis.restoke.servlets;

import com.zaxxer.hikari.*;
import org.apache.commons.io.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.models.*;
import ru.itis.restoke.repository.category.*;
import ru.itis.restoke.repository.posting.*;
import ru.itis.restoke.repository.seller.*;
import ru.itis.restoke.repository.subcategory.*;
import ru.itis.restoke.servlets.helpers.*;

import javax.servlet.http.*;
import java.io.*;
import java.sql.Date;
import java.util.*;

@Controller
@RequestMapping(name="/create_newAd", produces={"text/html; charset=UTF-8"})
public class CreateNewAdController  {

    @GetMapping
    public String returnPage(@RequestBody HttpServletRequest request, Model model)  {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("user_id") == null) {
            return ("login");
        } else {
            HikariDataSource dataSource = ConnectionHelper.getDataSource();
            CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
            CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
            dataSource.close();
            HeaderFtlHelper.toSetEmptyHidden(model);

            model.addAttribute("categories", categories);
            return ("Create_newAd/create_new_ads.ftl");
        }
    }

    @PostMapping
    public String createAd(@RequestParam(value = "subcategory", required = true) String subcategory,
                           @RequestParam(value = "photo", required = true) MultipartFile photo,
                           @RequestParam(value = "products_name", required = true) String adHeader,
                           @RequestParam(value = "products_phone", required = true) String sellerPhone,
                           @RequestParam(value = "products_city", required = true) String city,
                           @RequestParam(value = "products_price", required = true) Integer price,
                           @RequestParam(value = "description", required = true) String description,
                           @RequestBody HttpServletRequest request, Model model){
        HttpSession httpSession = request.getSession();

        HikariDataSource dataSource = ConnectionHelper.getDataSource();

        PostingsRepositoryJdbcImpl postingsRepositoryJdbc = new PostingsRepositoryJdbcImpl(dataSource);
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        Category category = categoriesDtoRepositoryJdbc.findByName(subcategory);
        SubcategoriesRepositoryJdbcImpl subcategoriesRepositoryJdbc = new SubcategoriesRepositoryJdbcImpl(dataSource);
        SubCategory subCategory = subcategoriesRepositoryJdbc.findByName(subcategory);

        String fileName = FileNameHelper.getNewFullFileNames();
        try {
            IOUtils.copy(photo.getInputStream(), new FileOutputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        SellersRepositoryJdbcImpl sellersRepositoryJdbc = new SellersRepositoryJdbcImpl(dataSource);
        long userIdValue = Long.parseLong((String) httpSession.getAttribute("user_id" ));
        List<Seller> seller = sellersRepositoryJdbc.findAllBy("user_id", userIdValue);
        if (seller.size() == 0) {
            sellersRepositoryJdbc.save(Seller.builder()
                    .user_id(userIdValue)
                    .generalRatingOfASeller(0)
                    .role(0)
                    .build());
        }

        seller = sellersRepositoryJdbc.findAllBy("user_id", userIdValue );

        postingsRepositoryJdbc.save(Posting.builder()
                .header(adHeader)
                .mobileNumber(sellerPhone)
                .dateOfPublishing(new Date(System.currentTimeMillis()))
                .address(city)
                .sellerId(seller.get(0).getId())
                .price(price)
                .categoryId(category.getId())
                .photo(fileName)
                .description(description)
                .subCategoryId(subCategory.getId())
                .build());
        dataSource.close();

        // ДОДЕЛАТЬ. возможно придется использовать Spring security
        HeaderFtlHelper.toSetEmptyHidden(model);

        return ("create_newAd");
    }


}

