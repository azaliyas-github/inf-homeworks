package ru.itis.restoke.servlets.helpers;

import com.zaxxer.hikari.HikariDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.repository.category.CategoriesDtoRepositoryJdbcImpl;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class TemplateHelper {
    public TemplateHelper(String templatePath, HttpServlet httpServlet, HttpServletResponse resp) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);
        cfg.setServletContextForTemplateLoading(httpServlet.getServletContext(), null);
        try {
            Template template = cfg.getTemplate(templatePath);

            Map<String, Object> data = new HashMap<>();
            setCategories(data);
            template.process(data, resp.getWriter());
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }

    private void setCategories(Map<String, Object> data) {
        CategoryDto[] categories = null;
        HikariDataSource dataSource = ConnectionHelper.getDataSource();
        CategoriesDtoRepositoryJdbcImpl categoriesDtoRepositoryJdbc = new CategoriesDtoRepositoryJdbcImpl(dataSource);
        List<CategoryDto> categoryDto = categoriesDtoRepositoryJdbc.findAll();
        data.put("categories", categoryDto);
        dataSource.close();
    }
}
