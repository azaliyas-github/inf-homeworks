package ru.itis.restoke.servlets.helpers;

import javax.servlet.http.Cookie;
import java.util.Optional;

public class CookieHelper {
    public static Cookie findByName(Cookie[] cookies, String name) {
        if (cookies != null) {
            for (Cookie cookie: cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }

        return null;
    }
}
