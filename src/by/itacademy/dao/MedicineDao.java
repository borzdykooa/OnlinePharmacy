package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.Group;
import by.itacademy.entity.Medicine;
import by.itacademy.exception.DaoException;
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
                    "(?,?,?,?,(SELECT id FROM online_pharmacy.group WHERE id = ?))";

    private static final String GET_ALL_MEDICINES =
            "SELECT " +
                    "  m.id          AS medicine_id, " +
                    "  m.name        AS medicine_name, " +
                    "  m.description AS medicine_description, " +
                    "  m.price       AS medicine_price, " +
                    "  m.quantity    AS medicine_quantity, " +
                    "  g.name        AS group_name " +
                    "FROM online_pharmacy.medicine m " +
                    "  INNER JOIN online_pharmacy.group g ON m.group_id = g.id " +
                    "ORDER BY medicine_name";

    private static final String GET_BY_MEDICINE_ID = "SELECT " +
            "  m.id          AS medicine_id, " +
            "  m.name        AS medicine_name, " +
            "  m.description AS medicine_description, " +
            "  m.price       AS medicine_price, " +
            "  m.quantity    AS medicine_quantity, " +
            "  g.name        AS group_name " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.group g ON m.group_id = g.id " +
            "WHERE m.id = ?";

    private static final String GET_MEDICINE_BY_GROUP_ID = "SELECT " +
            "  m.id          AS medicine_id, " +
            "  m.name        AS medicine_name " +
            "FROM online_pharmacy.medicine m " +
            "WHERE group_id=?";

    private static final String DELETE = "DELETE FROM online_pharmacy.medicine WHERE id = ?";

    private static final String UPDATE_QUANTITY = "UPDATE online_pharmacy.medicine SET quantity = ? WHERE id = ?";

    private static final String GET_MEDICINE_BY_PART_NAME = "SELECT " +
            "  m.id          AS medicine_id, " +
            "  m.name        AS medicine_name, " +
            "  m.description AS medicine_description, " +
            "  m.price       AS medicine_price, " +
            "  m.quantity    AS medicine_quantity, " +
            "  g.name        AS group_name " +
            "FROM online_pharmacy.medicine m " +
            "  INNER JOIN online_pharmacy.group g ON m.group_id = g.id " +
            "WHERE m.name ILIKE ?";

    public List<Medicine> getMedicinesByPartName(String partName) {
        List<Medicine> medicines = new ArrayList<Medicine>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MEDICINE_BY_PART_NAME)) {
            preparedStatement.setString(1, partName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Medicine medicine = Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .description(resultSet.getString("medicine_description"))
                        .price(resultSet.getDouble("medicine_price"))
                        .quantity(resultSet.getInt("medicine_quantity"))
                        .group(Group.builder()
                                .name(resultSet.getString("group_name"))
                                .build())
                        .build();

                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return medicines;
    }

    public void updateQuantity(Medicine medicine) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY)) {
            preparedStatement.setLong(1, medicine.getQuantity());
            preparedStatement.setLong(2, medicine.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Medicine> getMedicinesByGroupId(Long groupId) {
        List<Medicine> medicines = new ArrayList<Medicine>();
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_MEDICINE_BY_GROUP_ID)) {
            preparedStatement.setLong(1, groupId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Medicine medicine = Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .build();

                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return medicines;
    }

    public void delete(Long id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public Medicine getByMedicineID(Long medicineId) {
        Medicine medicine = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_MEDICINE_ID)) {
            preparedStatement.setLong(1, medicineId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                medicine = Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .description(resultSet.getString("medicine_description"))
                        .price(resultSet.getDouble("medicine_price"))
                        .quantity(resultSet.getInt("medicine_quantity"))
                        .group(Group.builder()
                                .name(resultSet.getString("group_name"))
                                .build())
                        .build();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return medicine;
    }

    public List<Medicine> getAllMedicines() {
        List<Medicine> medicines = new ArrayList<Medicine>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_MEDICINES);

            while (resultSet.next()) {
                Medicine medicine = Medicine.builder()
                        .id(resultSet.getLong("medicine_id"))
                        .name(resultSet.getString("medicine_name"))
                        .description(resultSet.getString("medicine_description"))
                        .price(resultSet.getDouble("medicine_price"))
                        .quantity(resultSet.getInt("medicine_quantity"))
                        .group(Group.builder()
                                .name(resultSet.getString("group_name"))
                                .build())
                        .build();
                medicines.add(medicine);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return medicines;
    }

    public void save(Medicine medicine) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, medicine.getName());
            preparedStatement.setString(2, medicine.getDescription());
            preparedStatement.setDouble(3, medicine.getPrice());
            preparedStatement.setInt(4, medicine.getQuantity());
            preparedStatement.setLong(5, medicine.getGroup().getId());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                medicine.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static MedicineDao getInstance() {
        return INSTANCE;
    }
}
