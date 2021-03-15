package restoke.servlets;

import com.zaxxer.hikari.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import restoke.dto.*;
import restoke.repository.category.*;
import restoke.servlets.helpers.*;

@Controller
public class MainPageController {

    @GetMapping("/main")
    public String mainPage(Model model) {
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        CategoryDto[] categories = categoriesDtoRepositoryJdbc.findAll().toArray(new CategoryDto[0]);
        dataSource.close();
        model.addAttribute("hidden", "");
        model.addAttribute("categories", categories);

        return ("main_page");


    }
}
