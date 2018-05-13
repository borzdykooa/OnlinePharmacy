package by.itacademy.validation;

import by.itacademy.dto.GroupDto;
import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupValidator implements Validator<GroupDto> {

    private static final GroupValidator INSTANCE = new GroupValidator();
    private static final int MAX_LENGTH_VARCHAR = 256;

    @Override
    public List<String> validate(GroupDto object) {
        List<String> errors = new ArrayList<>();
        if (StringUtil.isEmpty(object.getName()) || object.getName().length() > MAX_LENGTH_VARCHAR) {
            errors.add("Поле 'Название группы лекарств' не заполнено");
        }

        return errors;
    }

    public static GroupValidator getInstance() {
        return INSTANCE;
    }
}
