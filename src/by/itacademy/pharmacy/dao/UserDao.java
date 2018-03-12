package by.itacademy.pharmacy.dao;

import by.itacademy.pharmacy.entity.Medicine;
import by.itacademy.pharmacy.entity.Order;
import by.itacademy.pharmacy.entity.Role;
import by.itacademy.pharmacy.entity.User;
import by.itacademy.pharmacy.util.ConnectionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.user (surname, name, patronymic, login, password, role_id) VALUES " +
                    "(?,?,?,?,?,(SELECT id FROM online_pharmacy.role WHERE name = ?))";

    private static final String GET_ALL_USERS =
            "SELECT u.id AS user_id, u.surname as user_surname, u.name as user_name, u.patronymic AS user_patronymic, " +
                    "u.login AS user_login, u.password AS user_password, r.id AS role_id, r.name AS role_name " +
                    "FROM online_pharmacy.user u " +
                    "INNER JOIN online_pharmacy.role r ON u.role_id=r.id";

    private static final String GET_ORDER_BY_USER_LOGIN = "SELECT u.id AS user_id, u.surname AS user_surname, " +
            "u.name AS user_name, u.patronymic AS user_patronymic, u.login AS user_login, " +
            "u.password AS user_password, o.id AS order_id, o.date_of_order AS date_of_order, " +
            "o.order_clothing_date AS order_clothing_date,o.quantity AS order_quantity,  " +
            "m.id AS medicine_id, m.name AS medicine_name " +
            "FROM online_pharmacy.user u " +
            "INNER JOIN online_pharmacy.order o ON u.id= o.user_id " +
            "INNER JOIN online_pharmacy.order_medicine om ON om.order_id=o.id " +
            "INNER JOIN online_pharmacy.medicine m ON om.medicine_id=m.id " +
            "WHERE u.login = ? ";

    public User getOrderByLogin(String userLogin) {
       User user=null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_LOGIN)) {
            preparedStatement.setString(1, userLogin);
            ResultSet resultSet = preparedStatement.executeQuery();

            user=new User();
            while (resultSet.next()) {
                user.setId(resultSet.getLong("user_id"));
                user.setSurname(resultSet.getString("user_surname"));
                user.setName(resultSet.getString("user_name"));
                user.setPatronymic(resultSet.getString("user_patronymic"));
                user.setLogin(resultSet.getString("user_login"));
                user.setPassword(resultSet.getString("user_password"));
                user.getOrders().add(Order.builder()
                        .id(resultSet.getLong("order_id"))
                        .dateOfOrder(resultSet.getDate("date_of_order"))
                        .orderClothingDate(resultSet.getDate("order_clothing_date"))
                        .quantity(resultSet.getInt("order_quantity"))
                        .build());
                user.getMedicines().add(Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<User>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS);

            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("user_id"))
                        .surname(resultSet.getString("user_surname"))
                        .name(resultSet.getString("user_name"))
                        .patronymic(resultSet.getString("user_patronymic"))
                        .login(resultSet.getString("user_login"))
                        .password(resultSet.getString("user_password"))
                        .role(Role.builder()
                                .id(resultSet.getInt("role_id"))
                                .name(resultSet.getString("role_name"))
                                .build())
                        .build();

                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void save(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPatronymic());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getRole().getName());


            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}