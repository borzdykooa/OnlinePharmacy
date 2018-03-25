package by.itacademy.dto;

import by.itacademy.entity.Status;
import by.itacademy.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto {

    private String dateOfOrder;
    private String orderClothingDate;
    private String status;
    private String userId;
}
