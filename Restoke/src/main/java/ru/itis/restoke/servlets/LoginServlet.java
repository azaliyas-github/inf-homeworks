package ru.itis.restoke.servlets;

import com.zaxxer.hikari.HikariDataSource;
import ru.itis.restoke.models.User;
import ru.itis.restoke.repository.users.UsersRepositoryJdbcImpl;
import ru.itis.restoke.servlets.helpers.ConnectionHelper;
import ru.itis.restoke.servlets.helpers.CookieHelper;
import ru.itis.restoke.servlets.helpers.HashFunctions;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("varning", "");
        req.getRequestDispatcher("RegistrationWindow/login_window.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String login = req.getParameter("username");
        String password = req.getParameter("password");

        //Достаем из бд пользователя с таким username
        HikariDataSource hikariDataSource = ConnectionHelper.getDataSource();
        UsersRepositoryJdbcImpl usersRepositoryJdbc = new UsersRepositoryJdbcImpl(hikariDataSource);
        List<User> logInedUser = usersRepositoryJdbc.findAllBy("email", login);
        Long usersId = null;
        if (logInedUser.size() != 0) {
            usersId = logInedUser.get(0).getId();

            String hashedPassword = null;

            try {
                byte[] passwordBytes = password.getBytes();
                hashedPassword = HashFunctions.getHash(passwordBytes, "SHA-512");

            } catch (Exception e) {

            }

            if (login != null && password != null) {
                if (usersRepositoryJdbc.verifyUser(login, hashedPassword)) {
                    HttpSession httpSession = req.getSession();
                    httpSession.setAttribute("user_id", usersId);
                    httpSession.setMaxInactiveInterval(5);

                    if (CookieHelper.findByName(req.getCookies(),"user_id") != null) {
                        resp.addCookie(new Cookie("user_id", ""));
                    }

                    if (!(req.getParameter("remember_me") == null)) {

                        Cookie userCookie = new Cookie("user_id", logInedUser.get(0).getId().toString());
                        userCookie.setMaxAge(24 * 60 * 60);

                        resp.addCookie(userCookie);
                    }
                    resp.sendRedirect("/restoke_war/main");
                } else {
                    req.setAttribute("varning", "Invalid username or password. Try again.");
                    req.getRequestDispatcher("RegistrationWindow/login_window.ftl").forward(req, resp);
                }
            } else {
                req.setAttribute("varning", "Invalid username or password. Try again.");
                req.getRequestDispatcher("RegistrationWindow/login_window.ftl").forward(req, resp);
            }

        } else {
            req.setAttribute("varning", "You are not sighed up yet. Create an account");
            req.getRequestDispatcher("RegistrationWindow/login_window.ftl").forward(req, resp);
        }

        hikariDataSource.close();
    }

}
