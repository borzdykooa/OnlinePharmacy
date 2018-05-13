package by.itacademy.validation;

import by.itacademy.dto.PersonalDataDto;
import by.itacademy.formatter.QuantityFormat;
import by.itacademy.dto.MedicineDto;
import by.itacademy.formatter.PriceFormat;
import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PersonalDataValidator implements Validator<PersonalDataDto> {

    private static final PersonalDataValidator INSTANCE = new PersonalDataValidator();
    private static final int MAX_LENGTH_VARCHAR = 256;

    @Override
    public List<String> validate(PersonalDataDto object) {
        List<String> errors = new ArrayList<>();
        if (StringUtil.isEmpty(object.getFullName()) || object.getFullName().length() > MAX_LENGTH_VARCHAR) {
            errors.add("Поле 'ФИО' не заполнено");
        }
        if (StringUtil.isEmpty(object.getTelephoneNumber()) || object.getTelephoneNumber().length() > MAX_LENGTH_VARCHAR) {
            errors.add("Поле 'Номер телефона' не заполнено");
        }

        if (StringUtil.isEmpty(object.getAddress()) || object.getAddress().length() > MAX_LENGTH_VARCHAR) {
            errors.add("Поле 'Адрес'не заполнено");
        }

        if (StringUtil.isEmpty(object.getDateOfBirth())) {
            errors.add("Поле 'Дата рождения' не заполнено");
        }

        return errors;
    }

    public static PersonalDataValidator getInstance() {
        return INSTANCE;
    }
}
