package by.itacademy.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalData {

    private Long id;
    private String fullName;
    private LocalDate dateOfBirth;
    private String telephoneNumber;
    private String address;
    private User user;
}
