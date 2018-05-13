package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderMedicine implements Serializable{

    private Long id;
    private Order order;
    private Medicine medicine;
    private Integer quantity;
}
