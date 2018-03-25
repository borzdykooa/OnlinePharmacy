package by.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDto {

    private String name;
    private String description;
    private String price;
    private String quantity;
    private String groupId;
}
