package by.itacademy.servlet;

import by.itacademy.entity.OrderMedicine;
import by.itacademy.service.OrderMedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/orders", name = "Orders")
public class ViewAllOrdersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<OrderMedicine> orderMedicines = OrderMedicineService.getInstance().findAllOrders();
        req.setAttribute("orderMedicines", orderMedicines);
        getServletContext()
                .getRequestDispatcher(JspPath.get("orders"))
                .forward(req, resp);
    }
}
