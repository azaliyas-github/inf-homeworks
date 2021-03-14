package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.dto.SummaryPostingDto;
import ru.itis.restoke.models.Posting;
import ru.itis.restoke.repository.category.CategoriesDtoRepositoryJdbcImpl;
import ru.itis.restoke.repository.posting.PostingsRepositoryJdbcImpl;
import ru.itis.restoke.repository.posting.QueryBuilder;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.CookieHelper;
import ru.itis.restoke.servlets.helpers.HeaderFtlHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/searching_page/*"})
public class SearchingPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchQuery = req.getParameter("search");

        // Проверяем, есть ли кука содержащая значениие строки, если нет - создаем
        Cookie queryStringValue = CookieHelper.findByName(req.getCookies(), "queryStringValue");
        if (queryStringValue == null) {
            if (searchQuery == null) {
                searchQuery = "";
            }
            queryStringValue = new Cookie("queryStringValue", searchQuery);
        } else {
            if ((queryStringValue.getValue() != searchQuery) && (searchQuery != null)) {
                queryStringValue.setValue(searchQuery);
            }
        }
        resp.addCookie(queryStringValue);
        String[] words = queryStringValue.getValue().split(" ");

        HeaderFtlHelper.toSetHidden(req);

        // Берем параметры из фильтра
        String minPrice = req.getParameter("min_price");
        String maxPrice = req.getParameter("max_price");
        String sellersRole = req.getParameter("radio");

        HikariDataSource dataSource = ConnectionHelper.getDataSource();

        //блок отвечаающий за вывод всех объявлений исходя из фильтров
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

        String statement;
        // выводим сообщение об ошибке
        if (words.length == 0) {
            req.setAttribute("varning", "Упс..<br>Кажется, по вашему запросу ничего нет.");
            req.setAttribute("summaryPostings", new ArrayList<SummaryPostingDto>());
        } else {
            statement = queryBuilder.addSearchCondition(words).build();

            // Получаем модельки объявлений
            List<Posting> postings = postingsRepositoryJdbc.finder(statement);
            if (postings.isEmpty()) {
                req.setAttribute("varning", "Упс..<br>Кажется, по вашему запросу ничего нет.");
                req.setAttribute("summaryPostings", new ArrayList<SummaryPostingDto>());
            }
            else {
                // перевод из полных объявлений в краткие
                List<SummaryPostingDto> summaryPostings = SummaryPostingDto.ToSummaryPostingDto(postings);
                req.setAttribute("summaryPostings", summaryPostings);
                req.setAttribute("varning", "");
            }
        }

        //получение всех дто категорий содержащее название подкатегорий
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);

        dataSource.close();


        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/Searching_Page/searching_page.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
