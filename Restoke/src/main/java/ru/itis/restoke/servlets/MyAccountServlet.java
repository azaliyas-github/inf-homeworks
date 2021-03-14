package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.models.User;
import ru.itis.restoke.repository.category.CategoriesDtoRepositoryJdbcImpl;
import ru.itis.restoke.repository.users.UsersRepositoryJdbcImpl;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.HeaderFtlHelper;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/my_account")
public class MyAccountServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);


        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user_id") == null) {
            resp.sendRedirect("main");
            return;
        }

        String users_id = httpSession.getAttribute("user_id").toString();
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource);
        List<User> user = usersRepositoryJdbc.findAllBy("id", Long.parseLong(users_id));

        req.setAttribute("name", user.get(0).getFirstName());
        req.setAttribute("lastname", user.get(0).getLastName());
        req.setAttribute("date", user.get(0).getDateOfRegistration());
        req.setAttribute("email", user.get(0).getEmail());
        req.setAttribute("categories", categories);

        dataSource.close();
        req.getRequestDispatcher("MyAccount/my_account.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
