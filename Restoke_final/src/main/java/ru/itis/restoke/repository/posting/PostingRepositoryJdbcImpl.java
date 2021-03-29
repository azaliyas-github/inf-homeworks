package ru.itis.restoke.repository.posting;

import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;
import ru.itis.restoke.dbo.*;
import ru.itis.restoke.models.*;

import javax.sql.*;
import java.io.*;
import java.sql.Date;
import java.sql.*;
import java.util.*;

@Component
public class PostingRepositoryJdbcImpl implements CustomRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    public PostingRepositoryJdbcImpl(DataSource dataSource, JdbcTemplate jdbcTemplate) {
        this.dataSource = dataSource;
        this.jdbcTemplate = jdbcTemplate;
    }

    private List<String> getNewFullFileNames(String filePath, int filesCount) {
        int currentCount = 0;
        int counter = 1;
        List<String> fullFileNames = new ArrayList<>();

        String fullFileName = filePath + File.separator + "img.jpg";
        File file = new File(fullFileName);
        while (currentCount < filesCount) {
            boolean fileExists = file.exists();
            String previousFullFileName = fullFileName;

            counter++;
            fullFileName = filePath + File.separator + "img (" + counter + ")" + ".jpg";
            file = new File(fullFileName);

            if (!fileExists) {
                fullFileNames.add(previousFullFileName);
                ++currentCount;
            }
        }
        return fullFileNames;
    }

    public List<PostingDbo> finder(String SQL_REQUEST) {
        Connection connection= null;
        Statement statement= null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_REQUEST);

            HashMap<Long, PostingDbo> result = new HashMap<>();

            if (resultSet == null) {
                new ArrayList<PostingDbo>();
            }
            while (true) {
                if (!resultSet.next()) break;
                long id = resultSet.getLong("id");
                PostingDbo posting = null;
                if (!result.containsKey(id)) {
                        posting = PostingDbo.builder()
                            .id(id)
                            .category_id(resultSet.getLong("category_id"))
                            .subcategory_id(resultSet.getLong("subcategory_id"))
                            .seller_id(resultSet.getLong("seller_id"))
                            .date(resultSet.getDate("date"))
                            .city(resultSet.getString("city"))
                            .mobile_number(resultSet.getString("mobile_number"))
                            .header(resultSet.getString("header"))
                            .description(resultSet.getString("description"))
                            .price(resultSet.getInt("price"))
                            .photo(resultSet.getString("ref"))
                            .build();
                    result.put(id, posting);
                }
            }
            return new ArrayList<>(result.values());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
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
    }

    @Override
    public List<PostingDbo> findByPriceAndSellersRole(String query) {
        PreparedStatementCallback<List<PostingDbo>> statementAction = statement -> {
            ResultSet resultSet = statement.executeQuery();

            HashMap<Long, PostingDbo> result = new HashMap<>();

            if (resultSet == null) {
                new ArrayList<PostingDbo>();
            }
            while (true) {
                if (!resultSet.next()) break;
                long id = resultSet.getLong("id");
                PostingDbo posting = null;
                if (!result.containsKey(id)) {
                    posting = PostingDbo.builder()
                            .id(id)
                            .category_id(resultSet.getLong("category_id"))
                            .subcategory_id(resultSet.getLong("subcategory_id"))
                            .seller_id(resultSet.getLong("seller_id"))
                            .date(resultSet.getDate("date"))
                            .city(resultSet.getString("city"))
                            .mobile_number(resultSet.getString("mobile_number"))
                            .header(resultSet.getString("header"))
                            .description(resultSet.getString("description"))
                            .price(resultSet.getInt("price"))
                            .photo(resultSet.getString("photo"))
                            .build();
                    result.put(id, posting);
                }
            }
            return new ArrayList<>(result.values());
        };
        return jdbcTemplate.execute(query, statementAction);
    }
}
