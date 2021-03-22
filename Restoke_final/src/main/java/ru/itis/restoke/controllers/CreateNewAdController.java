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
    public String doGet(Model model,
                        HttpServletRequest req){
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user_id") == null) {
            return "redirect:/login";
        } else {
            CategoryDto[] categories = categoryService.getAllCategories().toArray(new CategoryDto[0]);
            // TODO переделать
            //HeaderFtlHelper.toSetEmptyHidden(req);

            // TODO переделать
            model.addAttribute("hidden", "");
            model.addAttribute("searchQuery", "");
            model.addAttribute("categories", categories);
            return "create_new_ads";
        }
    }

    @PostMapping
    public String doPost(Model model, HttpServletRequest req,
                         @RequestParam("subcategory") String subcategory,
                         @RequestParam("products_phone") String sellersPhoneNumber,
                         @RequestParam("products_city") String city,
                         @RequestParam("products_price") Integer price,
                         @RequestParam("description") String description,
                         @RequestParam("products_name") String header,
                         @RequestParam("photo") MultipartFile photo) {
        HttpSession httpSession = req.getSession();

        List<CategoryDto> category = categoryService.getBySubcategoryName(subcategory);
        List<SubcategoryDto> subCategory = subCategoryService.getByName(subcategory);

        String fileName = FileNameHelper.getNewFullFileNames();

        try (OutputStream os = Files.newOutputStream(Path.of(fileName))) {
            os.write(photo.getBytes());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

        long userIdValue = Long.parseLong((String) httpSession.getAttribute("user_id" ));
        List<SellerDto> seller = sellerService.getSellerByUserId(userIdValue);
        if (seller.size() == 0) {
            sellerService.createSeller(SellerDto.builder()
                    .user_id(userIdValue)
                    .general_rating_of_a_seller(0)
                    .role(0)
                    .build());
        }

        seller = sellerService.getSellerByUserId(userIdValue);

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

        //TODO
        //HeaderFtlHelper.toSetEmptyHidden(req);
        model.addAttribute("hidden", "");
        return "redirect:/create_newAd";
    }

//    private static String getPartValue(HttpServletRequest req, String partName) throws IOException, ServletException {
//        Part part = req.getPart(partName);
//        try (InputStream stream = part.getInputStream()) {
//            return IOUtils.toString(stream, StandardCharsets.UTF_8);
//        }
//    }
}
