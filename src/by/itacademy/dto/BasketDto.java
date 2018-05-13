package by.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasketDto {

    private String medicineId;
    private String medicineName;
    private String medicinePrice;
    private String medicineQuantity;
    private String orderQuantity;
    private String newQuantity;
    private String sum;
}
