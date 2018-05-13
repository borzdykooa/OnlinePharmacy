package by.itacademy.service;

import by.itacademy.dao.OrderDao;
import by.itacademy.dto.*;
import by.itacademy.entity.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderService {

    private static final OrderService INSTANCE = new OrderService();

    public void save(OrderDto orderDto, List<OrderMedicineDto> orderMedicineDtos) {
        Order orderRequest = Order.builder()
                .totalSum(Double.valueOf(orderDto.getTotalSum()))
                .user(User.builder()
                        .id(Long.valueOf(orderDto.getUserId()))
                        .build())
                .build();
        List<OrderMedicine> orderMedicineRequests = new ArrayList<>();
        for (OrderMedicineDto orderMedicineDto : orderMedicineDtos) {
            OrderMedicine orderMedicineRequest = OrderMedicine.builder()
                    .medicine(Medicine.builder()
                            .quantity(Integer.valueOf(orderMedicineDto.getQuantity()))
                            .id(Long.valueOf(orderMedicineDto.getMedicineId()))
                            .build())
                    .quantity(Integer.valueOf(orderMedicineDto.getQuantity()))
                    .build();
            orderMedicineRequests.add(orderMedicineRequest);
        }
        OrderDao.getInstance().save(orderRequest, orderMedicineRequests);
    }

    public void updateOrderStatusDate(OrderStatusDateDto dto) {
        Order request = Order.builder()
                .orderClothingDate(LocalDate.parse(dto.getOrderClothingDate()))
                .id(Long.valueOf(dto.getId()))
                .build();
        OrderDao.getInstance().updateStatusAndDate(request);
    }

    public static OrderService getInstance() {
        return INSTANCE;
    }
}
