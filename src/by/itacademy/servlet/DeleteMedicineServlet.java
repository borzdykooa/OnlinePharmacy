package by.itacademy.servlet;

import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/deleteMedicine", name = "DeleteMedicine")
public class DeleteMedicineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        getServletContext()
                .getRequestDispatcher(JspPath.get("delete-medicine"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        MedicineService.getInstance().delete(Long.valueOf(id));
        resp.sendRedirect("/success");
    }
}
