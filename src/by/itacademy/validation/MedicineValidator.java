package by.itacademy.validation;

import by.itacademy.formatter.QuantityFormat;
import by.itacademy.dto.MedicineDto;
import by.itacademy.formatter.PriceFormat;
import by.itacademy.util.StringUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MedicineValidator implements Validator<MedicineDto> {

    private static final MedicineValidator INSTANCE = new MedicineValidator();
    private static final int MAX_LENGTH_VARCHAR = 256;
    public static final int MAX_LENGTH_NUMERIC = 15;

    @Override
    public List<String> validate(MedicineDto object) {
        List<String> errors = new ArrayList<>();
        if (StringUtil.isEmpty(object.getName()) || object.getName().length() > MAX_LENGTH_VARCHAR) {
            errors.add("Поле 'Название лекарства' не заполнено");
        }
        if (StringUtil.isEmpty(object.getDescription())) {
            errors.add("Поле 'Описание' не заполнено");
        }

        Double price = PriceFormat.format(object.getPrice());
        if (price == null|| object.getPrice().length() > MAX_LENGTH_NUMERIC) {
            errors.add("Поле 'Цена' не заполнено либо заполнено в неверном формате");
        }

        Long quantity= QuantityFormat.format(object.getQuantity());
        if (quantity == null) {
            errors.add("Поле 'Количество' не заполнено либо заполнено в неверном формате");
        }

        return errors;
    }

    public static MedicineValidator getInstance() {
        return INSTANCE;
    }
}
