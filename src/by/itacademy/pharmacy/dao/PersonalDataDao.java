package by.itacademy.pharmacy.dao;

import by.itacademy.pharmacy.entity.PersonalData;
import by.itacademy.pharmacy.util.ConnectionUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonalDataDao {

    private static final PersonalDataDao INSTANCE = new PersonalDataDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.personal_data (date_of_birth, telephone_number, address, user_id) VALUES " +
                    "(?,?,?,(SELECT id FROM online_pharmacy.user WHERE login = ?))";

    public void save(PersonalData personalData) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setDate(1, personalData.getDateOfBirth());
            preparedStatement.setString(2, personalData.getTelephoneNumber());
            preparedStatement.setString(3, personalData.getAddress());
            preparedStatement.setString(4, personalData.getUser().getLogin());


            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                personalData.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static PersonalDataDao getInstance() {
        return INSTANCE;
    }
}
