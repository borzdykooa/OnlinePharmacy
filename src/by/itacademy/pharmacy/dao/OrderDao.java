package by.itacademy.pharmacy.dao;

import by.itacademy.pharmacy.entity.Medicine;
import by.itacademy.pharmacy.entity.Order;
import by.itacademy.pharmacy.util.ConnectionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class OrderDao {

    private static final OrderDao INSTANCE = new OrderDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.order (date_of_order, order_clothing_date, quantity, status_id, user_id) " +
                    "VALUES (?,?,?,(SELECT id FROM online_pharmacy.status WHERE name = ?)," +
                    "(SELECT id FROM online_pharmacy.user WHERE login = ? ))";

    private static final String SAVE_ORDER_MEDICINE =
            "INSERT INTO online_pharmacy.order_medicine (order_id, medicine_id) VALUES " +
                    "(?,(SELECT id FROM online_pharmacy.medicine WHERE name = ? ));";



    private static final String DELETE_ORDER_MEDICINE = "DELETE FROM order_medicine WHERE order_id IN " +
            "(SELECT id FROM online_pharmacy.order WHERE date_of_order<?)";

    private static final String DELETE = "DELETE FROM online_pharmacy.order WHERE date_of_order<?";

//    public void delete(Order order, Medicine medicine) {
//        Connection connection = null;
//        PreparedStatement orderStatement = null;
//        PreparedStatement relationStatement = null;
//        try {
//            connection = ConnectionUtil.getConnection();
//            connection.setAutoCommit(false);
//            orderStatement = connection.prepareStatement(DELETE, Statement.RETURN_GENERATED_KEYS);
//            relationStatement = connection.prepareStatement(DELETE_ORDER_MEDICINE);
//
//            orderStatement.setDate(1, order.getDateOfOrder());
//            orderStatement.();
//
//            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
//            if (generatedKeys.next()) {
//                order.setId(generatedKeys.getLong("id"));
//            }
//
//            relationStatement.setLong(1, order.getId());
//            relationStatement.setString(2, medicine.getName());
//            relationStatement.executeUpdate();
//
//            connection.commit();
//
//            medicine.getOrders().add(order);
//
//        } catch (SQLException e) {
//            try {
//                if (connection != null) {
//                    connection.rollback();
//                }
//            } catch (SQLException e1) {
//                e1.printStackTrace();
//            }
//            e.printStackTrace();
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//                if (orderStatement != null) {
//                    orderStatement.close();
//                }
//                if (relationStatement != null) {
//                    relationStatement.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }



    public void save(Order order, Medicine medicine) {
        Connection connection = null;
        PreparedStatement orderStatement = null;
        PreparedStatement relationStatement = null;
        try {
            connection = ConnectionUtil.getConnection();
            connection.setAutoCommit(false);
            orderStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);
            relationStatement = connection.prepareStatement(SAVE_ORDER_MEDICINE);

            orderStatement.setDate(1, order.getDateOfOrder());
            orderStatement.setDate(2, order.getOrderClothingDate());
            orderStatement.setInt(3, order.getQuantity());
            orderStatement.setString(4, order.getStatus().getName());
            orderStatement.setString(5, order.getUser().getLogin());
            orderStatement.executeUpdate();

            ResultSet generatedKeys = orderStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                order.setId(generatedKeys.getLong("id"));
            }

            relationStatement.setLong(1, order.getId());
            relationStatement.setString(2, medicine.getName());
            relationStatement.executeUpdate();

            connection.commit();

//            medicine.getOrders().add(order);

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

    public static OrderDao getInstance() {
        return INSTANCE;
    }
}
