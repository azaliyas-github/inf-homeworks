package ru.itis.restoke.repository.posting;

import ru.itis.restoke.models.Posting;

import javax.sql.DataSource;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostingsRepositoryJdbcImpl implements PostingsRepository {

    private final DataSource dataSource;

    public PostingsRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Posting entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        Date date = new Date(System.currentTimeMillis());
        String SQL_SAVE_PHOTO = "insert into photo (ref, posting_id)" +
                " values " + "(?,?);";

        String SQL_SAVE_POSTING = "insert into postings (category_id, " +
                "subcategory_id, seller_id, date, city, header, description, price, mobile_number)" +
                " values " + "(?,?,?,?,?,?,?,?,?);";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SAVE_POSTING, Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getCategoryId());
            statement.setLong(2, entity.getSubCategoryId());
            statement.setLong(3, entity.getSellerId());
            statement.setDate(4, entity.getDateOfPublishing());
            statement.setString(5, entity.getAddress());
            statement.setString(6, entity.getHeader());
            statement.setString(7, entity.getDescription());
            statement.setInt(8, entity.getPrice());
            statement.setString(9, entity.getMobileNumber());
            statement.executeUpdate();

            ResultSet generatedIds = statement.getGeneratedKeys();
            generatedIds.next();
            statement = connection.prepareStatement(SQL_SAVE_PHOTO);
            statement.setString(1, entity.getPhoto());
            statement.setInt(2, generatedIds.getInt("id"));

            statement.executeUpdate();

        } catch ( SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                }
        }
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

    @Override
    public void update(Posting entity) {

    }

    @Override
    public void delete(Posting entity) {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL_DELETE_BULLETIN = "DELETE FROM postings where id = ?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_BULLETIN);
            statement.setLong(1, entity.getId());
            statement.executeUpdate();
        } catch ( SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException throwables) {
                    //ignore
                }
            }
            if (statement != null)
                try {
                    statement.close();
                } catch (SQLException throwables) {
                    //ignore
                }
        }
    }

    public List<Posting> finder(String SQL_REQUEST) {
        Connection connection= null;
        Statement statement= null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_REQUEST);

            HashMap<Long, Posting> result = new HashMap<>();

            if (resultSet == null) {
                new ArrayList<Posting>();
            }
            while (true) {
                if (!resultSet.next()) break;
                long id = resultSet.getLong("id");
                Posting posting = null;
                if (!result.containsKey(id)) {
                        posting = Posting.builder()
                            .id(id)
                            .categoryId(resultSet.getLong("category_id"))
                            .subCategoryId(resultSet.getLong("subcategory_id"))
                            .sellerId(resultSet.getLong("seller_id"))
                            .dateOfPublishing(resultSet.getDate("date"))
                            .address(resultSet.getString("city"))
                            .mobileNumber(resultSet.getString("mobile_number"))
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

    /*private <ColumnType> void setWhereParameter(PreparedStatement statement, ColumnType parameter) throws SQLException {
        Class<?> parameterClass = parameter.getClass();
        if (String.class.isAssignableFrom(parameterClass))
            statement.setString(1, (String)parameter);
        else if (Long.class.isAssignableFrom(parameterClass)) {
            statement.setLong(1, (Long)parameter);
        }
        else if (Double.class.isAssignableFrom(parameterClass)) {
            statement.setDouble(1, (Double)parameter);
        }
        else if (Integer.class.isAssignableFrom(parameterClass)) {
            statement.setInt(1, (Integer) parameter);
        }
        else if (Date.class.isAssignableFrom(parameterClass)) {
            statement.setDate(1, (Date)parameter);
        }
        else {
            throw new IllegalArgumentException("Метод не принимает такой тип");
        }
    }*/
}
