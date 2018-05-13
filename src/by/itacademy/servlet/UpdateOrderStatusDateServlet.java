package by.itacademy.servlet;

import by.itacademy.dto.OrderStatusDateDto;
import by.itacademy.service.OrderMedicineService;
import by.itacademy.service.OrderService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/updateOrderStatusDate", name = "UpdateOrderStatusDate")
public class UpdateOrderStatusDateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("orders", OrderMedicineService.getInstance().findAllProcessedOrders());
        getServletContext()
                .getRequestDispatcher(JspPath.get("update-order-status-date"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String orderClothingDate = req.getParameter("orderClothingDate");
        String id = req.getParameter("id");
        OrderStatusDateDto orderStatusDateDto = new OrderStatusDateDto(orderClothingDate, id);
        OrderService.getInstance().updateOrderStatusDate(orderStatusDateDto);
        resp.sendRedirect("/success");
    }
}
