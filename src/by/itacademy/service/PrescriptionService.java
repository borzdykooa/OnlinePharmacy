package by.itacademy.service;


import by.itacademy.dao.MedicineDao;
import by.itacademy.dao.PrescriptionDao;
import by.itacademy.dto.PrescriptionDto;
import by.itacademy.entity.Medicine;
import by.itacademy.dto.MedicineDto;
import by.itacademy.entity.Group;
import by.itacademy.entity.Prescription;
import by.itacademy.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PrescriptionService {

    private static final PrescriptionService INSTANCE = new PrescriptionService();

    public void save(PrescriptionDto dto) {
        Prescription request = Prescription.builder()
                .name(Long.valueOf(dto.getName()))
                .medicine(Medicine.builder()
                        .id(Long.valueOf(dto.getMedicineId()))
                        .build())
                .user(User.builder()
                        .id(Long.valueOf(dto.getUserId()))
                        .build())
                .build();
        PrescriptionDao.getInstance().save(request);
    }

    public static PrescriptionService getInstance() {
        return INSTANCE;
    }
}
