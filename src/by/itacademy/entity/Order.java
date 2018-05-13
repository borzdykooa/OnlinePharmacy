package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    private Long id;
    private LocalDate dateOfOrder;
    private LocalDate orderClothingDate;
    private Status status;
    private Double totalSum;
    private User user;
//    private List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();
}
