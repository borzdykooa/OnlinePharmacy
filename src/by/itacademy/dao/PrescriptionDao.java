package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.Prescription;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public final class PrescriptionDao {

    private static final PrescriptionDao INSTANCE = new PrescriptionDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.prescription (name, medicine_id, user_id) VALUES " +
                    "(?,(SELECT id FROM online_pharmacy.medicine WHERE id = ?)," +
                    "(SELECT id FROM online_pharmacy.user WHERE id = ? ))";

    public void save(Prescription prescription) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, prescription.getName());
            preparedStatement.setLong(2, prescription.getMedicine().getId());
            preparedStatement.setLong(3, prescription.getUser().getId());

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                prescription.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PrescriptionDao getInstance() {
        return INSTANCE;
    }
}
