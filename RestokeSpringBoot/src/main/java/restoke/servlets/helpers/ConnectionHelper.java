package restoke.servlets.helpers;

import com.zaxxer.hikari.*;

public class ConnectionHelper {
    public static HikariDataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:postgresql://localhost:5432/restoke");
        hikariConfig.setDriverClassName("org.postgresql.Driver");
        hikariConfig.setUsername("postgres");
        hikariConfig.setPassword("Postgres");
        return new HikariDataSource(hikariConfig);
    }

}
