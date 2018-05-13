package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.*;
import by.itacademy.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMedicineDao {

    private static final OrderMedicineDao INSTANCE = new OrderMedicineDao();

    private static final String GET_ORDER_BY_MEDICINE_ID = "SELECT " +
            "  m.id                  AS medicine_id, " +
            "  m.name                AS medicine_name, " +
            "  m.price                AS medicine_price, " +
            "  om.id                 AS order_medicine_id, " +
            "  om.quantity           AS order_medicine_quantity, " +
            "  o.id                  AS order_id, " +
            "  o.date_of_order       AS date_of_order, " +
            "  o.order_clothing_date AS order_clothing_date, " +
            "  o.status              AS order_status, " +
            "  o.total_sum              AS order_total_sum, " +
            "  u.id                  AS user_id, " +
            "  u.login               AS user_login " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.order_medicine om ON om.medicine_id = m.id " +
            "  INNER JOIN online_pharmacy.order o ON om.order_id = o.id " +
            "  INNER JOIN online_pharmacy.user u ON o.user_id = u.id " +
            "WHERE m.id = ?";

    private static final String GET_ORDER_BY_USER_ID = "SELECT " +
            "  m.id                  AS medicine_id, " +
            "  m.name                AS medicine_name, " +
            "  m.price                AS medicine_price, " +
            "  om.id                 AS order_medicine_id, " +
            "  om.quantity           AS order_medicine_quantity, " +
            "  o.id                  AS order_id, " +
            "  o.date_of_order       AS date_of_order, " +
            "  o.order_clothing_date AS order_clothing_date, " +
            "  o.status              AS order_status, " +
            "  o.total_sum              AS order_total_sum, " +
            "  u.id                  AS user_id, " +
            "  u.login               AS user_login " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.order_medicine om ON om.medicine_id = m.id " +
            "  INNER JOIN online_pharmacy.order o ON om.order_id = o.id " +
            "  INNER JOIN online_pharmacy.user u ON o.user_id = u.id " +
            "WHERE u.id = ?";

    private static final String GET_ALL_ORDERS = "SELECT " +
            "  m.id                  AS medicine_id, " +
            "  m.name                AS medicine_name, " +
            "  m.price                AS medicine_price, " +
            "  om.id                 AS order_medicine_id, " +
            "  om.quantity           AS order_medicine_quantity, " +
            "  o.id                  AS order_id, " +
            "  o.date_of_order       AS date_of_order, " +
            "  o.order_clothing_date AS order_clothing_date, " +
            "  o.status              AS order_status, " +
            "  o.total_sum              AS order_total_sum, " +
            "  u.id                  AS user_id, " +
            "  u.login               AS user_login " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.order_medicine om ON om.medicine_id = m.id " +
            "  INNER JOIN online_pharmacy.order o ON om.order_id = o.id " +
            "  INNER JOIN online_pharmacy.user u ON o.user_id = u.id ";

    private static final String DELETE_ORDER_MEDICINE = "DELETE FROM online_pharmacy.order_medicine " +
            "WHERE order_id IN (SELECT id " +
            "                   FROM online_pharmacy.order " +
            "                   WHERE date_of_order < ?)";

    private static final String DELETE = "DELETE FROM online_pharmacy.order " +
            "WHERE date_of_order < ?";

    private static final String GET_ALL_PROCESSED_ORDERS = "SELECT " +
            "  m.id                  AS medicine_id, " +
            "  m.name                AS medicine_name, " +
            "  m.price                AS medicine_price, " +
            "  om.id                 AS order_medicine_id, " +
            "  om.quantity           AS order_medicine_quantity, " +
            "  o.id                  AS order_id, " +
            "  o.date_of_order       AS date_of_order, " +
            "  o.order_clothing_date AS order_clothing_date, " +
            "  o.total_sum              AS order_total_sum, " +
            "  u.id                  AS user_id, " +
            "  u.login               AS user_login " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.order_medicine om ON om.medicine_id = m.id " +
            "  INNER JOIN online_pharmacy.order o ON om.order_id = o.id " +
            "  INNER JOIN online_pharmacy.user u ON o.user_id = u.id " +
            "WHERE o.status = 'PROCESSED'";

    public List<OrderMedicine> getAllProcessedOrders() {
        List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_PROCESSED_ORDERS);

            while (resultSet.next()) {
                OrderMedicine orderMedicine = OrderMedicine.builder()
                        .id(resultSet.getLong("order_medicine_id"))
                        .quantity(resultSet.getInt("order_medicine_quantity"))
                        .order(Order.builder()
                                .id(resultSet.getLong("order_id"))
                                .dateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")))
                                .orderClothingDate(LocalDate.parse(resultSet.getString("order_clothing_date")))
                                .totalSum(resultSet.getDouble("order_total_sum"))
                                .user(User.builder()
                                        .id(resultSet.getLong("user_id"))
                                        .login(resultSet.getString("user_login"))
                                        .build())
                                .build())
                        .medicine(Medicine.builder()
                                .id(resultSet.getLong("medicine_id"))
                                .name(resultSet.getString("medicine_name"))
                                .price(resultSet.getDouble("medicine_price"))
                                .build())
                        .build();
                orderMedicines.add(orderMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orderMedicines;
    }

    public void delete(LocalDate date) {
        Connection connection = null;
        PreparedStatement orderMedicineStatement = null;
        PreparedStatement orderStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);

            orderMedicineStatement = connection.prepareStatement(DELETE_ORDER_MEDICINE);
            orderMedicineStatement.setDate(1, Date.valueOf(date));
            orderMedicineStatement.executeUpdate();

            orderStatement = connection.prepareStatement(DELETE);
            orderStatement.setDate(1, Date.valueOf(date));
            orderStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException e1) {
                    throw new DaoException(e1);
                }
            }
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (orderMedicineStatement != null) {
                    orderMedicineStatement.close();
                }
                if (orderStatement != null) {
                    orderStatement.close();
                }
            } catch (SQLException e1) {
                throw new DaoException(e1);
            }
        }
    }

    public List<OrderMedicine> getOrderByUserId(Long userId) {
        List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderMedicine orderMedicine = OrderMedicine.builder()
                        .id(resultSet.getLong("order_medicine_id"))
                        .quantity(resultSet.getInt("order_medicine_quantity"))
                        .order(Order.builder()
                                .id(resultSet.getLong("order_id"))
                                .dateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")))
                                .orderClothingDate(LocalDate.parse(resultSet.getString("order_clothing_date")))
                                .status(Status.valueOf(resultSet.getString("order_status")))
                                .totalSum(resultSet.getDouble("order_total_sum"))
                                .user(User.builder()
                                        .id(resultSet.getLong("user_id"))
                                        .login(resultSet.getString("user_login"))
                                        .build())
                                .build())
                        .medicine(Medicine.builder()
                                .id(resultSet.getLong("medicine_id"))
                                .name(resultSet.getString("medicine_name"))
                                .price(resultSet.getDouble("medicine_price"))
                                .build())
                        .build();
                orderMedicines.add(orderMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orderMedicines;
    }

    public List<OrderMedicine> getAllOrders() {
        List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS);

            while (resultSet.next()) {
                OrderMedicine orderMedicine = OrderMedicine.builder()
                        .id(resultSet.getLong("order_medicine_id"))
                        .quantity(resultSet.getInt("order_medicine_quantity"))
                        .order(Order.builder()
                                .id(resultSet.getLong("order_id"))
                                .dateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")))
                                .orderClothingDate(LocalDate.parse(resultSet.getString("order_clothing_date")))
                                .status(Status.valueOf(resultSet.getString("order_status")))
                                .totalSum(resultSet.getDouble("order_total_sum"))
                                .user(User.builder()
                                        .id(resultSet.getLong("user_id"))
                                        .login(resultSet.getString("user_login"))
                                        .build())
                                .build())
                        .medicine(Medicine.builder()
                                .id(resultSet.getLong("medicine_id"))
                                .name(resultSet.getString("medicine_name"))
                                .price(resultSet.getDouble("medicine_price"))
                                .build())
                        .build();
                orderMedicines.add(orderMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orderMedicines;
    }

    public List<OrderMedicine> getOrderByMedicineId(Long medicineId) {
        List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_MEDICINE_ID)) {
            preparedStatement.setLong(1, medicineId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                OrderMedicine orderMedicine = OrderMedicine.builder()
                        .id(resultSet.getLong("order_medicine_id"))
                        .quantity(resultSet.getInt("order_medicine_quantity"))
                        .order(Order.builder()
                                .id(resultSet.getLong("order_id"))
                                .dateOfOrder(LocalDate.parse(resultSet.getString("date_of_order")))
                                .orderClothingDate(LocalDate.parse(resultSet.getString("order_clothing_date")))
                                .status(Status.valueOf(resultSet.getString("order_status")))
                                .totalSum(resultSet.getDouble("order_total_sum"))
                                .user(User.builder()
                                        .id(resultSet.getLong("user_id"))
                                        .login(resultSet.getString("user_login"))
                                        .build())
                                .build())
                        .medicine(Medicine.builder()
                                .id(resultSet.getLong("medicine_id"))
                                .name(resultSet.getString("medicine_name"))
                                .price(resultSet.getDouble("medicine_price"))
                                .build())
                        .build();
                orderMedicines.add(orderMedicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return orderMedicines;
    }

    public static OrderMedicineDao getInstance() {
        return INSTANCE;
    }
}
