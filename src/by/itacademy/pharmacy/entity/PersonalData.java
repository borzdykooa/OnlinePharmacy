package by.itacademy.pharmacy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PersonalData {

    private Long id;
    private Date dateOfBirth;
    private String telephoneNumber;
    private String address;
    private User user;
}
