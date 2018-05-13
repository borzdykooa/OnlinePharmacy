package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.*;
import by.itacademy.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String GET_ALL_USERS = "SELECT " +
            "  u.id                AS user_id, " +
            "  u.login             AS user_login, " +
            "  u.password          AS user_password, " +
            "  u.role              AS user_role " +
            "  FROM online_pharmacy.user u ";

    private static final String SAVE =
            "INSERT INTO online_pharmacy.user (login, password, role) VALUES " +
                    "(?,?,?)";
    private static final String SAVE_PERSONAL_DATA =
            "INSERT INTO online_pharmacy.personal_data (full_name,date_of_birth, telephone_number, address, user_id) VALUES " +
                    "(?,?,?,?,(SELECT id FROM online_pharmacy.user WHERE id = ?))";

    public void save(User user, PersonalData personalData) {
        Connection connection = null;
        PreparedStatement userStatement = null;
        PreparedStatement personalDataStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            userStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            personalDataStatement = connection.prepareStatement(SAVE_PERSONAL_DATA);

            userStatement.setString(1, user.getLogin());
            userStatement.setString(2, user.getPassword());
            userStatement.setString(3, Role.CLIENT.getDescription());
            userStatement.executeUpdate();

            ResultSet generatedKeys = userStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }

            personalDataStatement.setString(1, personalData.getFullName());
            personalDataStatement.setDate(2, Date.valueOf(personalData.getDateOfBirth()));
            personalDataStatement.setString(3, personalData.getTelephoneNumber());
            personalDataStatement.setString(4, personalData.getAddress());
            personalDataStatement.setLong(5, user.getId());
            personalDataStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (userStatement != null) {
                    userStatement.close();
                }
                if (personalDataStatement != null) {
                    personalDataStatement.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("user_id"))
                        .login(resultSet.getString("user_login"))
                        .password(resultSet.getString("user_password"))
                        .role(Role.valueOf(resultSet.getString("user_role")))
                        .build();
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return users;
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}
