package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import ru.itis.restoke.models.User;
import ru.itis.restoke.repository.users.UsersRepositoryJdbcImpl;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.CookieHelper;
import ru.itis.restoke.servlets.helpers.HashFunctions;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Locale;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = {"/sign-up"})
public class SignUpServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("varning", "");
        req.getRequestDispatcher("RegistrationWindow/signup_window.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");

        // здесь получаю
        String password = req.getParameter("password");
        String hashedPassword = null;
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(dataSource);

        try {
            byte[] passwordBytes = password.getBytes();
            //здесь хеширую пароль
            hashedPassword = HashFunctions.getHash(passwordBytes, "SHA-512");

        } catch (Exception e) {

        }

        if (email != null && password != null) {

            // это регулярки для Email
            if (!Pattern.compile(".*@.*").matcher(email).matches())
            {
                req.setAttribute("varning", "invalid email address");
                req.getRequestDispatcher("RegistrationWindow/signup_window.ftl").forward(req, resp);

                // Ниже регулярки для пароля
            } else if (!Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$").matcher(password).matches()) {
                req.setAttribute("varning", "invalid login. Login must contain  upper and lowercase and numbers");
                req.getRequestDispatcher("RegistrationWindow/signup_window.ftl").forward(req, resp);
            } else {

                if (usersRepositoryJdbc.verifyUser(email, password)) {
                    req.setAttribute("varning", "This email address has already been registered. ");
                    req.getRequestDispatcher("RegistrationWindow/signup_window.ftl").forward(req, resp);
                } else {
                    usersRepositoryJdbc.save(User.builder()
                            .firstName(req.getParameter("name"))
                            .lastName(req.getParameter("surname"))
                            .email(email)
                            // Здесь засовываю захешированный пароль в бд
                            .password(hashedPassword)
                            .dateOfRegistration(new Date(System.currentTimeMillis()))
                            .address(req.getParameter("city"))
                            .build());
                    dataSource.close();
                    resp.sendRedirect("/restoke_war/login");
                }

            }
        } else {
            req.getRequestDispatcher("RegistrationWindow/signup_window.ftl").forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
