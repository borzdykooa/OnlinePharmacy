package by.itacademy.service;

import by.itacademy.dao.OrderMedicineDao;
import by.itacademy.entity.OrderMedicine;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMedicineService {

    private static final OrderMedicineService INSTANCE = new OrderMedicineService();

    public List<OrderMedicine> findOrdersByMedicineId(Long medicineId) {
        return OrderMedicineDao.getInstance().getOrderByMedicineId(medicineId);
    }

    public List<OrderMedicine> findOrdersByUserId(Long userId) {
        return OrderMedicineDao.getInstance().getOrderByUserId(userId);
    }

    public void delete(LocalDate date) {
        OrderMedicineDao.getInstance().delete(date);
    }

    public List<OrderMedicine> findAllOrders() {
        return OrderMedicineDao.getInstance().getAllOrders();
    }

    public List<OrderMedicine> findAllProcessedOrders() {
        return OrderMedicineDao.getInstance().getAllProcessedOrders();
    }

    public static OrderMedicineService getInstance() {
        return INSTANCE;
    }
}
