package by.itacademy.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalDataDto {

    private String fullName;
    private String dateOfBirth;
    private String telephoneNumber;
    private String address;
}
