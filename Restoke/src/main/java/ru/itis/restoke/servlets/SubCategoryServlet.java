package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.dto.SummaryPostingDto;
import ru.itis.restoke.models.Posting;
import ru.itis.restoke.repository.category.CategoriesDtoRepositoryJdbcImpl;
import ru.itis.restoke.repository.posting.PostingsRepositoryJdbcImpl;
import ru.itis.restoke.repository.posting.QueryBuilder;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.HeaderFtlHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/subcategories/*"})
public class SubCategoryServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // Берем параметры из фильтра
        String minPrice = req.getParameter("min_price");
        String maxPrice = req.getParameter("max_price");
        String sellersRole = req.getParameter("radio");

        HikariDataSource dataSource = ConnectionHelper.getDataSource();

        // Блок отвечающий за формирование подкатегории для заголовка
        String url = req.getRequestURL().toString();
        String encryptedSubcategory = url.substring(url.lastIndexOf("/") + 1);
        String decryptedSubcategory = encryptedSubcategory.replace("%20", " ");

        HeaderFtlHelper.toSetHidden(req);

        //блок отвечаающий за вывод всех объявлений данной подкатегории
        PostingsRepositoryJdbcImpl postingsRepositoryJdbc = new PostingsRepositoryJdbcImpl(dataSource);
        QueryBuilder queryBuilder = new QueryBuilder();

        if (minPrice != null) {
            if (!minPrice.equals("")) {
                queryBuilder.addMinPriceCondition(Integer.parseInt(minPrice));
            }
        }
        if (maxPrice != null) {
            if (!maxPrice.equals("")) {
                queryBuilder.addMaxPriceCondition(Integer.parseInt(maxPrice));
            }
        }
        if (sellersRole != null ) {
            if (sellersRole.equals("all")) {
                req.setAttribute("check_one", "checked");
                req.setAttribute("check_two", "");
                req.setAttribute("check_three", "");
            } else if (sellersRole.equals(0)) {
                queryBuilder.addRoleCondition(Integer.parseInt(sellersRole));
                req.setAttribute("check_one", "");
                req.setAttribute("check_two", "checked");
                req.setAttribute("check_three", "");
            } else {
                queryBuilder.addRoleCondition(Integer.parseInt(sellersRole));
                req.setAttribute("check_one", "");
                req.setAttribute("check_two", "");
                req.setAttribute("check_three", "checked");
            }
        } else {
            req.setAttribute("check_one", "checked");
            req.setAttribute("check_two", "");
            req.setAttribute("check_three", "");
        }

        String statement = queryBuilder.addSubcategoryCondition(decryptedSubcategory).build();
        List<Posting> postings = postingsRepositoryJdbc.finder(statement);

        // перевод из полных объявлений в краткие
        List<SummaryPostingDto> summaryPostings = SummaryPostingDto.ToSummaryPostingDto(postings);

        //получение всех дто категорий содержащее название подкатегорий
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);

        dataSource.close();

        req.setAttribute("summaryPostings", summaryPostings);
        req.setAttribute("categories", categories);
        req.setAttribute("title", decryptedSubcategory);
        req.getRequestDispatcher("/All_Categories/category.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
