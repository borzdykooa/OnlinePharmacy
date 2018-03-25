package by.itacademy.dto;

import by.itacademy.entity.Role;
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
public class PersonalDataDto {

    private String fullName;
    private String dateOfBirth;
    private String telephoneNumber;
    private String address;
    private String userId;
}
