package ru.itis.restoke.repository.subcategory;
import ru.itis.restoke.models.Category;
import ru.itis.restoke.models.Posting;
import ru.itis.restoke.models.SubCategory;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubcategoriesRepositoryJdbcImpl implements SubcategoriesRepository {

    private final DataSource dataSource;

    public SubcategoriesRepositoryJdbcImpl(DataSource dataSource) {
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

    public SubCategory findByName(String name) {
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

            SubCategory category = null;
            while (resultSet.next()) {
                category = SubCategory.builder()
                        .id(resultSet.getLong("id"))
                        .categoryId(resultSet.getLong("category_id"))
                        .name(resultSet.getString("name"))
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
