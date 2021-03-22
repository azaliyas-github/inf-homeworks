package ru.itis.restoke.controllers.helpers;

import javax.servlet.http.*;

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
