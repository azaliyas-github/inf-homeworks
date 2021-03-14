package ru.itis.restoke.servlets.helpers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class HttpSessionHelper {
    public static void createSession(HttpServletRequest req) {
        Cookie user_id = CookieHelper.findByName(req.getCookies(), "user_id");
        if (user_id != null) {
            if (!user_id.getValue().equals("")) {
                HttpSession httpSession = req.getSession();
                httpSession.setAttribute("user_id", user_id.getValue());
                httpSession.setMaxInactiveInterval(5 * 60);
            }

        } else {
            HttpSession httpSession = req.getSession();
            httpSession.setMaxInactiveInterval(5*60);
        }
    }
}
