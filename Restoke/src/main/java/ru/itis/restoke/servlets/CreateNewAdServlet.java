package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.models.Category;
import ru.itis.restoke.models.Posting;
import ru.itis.restoke.models.Seller;
import ru.itis.restoke.models.SubCategory;
import ru.itis.restoke.repository.category.CategoriesDtoRepositoryJdbcImpl;
import ru.itis.restoke.repository.posting.PostingsRepositoryJdbcImpl;
import ru.itis.restoke.repository.seller.SellersRepositoryJdbcImpl;
import ru.itis.restoke.repository.subcategory.SubcategoriesRepositoryJdbcImpl;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.FileNameHelper;
import ru.itis.restoke.servlets.helpers.HeaderFtlHelper;
import org.apache.commons.io.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;

@MultipartConfig
@WebServlet(urlPatterns = "/create_newAd")
public class CreateNewAdServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user_id") == null) {
            resp.sendRedirect("login");
        } else {
            HikariDataSource dataSource = ConnectionHelper.getDataSource();
            CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
            CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
            dataSource.close();
            HeaderFtlHelper.toSetEmptyHidden(req);

            req.setAttribute("categories", categories);
            req.getRequestDispatcher("Create_newAd/create_new_ads.ftl").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();

        req.setCharacterEncoding("UTF-8");

        HikariDataSource dataSource = ConnectionHelper.getDataSource();

        PostingsRepositoryJdbcImpl postingsRepositoryJdbc = new PostingsRepositoryJdbcImpl(dataSource);
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        Category category = categoriesDtoRepositoryJdbc.findByName(req.getParameter("subcategory"));
        SubcategoriesRepositoryJdbcImpl subcategoriesRepositoryJdbc = new SubcategoriesRepositoryJdbcImpl(dataSource);
        SubCategory subCategory = subcategoriesRepositoryJdbc.findByName(req.getParameter("subcategory"));

        String fileName = FileNameHelper.getNewFullFileNames();
        IOUtils.copy(req.getPart("photo").getInputStream(), new FileOutputStream(fileName));

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
                .header(getPartValue(req, "products_name"))
                .mobileNumber(req.getParameter("products_phone"))
                .dateOfPublishing(new Date(System.currentTimeMillis()))
                .address(req.getParameter("products_city"))
                .sellerId(seller.get(0).getId())
                .price(Integer.parseInt(req.getParameter( "products_price")))
                .categoryId(category.getId())
                .photo(fileName)
                .description(req.getParameter("description"))
                .subCategoryId(subCategory.getId())
                .build());
        dataSource.close();

        HeaderFtlHelper.toSetEmptyHidden(req);

        resp.sendRedirect("create_newAd");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }

    private static String getPartValue(HttpServletRequest req, String partName) throws IOException, ServletException {
        Part part = req.getPart(partName);
        try (InputStream stream = part.getInputStream()) {
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        }
    }
}
