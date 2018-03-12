package by.itacademy.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Order {

    private Long id;
    private Date dateOfOrder;
    private Date orderClothingDate;
    private Integer quantity;
    private Status status;
    private User user;
    private Set<Medicine> medicines = new HashSet<Medicine>();

}
