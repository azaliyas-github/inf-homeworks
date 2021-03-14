package ru.itis.restoke.repository.seller;

import ru.itis.restoke.models.Seller;
import ru.itis.restoke.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SellersRepositoryJdbcImpl implements SellersRepository {

    //language - SQL
    private DataSource dataSource;

    public SellersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(Seller entity) {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL_SAVE_USER = "insert into sellers (user_id, general_rating_of_a_seller, role)" +
                " values (?,?,?);";
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SAVE_USER);
            statement.setLong(1, entity.getUser_id());
            statement.setDouble(2, entity.getGeneralRatingOfASeller());
            statement.setInt(3, entity.getRole());
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

    @Override
    public void update(Seller entity) {

    }

    @Override
    public void delete(Seller entity) {

    }

    public <Column> List<Seller> findAllBy(String nameOfDBColumn, Long value) {
        String SQL_FIND_ALL_BY = "SELECT * FROM sellers WHERE " + nameOfDBColumn + " = ?;";
        Connection connection= null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_BY);

            statement.setLong(1, value);
            resultSet = statement.executeQuery();
            List<Seller> result = new ArrayList<>();

            while (resultSet.next()) {
                Seller seller = Seller.builder()
                        .id(resultSet.getLong("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .generalRatingOfASeller(resultSet.getInt("general_rating_of_a_seller"))
                        .role(resultSet.getInt("role"))
                        .build();
                result.add(seller);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException throwables) {
                    //ignore
                    //throwables.printStackTrace();
                }
            }
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
        }
    }

    private <ColumnType> void setWhereParameter(PreparedStatement statement, ColumnType parameter) throws SQLException {
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
    }

    public List<Seller> findAll() {
        String SQL_FIND_ALL = "SELECT * FROM sellers;";
        Connection connection= null;
        Statement statement= null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SQL_FIND_ALL);

            List<Seller> result = new ArrayList<>();

            while (true) {
                assert false;
                if (!resultSet.next()) break;
                Seller seller = Seller.builder()
                        .id(resultSet.getLong("id"))
                        .user_id(resultSet.getInt("user_id"))
                        .generalRatingOfASeller(resultSet.getInt("general_rating_of_a_seller"))
                        .role(resultSet.getInt("role"))
                        .build();
                result.add(seller);
            }
            return result;
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
}