package by.itacademy.service;

import by.itacademy.dao.UserDao;
import by.itacademy.dto.PersonalDataDto;
import by.itacademy.dto.UserDto;
import by.itacademy.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    private static final UserService INSTANCE = new UserService();

    public void save(UserDto userDto, PersonalDataDto personalDataDto) {
        User userRequest = User.builder()
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .build();
        PersonalData personalDataRequest = PersonalData.builder()
                .fullName(personalDataDto.getFullName())
                .dateOfBirth(LocalDate.parse(personalDataDto.getDateOfBirth()))
                .telephoneNumber(personalDataDto.getTelephoneNumber())
                .address(personalDataDto.getAddress())
                .build();

        UserDao.getInstance().save(userRequest, personalDataRequest);
    }

    public List<User> findAllUsers() {
        return UserDao.getInstance().getAllUsers();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}
