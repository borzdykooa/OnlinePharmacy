package by.itacademy.service;


import by.itacademy.dao.MedicineDao;
import by.itacademy.dao.OrderDao;
import by.itacademy.dto.OrderDto;
import by.itacademy.dto.OrderMedicineDto;
import by.itacademy.entity.*;
import by.itacademy.dto.MedicineDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderService {

    private static final OrderService INSTANCE = new OrderService();

    public void save(OrderDto orderDto, OrderMedicineDto orderMedicineDto) {
        Order orderRequest = Order.builder()
                .dateOfOrder(LocalDate.parse(orderDto.getDateOfOrder()))
                .orderClothingDate(LocalDate.parse(orderDto.getOrderClothingDate()))
                .status(Status.valueOf(orderDto.getStatus()))
                .user(User.builder()
                        .id(Long.valueOf(orderDto.getUserId()))
                        .build())
                .build();

        OrderMedicine orderMedicineRequest = OrderMedicine.builder()
                .order(Order.builder()
                        .id(Long.valueOf(orderMedicineDto.getOrderId()))
                        .build())
                .medicine(Medicine.builder()
                        .id(Long.valueOf(orderMedicineDto.getMedicineId()))
                        .build())
                .build();
        OrderDao.getInstance().save(orderRequest,orderMedicineRequest);
    }

    public List<Medicine> findAllMedicines() {
        return MedicineDao.getInstance().getAllMedicines();
    }

    public List<Medicine> findAllMedicinesByGroupId(Long groupId) {
        return MedicineDao.getInstance().getMedicinesByGroupId(groupId);
    }

    public Medicine getMedicineByMedicineID(Long medicineID) {
        return MedicineDao.getInstance().getByMedicineID(medicineID);
    }

    public void delete(Long medicineId) {
        MedicineDao.getInstance().delete(medicineId);
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}
