package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class OrderMedicine {

    private Long id;
    private Order order;
    private Medicine medicine;
    private Integer quantity;
}
