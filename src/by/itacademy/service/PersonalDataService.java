package by.itacademy.service;


import by.itacademy.dao.GroupDao;
import by.itacademy.dao.MedicineDao;
import by.itacademy.dao.PersonalDataDao;
import by.itacademy.dto.PersonalDataDto;
import by.itacademy.entity.Medicine;
import by.itacademy.dto.MedicineDto;
import by.itacademy.entity.Group;
import by.itacademy.entity.PersonalData;
import by.itacademy.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonalDataService {

    private static final PersonalDataService INSTANCE = new PersonalDataService();

    public void save(PersonalDataDto dto) {
        PersonalData request = PersonalData.builder()
                .fullName(dto.getFullName())
                .dateOfBirth(LocalDate.parse(dto.getDateOfBirth()))
                .telephoneNumber(dto.getTelephoneNumber())
                .address(dto.getAddress())
                .user(User.builder()
                        .id(Long.valueOf(dto.getUserId()))
                        .build())
                .build();
        PersonalDataDao.getInstance().save(request);
    }

    public List<PersonalData> findAllClients() {
        return PersonalDataDao.getInstance().getAllClients();
    }

    public static PersonalDataService getInstance() {
        return INSTANCE;
    }
}
