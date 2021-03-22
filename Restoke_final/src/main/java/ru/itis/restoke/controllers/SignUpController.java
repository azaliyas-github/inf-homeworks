package ru.itis.restoke.controllers;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import ru.itis.restoke.dto.*;
import ru.itis.restoke.service.user.*;

import java.sql.*;
import java.util.regex.*;

@Controller
@RequestMapping(value = {"/sign_up"}, produces={"text/html; charset=UTF-8"})
public class SignUpController {
    @Autowired
    UserService userService;

    @GetMapping
    public String doGet(Model model) {
        model.addAttribute("varning", "");
        return "signup_window";
    }

    @PostMapping
    public String doPost(@RequestParam(value = "email", required = false) String email,
                          @RequestParam(value = "password", required = false) String password,
                          @RequestParam(value = "name", required = false) String first_name,
                          @RequestParam(value = "surname", required = false) String last_name,
                          @RequestParam(value = "city", required = false) String city,
                          Model model) {

        if (email != null && password != null) {

            // это регулярки для Email
            if (!Pattern.compile(".*@.*").matcher(email).matches())
            {
                model.addAttribute("varning", "invalid email address");
                return "signup_window";

                // Ниже регулярка для пароля
            } else if (!Pattern.compile("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\\s).*$").matcher(password).matches()) {
                model.addAttribute("varning", "invalid login. Login must contain  upper and lowercase and numbers");
                return "signup_window";
            } else {

                if (userService.verifyUser(userService.getUserByEmail(email).get(0), password)) {
                    model.addAttribute("varning", "This email address has already been registered. ");
                    return "signup_window";
                } else {
                    userService.registerUser(UserForm.builder()
                            .first_name(first_name)
                            .last_name(last_name)
                            .email(email)
                            .password(password)
                            .date_of_registration(new Date(System.currentTimeMillis()))
                            .address(city)
                            .build());
                    return "redirect:/login";
                }
            }
        } else {
            return "signup_window";
        }
    }
}
