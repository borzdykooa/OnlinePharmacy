package by.itacademy.servlet;

import by.itacademy.entity.Medicine;
import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/medicineFullInfo", name = "MedicineFullInfo")
public class FullInfoMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            Medicine fullMedicineInfo = MedicineService.getInstance().getMedicineByMedicineID(Long.valueOf(id));
            req.setAttribute("medicine", fullMedicineInfo);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("medicine-full-info"))
                    .forward(req, resp);
        }
    }
}
