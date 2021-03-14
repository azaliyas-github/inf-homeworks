package ru.itis.restoke.servlets.helpers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class HeaderFtlHelper {
    public static void toSetHidden(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        if (httpSession.getAttribute("user_id") == null) {
            req.setAttribute("hidden", "hidden");
        }
        else {
            req.setAttribute("hidden", "");
        }
    }

    public static void toSetEmptyHidden(HttpServletRequest req) throws IOException, ServletException {
        req.setAttribute("hidden", "");
    }
}
