package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Prescription {

    private Long id;
    private Long name;
    private Medicine medicine;
    private User user;
}
