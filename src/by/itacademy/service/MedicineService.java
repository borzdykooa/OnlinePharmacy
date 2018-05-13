package by.itacademy.service;

import by.itacademy.dao.MedicineDao;
import by.itacademy.dto.MedicineQuantityDto;
import by.itacademy.entity.Medicine;
import by.itacademy.dto.MedicineDto;
import by.itacademy.entity.Group;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MedicineService {

    private static final MedicineService INSTANCE = new MedicineService();

    public void save(MedicineDto dto) {
        Medicine request = Medicine.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(Double.valueOf(dto.getPrice()))
                .quantity(Integer.valueOf(dto.getQuantity()))
                .group(Group.builder()
                        .id(Long.valueOf(dto.getGroupId()))
                        .build())
                .build();
        MedicineDao.getInstance().save(request);
    }

    public void updateMedicineQuantity(MedicineQuantityDto dto) {
        Medicine request = Medicine.builder()
                .quantity(Integer.valueOf(dto.getQuantity()))
                .id(Long.valueOf(dto.getId()))
                .build();
        MedicineDao.getInstance().updateQuantity(request);
    }

    public List<Medicine> findAllMedicines() {
        return MedicineDao.getInstance().getAllMedicines();
    }

    public List<Medicine> findMedicinesByPartName(String partName) {
        return MedicineDao.getInstance().getMedicinesByPartName(partName);
    }

    public List<Medicine> findAllMedicinesByGroupId(Long groupId) {
        return MedicineDao.getInstance().getMedicinesByGroupId(groupId);
    }

    public Medicine getMedicineByMedicineID(Long medicineId) {
        return MedicineDao.getInstance().getByMedicineID(medicineId);
    }

    public void delete(Long medicineId) {
        MedicineDao.getInstance().delete(medicineId);
    }

    public static MedicineService getInstance() {
        return INSTANCE;
    }
}
