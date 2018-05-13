package by.itacademy.dao;

import by.itacademy.connection.ConnectionPool;
import by.itacademy.entity.Group;
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
public final class GroupDao {

    private static final GroupDao INSTANCE = new GroupDao();

    private static final String SAVE =
            "INSERT INTO online_pharmacy.group (name) VALUES (?)";

    private static final String GET_ALL_GROUPS =
            "SELECT " +
                    "  g.id          AS group_id, " +
                    "  g.name        AS group_name " +
                    "FROM online_pharmacy.group g " +
                    "ORDER BY group_name";

    public List<Group> getAllGroups() {
        List<Group> groups = new ArrayList<Group>();
        try (Connection connection = ConnectionPool.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_ALL_GROUPS);

            while (resultSet.next()) {
                Group group = Group.builder()
                        .id(resultSet.getLong("group_id"))
                        .name(resultSet.getString("group_name"))
                        .build();

                groups.add(group);
            }

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        return groups;
    }

    public void save(Group group) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, group.getName());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                group.setId(generatedKeys.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static GroupDao getInstance() {
        return INSTANCE;
    }
}
