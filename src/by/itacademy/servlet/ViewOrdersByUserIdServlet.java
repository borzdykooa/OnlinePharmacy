package by.itacademy.servlet;

import by.itacademy.entity.OrderMedicine;
import by.itacademy.service.OrderMedicineService;
import by.itacademy.service.PersonalDataService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/ordersByUserId", name = "OrdersByUserId")
public class ViewOrdersByUserIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        getServletContext()
                .getRequestDispatcher(JspPath.get("order-by-users"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        String id = req.getParameter("id");
        if (id != null) {
            List<OrderMedicine> orderMedicines = OrderMedicineService.getInstance().findOrdersByUserId(Long.valueOf(id));
            req.setAttribute("orderMedicines", orderMedicines);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("order-by-users"))
                    .forward(req, resp);
        }
    }
}
