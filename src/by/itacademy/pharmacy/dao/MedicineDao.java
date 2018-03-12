package by.itacademy.pharmacy.dao;

import by.itacademy.pharmacy.entity.Group;
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
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class MedicineDao {

    private static final MedicineDao INSTANCE = new MedicineDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.medicine (name,description,price,quantity,group_id) VALUES " +
                    "(?,?,?,?,(SELECT id FROM online_pharmacy.group WHERE name = ?))";

    private static final String GET_ALL_MEDICINES =
            "SELECT m.id AS medicine_id, m.name as medicine_name, m.description AS medicine_description, " +
                    "m.price AS medicine_price, m.quantity AS medicine_quantity, g.id AS group_id, g.name AS group_name " +
                    "FROM online_pharmacy.medicine m " +
                    "INNER JOIN online_pharmacy.group g ON m.group_id=g.id";

    private static final String GET_ORDER_BY_MEDICINE_NAME = "SELECT m.id AS medicine_id, m.name AS medicine_name, " +
            "o.id AS order_id, o.date_of_order AS date_of_order, o.order_clothing_date AS order_clothing_date, " +
            "o.quantity AS order_quantity " +
            "FROM online_pharmacy.medicine m " +
            "INNER JOIN online_pharmacy.order_medicine om ON om.medicine_id=m.id " +
            "INNER JOIN online_pharmacy.order o ON om.order_id=o.id " +
            "WHERE m.name =?";

    public Medicine getOrderByName(String medicineName) {
        Medicine medicine = null;
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_MEDICINE_NAME)) {
            preparedStatement.setString(1, medicineName);
            ResultSet resultSet = preparedStatement.executeQuery();

            medicine = new Medicine();
            while (resultSet.next()) {
                medicine.setId(resultSet.getLong("medicine_id"));
                medicine.setName(resultSet.getString("medicine_name"));
                medicine.getOrders().add(Order.builder()
                        .id(resultSet.getLong("order_id"))
                        .dateOfOrder(resultSet.getDate("date_of_order"))
                        .orderClothingDate(resultSet.getDate("order_clothing_date"))
                        .quantity(resultSet.getInt("order_quantity"))
                        .build());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicine;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<Medicine>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_MEDICINES);

            while (resultSet.next()) {
                Medicine medicine = Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .description(resultSet.getString("medicine_description"))
                        .price(resultSet.getBigDecimal("medicine_price"))
                        .quantity(resultSet.getInt("medicine_quantity"))
                        .group(Group.builder()
                                .id(resultSet.getLong("group_id"))
                                .name(resultSet.getString("group_name"))
                                .build())
                        .build();

                medicines.add(medicine);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicines;
    }

    public void save(Medicine medicine) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, medicine.getName());
            preparedStatement.setString(2, medicine.getDescription());
            preparedStatement.setBigDecimal(3, medicine.getPrice());
            preparedStatement.setInt(4, medicine.getQuantity());
            preparedStatement.setString(5, medicine.getGroup().getName());


            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                medicine.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MedicineDao getInstance() {
        return INSTANCE;
    }
}
