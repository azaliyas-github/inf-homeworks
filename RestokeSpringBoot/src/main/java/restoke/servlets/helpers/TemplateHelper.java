package restoke.servlets.helpers;

import com.zaxxer.hikari.*;
import freemarker.template.*;
import restoke.dto.*;
import restoke.repository.category.*;

import javax.servlet.http.*;
import java.io.*;
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
