package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.controllers.helpers.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.user.*;
import javax.servlet.http.*;
import java.util.*;

@Controller
@RequestMapping(value = "/login", produces={"text/html; charset=UTF-8"})
public class LoginController extends HttpServlet {

    @Autowired
    UserService userService;

    @PostMapping
    public String doGet(Model model){
        model.addAttribute("varning", "");
        return "login_window";
    }

    @GetMapping
    public String doPost(@RequestParam(value = "username", required = false) String login,
                         @RequestParam(value = "password", required = false) String password,
                         @RequestParam(value = "remember_me",required = false) String remember_me_checkbox,
                         @CookieValue(value = "user_id", required = false) String userId,
                         Model model,
                         HttpSession httpSession,
                         HttpServletResponse response) {

        List<UserDto> logInedUser;
        if (login != null) {
            logInedUser = userService.getUserByEmail(login);
        }
        else {
            model.addAttribute("varning", "Invalid username or password. Try again.");
            return "login_window";
        }
        Long usersId = null;

        //проверка на существование пользователя
        if (logInedUser.size() != 0) {
            usersId = logInedUser.get(0).getId();
            String hashedPassword = null;

            try {
                byte[] passwordBytes = password.getBytes();
                hashedPassword = HashFunctions.getHash(passwordBytes, "SHA-512");

            } catch (Exception e) {
                //???
                e.printStackTrace();
            }

            if (password != null) {
                if (userService.verifyUser(logInedUser.get(0), hashedPassword)) {
                    httpSession.setAttribute("user_id", usersId);
                    httpSession.setMaxInactiveInterval(30);

                    if (userId != null) {
                        response.addCookie(new Cookie("user_id", ""));
                    }

                    if (remember_me_checkbox != null) {
                        Cookie userCookie = new Cookie( "user_id", logInedUser.get(0).getId().toString());
                        userCookie.setMaxAge(24 * 60 * 60);
                        response.addCookie(userCookie);
                    }

                    return "redirect:/restoke_war/main";

                } else {
                    model.addAttribute("varning", "Invalid username or password. Try again.");
                    return "login_window";
                }
            } else {
                model.addAttribute("varning", "Invalid username or password. Try again.");
                return "login_window";            }
        } else {
            model.addAttribute("varning", "You are not sighed up yet. Create an account");
            return "login_window";        }
    }

}
