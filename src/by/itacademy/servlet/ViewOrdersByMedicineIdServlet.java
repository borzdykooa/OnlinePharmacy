package by.itacademy.servlet;

import by.itacademy.entity.OrderMedicine;
import by.itacademy.service.MedicineService;
import by.itacademy.service.OrderMedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/ordersByMedicineId", name = "OrdersByMedicineId")
public class ViewOrdersByMedicineIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        getServletContext()
                .getRequestDispatcher(JspPath.get("order-by-medicines"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        String id = req.getParameter("id");
        if (id != null) {
            List<OrderMedicine> orderMedicines = OrderMedicineService.getInstance().findOrdersByMedicineId(Long.valueOf(id));
            req.setAttribute("orderMedicines", orderMedicines);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("order-by-medicines"))
                    .forward(req, resp);
        }
    }
}
