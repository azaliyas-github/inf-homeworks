package ru.itis.restoke.repository.users;

import ru.itis.restoke.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;
        Date date = new Date(System.currentTimeMillis());

        String SQL_SAVE_USER = "insert into users (email, first_name, " +
                "last_name, password, date_of_registration, address)" +
                " values (?,?,?,?,?,?);";
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_SAVE_USER);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPassword());
            statement.setDate(5, entity.getDateOfRegistration());
            statement.setString(6, entity.getAddress());
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

    @Override //TODO
    public void update(User entity)  {
        /*Connection connection = null;
        PreparedStatement statement = null;

        String SQL_UPDATE = "UPDATE users SET " ;
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_UPDATE);
            statement.setString(1, entity.getEmail());
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getPassword());
            statement.setDate(5, entity.getDateOfRegistration());
            statement.setString(6, entity.getAddress());
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
        }*/
    }

    @Override
    public void delete(User entity) {
        Connection connection = null;
        PreparedStatement statement = null;

        String SQL_DELETE_USER = "DELETE FROM users where email = ?";
        try {

            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_DELETE_USER);
            statement.setString(1, entity.getEmail());
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

    public <Column> List<User> findAllBy(String nameOfDBColumn, Column value) {
        String SQL_FIND_ALL_BY = "SELECT * FROM users WHERE " + nameOfDBColumn + " = ?;";
        Connection connection= null;
        PreparedStatement statement= null;
        ResultSet resultSet = null;

        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_FIND_ALL_BY);

            setWhereParameter(statement, value);
            resultSet = statement.executeQuery();
            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .address(resultSet.getString("address"))
                        .dateOfRegistration(resultSet.getDate("date_of_registration"))
                        .build();
                result.add(user);
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

    public List<User> findAll() {
        String SQL_FIND_ALL = "SELECT * FROM users;";
        Connection connection= null;
        Statement statement= null;

        try {
            connection = dataSource.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_FIND_ALL);

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .address(resultSet.getString("address"))
                        .dateOfRegistration(resultSet.getDate("date_of_registration"))
                        .build();
                result.add(user);
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
        }
    }

    public boolean verifyUser(String email, String password) {
        List<User> users = findAllBy("email", email);

        if (users.isEmpty()) {
            return false;
        }

        if (users.get(0).getPassword().equals(password)) {
            return true;
        }

        return false;
    }
}
