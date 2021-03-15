package ru.itis.restoke.servlets.helpers;

import org.springframework.ui.*;
import javax.servlet.http.*;

public class HeaderFtlHelper {
    public static void toSetHidden(HttpSession httpSession, Model model) {
        if (httpSession.getAttribute("user_id") == null) {
            model.addAttribute("hidden", "hidden");
        }
        else {
            model.addAttribute("hidden", "");
        }
    }

    public static void toSetEmptyHidden(Model model) {
        model.addAttribute("hidden", "");
    }
}
