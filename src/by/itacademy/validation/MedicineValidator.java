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
            errors.add("Название лекарства пустое или слишком длинное");
        }
        if (StringUtil.isEmpty(object.getDescription())) {
            errors.add("Описание пустое");
        }

        Double price = PriceFormat.format(object.getPrice());
        if (price == null|| object.getPrice().length() > MAX_LENGTH_NUMERIC) {
            errors.add("Цена слишком длинная, пустая или неверного формата");
        }

        Long quantity= QuantityFormat.format(object.getQuantity());
        if (quantity == null) {
            errors.add("Количество пустое или неверного формата");
        }

//        Long id= QuantityFormat.format(object.getGroupId());
//        if (id == null) {
//            errors.add("У id группы лекарств неверный формат, такой группы лекарств нет или поле не заполнено");
//        }

        return errors;
    }

    //Валидатор на дату исполнения заказа
//    @Override
//    public List<String> validate(MedicineDto object) {
//        List<String> errors = new ArrayList<>();
//        if (StringUtil.isEmpty(object.getDescription()) || object.getDescription().length() > DESCRIPTION_MAX_LENGTH) {
//            errors.add("Description is empty or too long");
//        }
//        LocalDate localDate = LocalDateFormat.format(object.getDate());
//        if (localDate == null) {
//            errors.add("Date field has incorrect format");
//        } else if (localDate.isBefore(LocalDate.now())) {
//            errors.add("Date is in past");
//        }
//
//        return errors;
//    }
//
    public static MedicineValidator getInstance() {
        return INSTANCE;
    }
}
