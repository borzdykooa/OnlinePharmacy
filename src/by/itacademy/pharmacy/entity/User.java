package by.itacademy.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class User {

    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String login;
    private String password;
    private Role role;
    private Set<Order> orders = new HashSet<>();
    private Set<Medicine> medicines = new HashSet<>();

}
