package by.itacademy.servlet;

import by.itacademy.dto.BasketDto;
import by.itacademy.dto.MedicineQuantityDto;
import by.itacademy.dto.OrderDto;
import by.itacademy.dto.OrderMedicineDto;
import by.itacademy.service.*;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/saveOrder", name = "SaveOrder")
public class SaveOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-order"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        String totalSum = req.getParameter("totalSum");
        OrderDto orderDto = new OrderDto(user, totalSum);

        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        List<OrderMedicineDto> orderMedicineDtos = (List<OrderMedicineDto>) req.getAttribute("orderMedicineDtos");

        for (BasketDto aBasket : basket) {
            if (orderMedicineDtos == null) {
                orderMedicineDtos = new ArrayList<>();
            }
            String medicineId = aBasket.getMedicineId();
            String quantity = aBasket.getOrderQuantity();
            orderMedicineDtos.add(new OrderMedicineDto(medicineId, quantity));

            String newQuantity=aBasket.getNewQuantity();
            MedicineQuantityDto medicineQuantityDto = new MedicineQuantityDto(newQuantity, medicineId);
            MedicineService.getInstance().updateMedicineQuantity(medicineQuantityDto);
        }

        OrderService.getInstance().save(orderDto, orderMedicineDtos);
        req.getSession().removeAttribute("addToBasket");
        resp.sendRedirect("/success");
    }
}
