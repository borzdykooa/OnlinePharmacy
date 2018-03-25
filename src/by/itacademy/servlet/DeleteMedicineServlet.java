package by.itacademy.servlet;

import by.itacademy.dto.GroupDto;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.GroupValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/deleteMedicine")
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
