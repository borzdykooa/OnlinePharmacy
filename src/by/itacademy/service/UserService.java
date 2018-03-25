package by.itacademy.service;


import by.itacademy.dao.MedicineDao;
import by.itacademy.dao.PersonalDataDao;
import by.itacademy.dao.UserDao;
import by.itacademy.dto.UserDto;
import by.itacademy.entity.Medicine;
import by.itacademy.entity.PersonalData;
import by.itacademy.entity.Role;
import by.itacademy.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public void save(UserDto dto) {
        User request = User.builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
//                .role(Role.valueOf(dto.getRole()))
                .build();
        UserDao.getInstance().save(request);

    }

    public List<User> findAllClients() {
        return UserDao.getInstance().getAllClients();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
