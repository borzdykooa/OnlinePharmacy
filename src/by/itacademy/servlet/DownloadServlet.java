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
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@WebServlet(value = "/download",name="Dowmload")
public class DownloadServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        getServletContext()
                .getRequestDispatcher(JspPath.get("orders-by-current-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        String currentUserId = req.getParameter("currentUserId");
        if (currentUserId != null) {
            List<OrderMedicine> orderMedicines = OrderMedicineService.getInstance().findOrdersByUserId(Long.valueOf(currentUserId));
            resp.setContentType("text/plain");
            resp.setHeader("Content-disposition", "attachment; filename=OrderList.txt");
            for (OrderMedicine orderMedicine : orderMedicines) {
                String orderId = "   Номер заказа: " + (orderMedicine.getOrder().getId()).toString();
                String orderDate = "   Дата заказа: " + (orderMedicine.getOrder().getDateOfOrder()).toString();
                String orderSum = "   Общая стоимость заказа: " + (orderMedicine.getOrder().getTotalSum()).toString();
                String orderStatus = "   Статус заказа: " + (orderMedicine.getOrder().getStatus()).toString();
                String medicine = "   Лекарство: " + orderMedicine.getMedicine().getName();
                String quantity = "   Количество упаковок: " + (orderMedicine.getQuantity()).toString();
                String price = "   Цена: " + (orderMedicine.getMedicine().getPrice()).toString();
                String string = orderId + orderDate + orderSum + orderStatus + medicine + quantity + price;
                resp.getWriter().println(string);
            }
        }
    }
}
