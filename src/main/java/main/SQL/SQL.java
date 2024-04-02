package main.SQL;


import main.User.User;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
//192.168.0.47:5432/postgres
@SpringBootApplication
public class SQL {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    public User select(String login) {
        User user = null;
        Connection connection = null;
        String query = "SELECT * FROM task1 JOIN task2 USING (login) WHERE login = '" + login + "'";

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                user = new User(
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("date"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;

    }

    public int insert(User user) {
        int rowsUpdated = 0;
        String insertQuery = "INSERT INTO task1 (login, password, date) VALUES (?, ?, ?); " +
                "INSERT INTO task2 (login, email) VALUES (?, ?);";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setDate(3, java.sql.Date.valueOf(user.getTimestamp()));
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getEmail());
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsUpdated;
    }
}
