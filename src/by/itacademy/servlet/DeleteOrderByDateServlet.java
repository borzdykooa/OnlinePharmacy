package by.itacademy.servlet;

import by.itacademy.service.OrderMedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(value = "/deleteOrderByDate", name = "DeleteOrderByDate")
public class DeleteOrderByDateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("delete-order-by-date"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateOfOrder = req.getParameter("dateOfOrder");
        OrderMedicineService.getInstance().delete(LocalDate.parse(dateOfOrder));
        resp.sendRedirect("/success");
    }
}
