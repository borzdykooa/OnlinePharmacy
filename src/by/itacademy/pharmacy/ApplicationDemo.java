package by.itacademy.pharmacy;

import by.itacademy.pharmacy.dao.MedicineDao;
import by.itacademy.pharmacy.dao.OrderDao;
import by.itacademy.pharmacy.dao.PersonalDataDao;
import by.itacademy.pharmacy.dao.PrescriptionDao;
import by.itacademy.pharmacy.dao.UserDao;
import by.itacademy.pharmacy.entity.Group;
import by.itacademy.pharmacy.entity.Medicine;
import by.itacademy.pharmacy.entity.Order;
import by.itacademy.pharmacy.entity.PersonalData;
import by.itacademy.pharmacy.entity.Prescription;
import by.itacademy.pharmacy.entity.Role;
import by.itacademy.pharmacy.entity.Status;
import by.itacademy.pharmacy.entity.User;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Set;

public class ApplicationDemo {

    public static void main(String[] args) {
        saveMedicine();
        saveUserAdmin();
        saveUserClient();
        savePersonalData();
        savePrescription();
        getAllUsers();
        getAllMedicines();
        saveOrderMedicine();
        getOrdersByMedicineName();
        getOrdersByUserLogin();
    }

    private static void getOrdersByMedicineName() {
        Medicine medicine = MedicineDao.getInstance().getOrderByName("Анаферон");
        System.out.println(medicine);
    }

    private static void getOrdersByUserLogin() {
        User user = UserDao.getInstance().getOrderByLogin("lula_m@mail.ru");
        System.out.println(user);
    }

    private static void saveOrderMedicine() {
        Order order = Order.builder()
                .dateOfOrder(Date.valueOf("2018-3-8"))
                .orderClothingDate(Date.valueOf("2018-3-12"))
                .quantity(2)
                .status(Status.builder()
                        .name("заказ оформлен")
                        .build())
                .user(User.builder()
                        .login("lula_m@mail.ru")
                        .build())
                .build();
        Medicine medicine = Medicine.builder()
                .name("Анаферон")
                .build();
        OrderDao.getInstance().save(order, medicine);
    }

    private static void getAllMedicines() {
        List<Medicine> allMedicines = MedicineDao.getInstance().getAllMedicines();
        allMedicines.forEach(System.out::println);
    }

    private static void getAllUsers() {
        List<User> allUsers = UserDao.getInstance().getAllUsers();
        allUsers.forEach(System.out::println);
    }

    private static void savePrescription() {
        PrescriptionDao instance = PrescriptionDao.getInstance();
        Prescription prescription = Prescription.builder()
                .name(123456789L)
                .medicine(Medicine.builder()
                        .name("Анаферон")
                        .build())
                .user(User.builder()
                        .login("lula_m@mail.ru")
                        .build())
                .build();
        instance.save(prescription);

        System.out.println(prescription);
    }

    private static void savePersonalData() {
        PersonalDataDao instance = PersonalDataDao.getInstance();
        PersonalData personalData = PersonalData.builder()
                .dateOfBirth(Date.valueOf("1989-7-2"))
                .telephoneNumber("8-029-1234567")
                .address("г.Минск, ул. Жудро 48/19")
                .user(User.builder()
                        .login("lula_m@mail.ru")
                        .build())
                .build();
        instance.save(personalData);

        System.out.println(personalData);
    }

    private static void saveUserClient() {
        UserDao instance = UserDao.getInstance();
        User user = User.builder()
                .surname("Мелешко")
                .name("Юлия")
                .patronymic("Викторовна")
                .login("lula_m@mail.ru")
                .password("password")
                .role(Role.builder()
                        .name("клиент")
                        .build())
                .build();
        instance.save(user);

        System.out.println(user);
    }

    private static void saveUserAdmin() {
        UserDao instance = UserDao.getInstance();
        User user = User.builder()
                .surname("Борздыко")
                .name("Ольга")
                .patronymic("Александровна")
                .login("borzdykooa@mail.ru")
                .password("password")
                .role(Role.builder()
                        .name("администратор")
                        .build())
                .build();
        instance.save(user);

        System.out.println(user);
    }

    private static void saveMedicine() {
        MedicineDao instance = MedicineDao.getInstance();
        Medicine medicine = Medicine.builder()
                .name("Анаферон")
                .description("Профилактика и лечение гриппа, ОРВИ, вирусных инфекций верхних дыхательных путей " +
                        "(риниты, фарингиты, ларингиты, трахеобронхиты). Комплексная терапия и профилактика " +
                        "хронической рецидивирующей герпесвирусной инфекции. Лечение вторичных иммунодефицитных " +
                        "состояний различной этиологии.")
                .price(BigDecimal.valueOf(6.58))
                .quantity(25)
                .group(Group.builder()
                        .name("противовирусные препараты")
                        .build())
                .build();
        instance.save(medicine);

        System.out.println(medicine);
    }
}
