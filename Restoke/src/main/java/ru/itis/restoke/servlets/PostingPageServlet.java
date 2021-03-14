package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.restoke.dto.CategoryDto;
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

@WebServlet(urlPatterns = {"/ad/*"})
public class PostingPageServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //получаем id объявления из URL
        String url = req.getRequestURL().toString();
        String postingId = url.substring(url.lastIndexOf("/") + 1);

        //достаем из бд объявлениепо его id, его фото и продавца
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        PostingsRepositoryJdbcImpl postingsRepositoryJdbc = new PostingsRepositoryJdbcImpl(dataSource);
        QueryBuilder queryBuilder = new QueryBuilder();
        String statement = queryBuilder.addPostingIdCondition(Long.parseLong(postingId)).build();
        List<Posting> posting = postingsRepositoryJdbc.finder(statement);

        //достаем из бд все подкатегории
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);

        dataSource.close();

        //заполняем все из модели объявления
        req.setAttribute("header", posting.get(0).getHeader());
        req.setAttribute("photoref", posting.get(0).getPhoto());
        req.setAttribute("date", posting.get(0).getDateOfPublishing());
        req.setAttribute("city", posting.get(0).getAddress());
        req.setAttribute("description", posting.get(0).getDescription());
        req.setAttribute("price", posting.get(0).getPrice());

        //заполняем все из можели продавца
        req.setAttribute("phone", "8" + posting.get(0).getMobileNumber());

        //заполняем сайдбар содержащий подкатегории
        req.setAttribute("categories", categories);
        req.getRequestDispatcher("/Ad_Page/ad_page.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Ad_Page/ad_page.ftl").forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
