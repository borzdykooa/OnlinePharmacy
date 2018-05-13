package by.itacademy.service;

import by.itacademy.dao.PersonalDataDao;
import by.itacademy.entity.PersonalData;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonalDataService {

    private static final PersonalDataService INSTANCE = new PersonalDataService();

    public List<PersonalData> findAllClients() {
        return PersonalDataDao.getInstance().getAllClients();
    }

    public static PersonalDataService getInstance() {
        return INSTANCE;
    }
}
