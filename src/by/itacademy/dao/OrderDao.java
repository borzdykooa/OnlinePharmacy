package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.Medicine;
import by.itacademy.entity.Order;
import by.itacademy.entity.OrderMedicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.order (date_of_order, order_clothing_date, status, user_id) " +
                    "VALUES (?, ?, ?, " +
                    "        (SELECT id " +
                    "         FROM online_pharmacy.user " +
                    "         WHERE id = ?))";

    private static final String SAVE_ORDER_MEDICINE =
            "INSERT INTO online_pharmacy.order_medicine (order_id, medicine_id, quantity) VALUES " +
                    "  ((SELECT id " +
                    "    FROM online_pharmacy.order " +
                    "    WHERE id = ?), (SELECT id " +
                    "                    FROM online_pharmacy.medicine " +
                    "                    WHERE id = ?), ?)";

    public void save(Order order, OrderMedicine orderMedicine) {
        Connection connection = null;
        PreparedStatement orderStatement = null;
        PreparedStatement relationStatement = null;
        try {
            connection = ConnectionPool.getConnection();
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            relationStatement = connection.prepareStatement(SAVE_ORDER_MEDICINE);

            orderStatement.setDate(1, Date.valueOf(order.getDateOfOrder()));
            orderStatement.setDate(2, Date.valueOf(order.getOrderClothingDate()));
            orderStatement.setString(3, order.getStatus().getDescription());
            orderStatement.setLong(4, order.getUser().getId());
            orderStatement.executeUpdate();

            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }

            relationStatement.setLong(1, orderMedicine.getOrder().getId());
            relationStatement.setLong(2, orderMedicine.getMedicine().getId());
            relationStatement.setLong(3, orderMedicine.getQuantity());
            relationStatement.executeUpdate();

            connection.commit();

        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
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
                if (relationStatement != null) {
                    relationStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static final String DELETE_ORDER_MEDICINE = "DELETE FROM order_medicine WHERE order_id IN " +
            "(SELECT id FROM online_pharmacy.order WHERE date_of_order<?)";

    private static final String DELETE = "DELETE FROM online_pharmacy.order WHERE date_of_order<?";


//    private static final String DELETE_RESTAURANT_DISH =
//            "DELETE FROM restaurant_storage.restaurant_dish WHERE dish_id = ?";

//    private static final String DELETE = "DELETE FROM restaurant_storage.dish WHERE id = ?";

//    public void delete(Long id) {
//        Connection connection = null;
//        PreparedStatement restaurantDishStatement = null;
//        PreparedStatement dishStatement = null;
//        try {
//            connection = ConnectionManager.getConnection();
//            connection.setAutoCommit(false);
//
//            restaurantDishStatement = connection.prepareStatement(DELETE_RESTAURANT_DISH);
//            restaurantDishStatement.setLong(1, id);
//            restaurantDishStatement.executeUpdate();
//
//            dishStatement = connection.prepareStatement(DELETE);
//            dishStatement.setLong(1, id);
//            dishStatement.executeUpdate();
//
//            connection.commit();
//        } catch (SQLException e) {
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (restaurantDishStatement != null) {
//                    restaurantDishStatement.close();
//                }
//                if (dishStatement != null) {
//                    dishStatement.close();
//                }
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//        }
//    }


    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
