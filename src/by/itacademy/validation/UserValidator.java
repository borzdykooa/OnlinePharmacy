package by.itacademy.validation;

import by.itacademy.dto.UserDto;
import by.itacademy.entity.User;
import by.itacademy.service.UserService;
import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserValidator implements Validator<UserDto> {

    private static final UserValidator INSTANCE = new UserValidator();
    private static final int MAX_LENGTH_VARCHAR = 256;


    @Override
    public List<String> validate(UserDto object) {
        List<User> users = UserService.getInstance().findAllUsers();

        Map<String, String> allUsers = new HashMap<String, String>();
        for (User user : users) {
            allUsers.put(user.getLogin(), user.getPassword());
        }

        List<String> UserErrors = new ArrayList<>();
        if (StringUtil.isEmpty(object.getLogin()) || object.getLogin().length() > MAX_LENGTH_VARCHAR) {
            UserErrors.add("Поле 'Логин' не заполнено");
        }
        if (allUsers.containsKey(object.getLogin())) {
            UserErrors.add("Такой логин уже занят");
        }
        if (StringUtil.isEmpty(object.getPassword()) || object.getPassword().length() > MAX_LENGTH_VARCHAR) {
            UserErrors.add("Поле 'Пароль' не заполнено");
        }
        return UserErrors;
    }

    public static UserValidator getInstance() {
        return INSTANCE;
    }
}
