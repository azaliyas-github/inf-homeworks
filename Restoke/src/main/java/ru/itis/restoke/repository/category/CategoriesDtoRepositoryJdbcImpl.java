package ru.itis.restoke.repository.category;

import ru.itis.restoke.dto.CategoryDto;
import ru.itis.restoke.models.Category;
import ru.itis.restoke.models.Posting;
import ru.itis.restoke.models.Seller;
import ru.itis.restoke.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoriesDtoRepositoryJdbcImpl implements CategoriesDtoRepository {

    private final DataSource dataSource;

    public CategoriesDtoRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void save(Object entity) {

    }

    @Override
    public void update(Object entity) {

    }

    @Override
    public void delete(Object entity) {

    }


    public List<CategoryDto> findAll() {
        String SQL_FIND_ALL = "SELECT category.name as cname, category.str_id, subcategory.name  as sname " +
                "FROM category " +
                "JOIN subcategory ON category.id = subcategory.category_id;";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null ;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(SQL_FIND_ALL);
            HashMap<String, CategoryDto> result = new HashMap<>();

            while (resultSet.next()) {
                String categoryName = resultSet.getString("cname");
                CategoryDto categoryDto = null;
                if (!result.containsKey(categoryName)) {
                    categoryDto = CategoryDto.builder()
                            .name(categoryName)
                            .str_id(resultSet.getString("str_id"))
                            .subcategoriesNames(new ArrayList<String>())
                            .build();
                    result.put(categoryName, categoryDto);
                }
                result.get(categoryName).getSubcategoriesNames().add(resultSet.getString("sname"));
            }
            return new ArrayList<>(result.values());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
                try {
                    super.finalize();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }
            }
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
        }

        return new ArrayList<>();
    }

    @Override
    public Category findByName(String name) {
        Connection connection= null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM category JOIN " +
                    "subcategory s on category.id = s.category_id WHERE s.name = '" +
                    name + "'");

            HashMap<Long, Posting> result = new HashMap<>();

            Category category = null;
            while (resultSet.next()) {
                category = Category.builder()
                            .id(resultSet.getLong("id"))
                            .name(resultSet.getString("name"))
                            .str_id(resultSet.getString("str_id"))
                            .build();
            }

            return category;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
            }
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
            }
        }

        return null;
    }
}