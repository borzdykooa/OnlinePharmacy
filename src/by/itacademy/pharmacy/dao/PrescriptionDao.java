package by.itacademy.pharmacy.dao;

import by.itacademy.pharmacy.entity.Prescription;
import by.itacademy.pharmacy.util.ConnectionUtil;
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
                    "(?,(SELECT id FROM online_pharmacy.medicine WHERE name = ?)," +
                    "(SELECT id FROM online_pharmacy.user WHERE login = ? ))";

    public void save(Prescription prescription) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, prescription.getName());
            preparedStatement.setString(2, prescription.getMedicine().getName());
            preparedStatement.setString(3, prescription.getUser().getLogin());

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
