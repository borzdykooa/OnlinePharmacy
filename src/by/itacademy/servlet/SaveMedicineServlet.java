package by.itacademy.servlet;

import by.itacademy.dto.MedicineDto;
import by.itacademy.entity.Group;
import by.itacademy.entity.Medicine;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.MedicineValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@WebServlet(value = "/saveMedicine", name = "SaveMedicine")
public class SaveMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", GroupService.getInstance().findAllGroups());
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-medicine"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String price = req.getParameter("price");
        String quantity = req.getParameter("quantity");
        String group = req.getParameter("group");
        MedicineDto medicineDto = new MedicineDto(name, description, price, quantity, group);

        List<Medicine> allMedicines = MedicineService.getInstance().findAllMedicines();
        Set<String> medicines = new HashSet<>();
        for (Medicine allMedicine : allMedicines) {
            medicines.add(allMedicine.getName());
        }

        List<String> validateResult = MedicineValidator.getInstance().validate(medicineDto);
        List<String> errors = new ArrayList<>();

        if (medicines.contains(name)) {
            errors.add("Такое лекарство уже существует");
            req.setAttribute("errors", errors);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("save-medicine"))
                    .forward(req, resp);
        } else if (validateResult.isEmpty()) {
            MedicineService.getInstance().save(medicineDto);
            resp.sendRedirect("/success");
        } else {
            req.setAttribute("errors", validateResult);
            req.setAttribute("groups", GroupService.getInstance().findAllGroups());

            getServletContext()
                    .getRequestDispatcher(JspPath.get("save-medicine"))
                    .forward(req, resp);
        }
    }
}
