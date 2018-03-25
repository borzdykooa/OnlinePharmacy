package by.itacademy.servlet;

import by.itacademy.dto.MedicineDto;
import by.itacademy.dto.PrescriptionDto;
import by.itacademy.service.*;
import by.itacademy.util.JspPath;
import by.itacademy.validation.MedicineValidator;

import javax.jnlp.PersistenceService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/savePrescription")
public class SavePrescriptionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-prescription"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String medicine = req.getParameter("medicine");
        String user = req.getParameter("user");
        PrescriptionDto prescriptionDto=new PrescriptionDto(name,medicine,user);

//        List<String> validateResult = MedicineValidator.getInstance().validate(medicineDto);
//        if (validateResult.isEmpty()) {
            PrescriptionService.getInstance().save(prescriptionDto);
            resp.sendRedirect("/success");
//        } else {
//            req.setAttribute("errors", validateResult);
//            req.setAttribute("name", name);
//            req.setAttribute("description", description);
//            req.setAttribute("price", price);
//            req.setAttribute("quantity", quantity);
//            req.setAttribute("groups", GroupService.getInstance().findAllGroups());
//
//            getServletContext()
//                    .getRequestDispatcher(JspPath.get("save-medicine"))
//                    .forward(req, resp);
//        }
    }
}
