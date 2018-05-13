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
import java.util.List;

@WebServlet(value = "/medicinesInGroup", name = "medicinesInGroup")
public class ViewMedicineInGroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if (id != null) {
            List<Medicine> medicines = MedicineService.getInstance().findAllMedicinesByGroupId(Long.valueOf(id));
            req.setAttribute("medicines", medicines);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("medicines-list"))
                    .forward(req, resp);
        }
    }
}
