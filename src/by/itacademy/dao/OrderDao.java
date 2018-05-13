package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.*;
import by.itacademy.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private static final String UPDATE_STATUS_AND_DATE = "UPDATE online_pharmacy.order SET order_clothing_date =?, " +
            "status=? WHERE id = ?";

    private static final String SAVE =
            "INSERT INTO online_pharmacy.order (date_of_order, order_clothing_date, status, total_sum, user_id) " +
                    "VALUES (?, '2100-01-01',?, ?, " +
                    "        (SELECT id " +
                    "         FROM online_pharmacy.user " +
                    "         WHERE id = ?))";

    private static final String SAVE_ORDER_MEDICINE =
            "INSERT INTO online_pharmacy.order_medicine (order_id, medicine_id, quantity) VALUES " +
                    "  ((SELECT id " +
                    "    FROM online_pharmacy.order " +
                    "    WHERE id = ?), (SELECT id " +
                    "                    FROM online_pharmacy.medicine " +
                    "                    WHERE id = ?),?)";

    public void save(Order order, List<OrderMedicine> orderMedicines) {
        Connection connection = null;
        PreparedStatement orderStatement = null;
        PreparedStatement orderMedicineStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            orderMedicineStatement = connection.prepareStatement(SAVE_ORDER_MEDICINE);

            orderStatement.setDate(1, Date.valueOf(LocalDate.now()));
            orderStatement.setString(2, Status.PROCESSED.getDescription());
            orderStatement.setDouble(3, order.getTotalSum());
            orderStatement.setLong(4, order.getUser().getId());
            orderStatement.executeUpdate();

            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }

            for (OrderMedicine orderMedicine : orderMedicines) {
                orderMedicineStatement.setLong(1, order.getId());
                orderMedicineStatement.setLong(2, orderMedicine.getMedicine().getId());
                orderMedicineStatement.setLong(3, orderMedicine.getQuantity());
                orderMedicineStatement.executeUpdate();
            }
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
                if (orderStatement != null) {
                    orderStatement.close();
                }
                if (orderMedicineStatement != null) {
                    orderMedicineStatement.close();
                }
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    public void updateStatusAndDate(Order order) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATUS_AND_DATE)) {
            preparedStatement.setDate(1, Date.valueOf(order.getOrderClothingDate()));
            preparedStatement.setString(2, Status.DONE.getDescription());
            preparedStatement.setLong(3, order.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
