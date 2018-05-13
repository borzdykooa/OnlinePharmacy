package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.PersonalData;
import by.itacademy.entity.Role;
import by.itacademy.entity.User;
import by.itacademy.exception.DaoException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonalDataDao {

    private static final PersonalDataDao INSTANCE = new PersonalDataDao();

    private static final String GET_ALL_CLIENTS =
            "SELECT " +
                    "                      pd.id             AS pd_id, " +
                    "                      pd.full_name             AS full_name, " +
                    "                      pd.date_of_birth    AS date_of_birth, " +
                    "                      pd.telephone_number AS telephone_number, " +
                    "                      pd.address          AS address, " +
                    "                      u.id                AS user_id, " +
                    "                      u.login             AS user_login, " +
                    "                      u.password          AS user_password, " +
                    "                      u.role              AS user_role " +
                    "                    FROM online_pharmacy.user u " +
                    "                      INNER JOIN online_pharmacy.personal_data pd ON pd.user_id = u.id ";

    public List<PersonalData> getAllClients() {
        List<PersonalData> personalDataList = new ArrayList<PersonalData>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_CLIENTS);

            while (resultSet.next()) {
                PersonalData personalData = PersonalData.builder()
                        .id(resultSet.getLong("pd_id"))
                        .fullName(resultSet.getString("full_name"))
                        .dateOfBirth(LocalDate.parse(resultSet.getString("date_of_birth")))
                        .telephoneNumber(resultSet.getString("telephone_number"))
                        .address(resultSet.getString("address"))
                        .user(User.builder()
                                .id(resultSet.getLong("user_id"))
                                .login(resultSet.getString("user_login"))
                                .password(resultSet.getString("user_password"))
                                .role(Role.valueOf(resultSet.getString("user_role")))
                                .build())
                        .build();

                personalDataList.add(personalData);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return personalDataList;
    }

    public static PersonalDataDao getInstance() {
        return INSTANCE;
    }
}
