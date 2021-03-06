package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Medicine {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private Group group;
}
