package by.itacademy.servlet;

import by.itacademy.dto.BasketDto;
import by.itacademy.dto.MedicineQuantityDto;
import by.itacademy.service.MedicineService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/addToBasket", name = "AddToBasket")
public class AddToBasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        String medicineId = req.getParameter("medicineId");
        String medicineName = req.getParameter("medicineName");
        String medicinePrice = req.getParameter("medicinePrice");
        String medicineQuantity = req.getParameter("medicineQuantity");
        String orderQuantity = req.getParameter("orderQuantity");
        String newQuantity = String.valueOf(Integer.valueOf(medicineQuantity) - Integer.valueOf(orderQuantity));
        BigDecimal helpSum = (BigDecimal.valueOf(Double.valueOf(medicinePrice)
                * Double.valueOf(orderQuantity))).setScale(2, BigDecimal.ROUND_HALF_UP);
        String sum = String.valueOf(helpSum);

        List<BasketDto> basket = (List<BasketDto>) req.getSession().getAttribute("addToBasket");
        if (basket == null) {
            basket = new ArrayList<>();
            req.getSession().setAttribute("addToBasket", basket);
        }
        basket.add(new BasketDto(medicineId, medicineName, medicinePrice, medicineQuantity, orderQuantity, newQuantity, sum));
        double helpTotalSum = 0;
        for (int i = 0; i < basket.size(); i++) {
            helpTotalSum += Double.valueOf(basket.get(i).getOrderQuantity()) * Double.valueOf(basket.get(i).getMedicinePrice());
            BigDecimal totalSum = (BigDecimal.valueOf(helpTotalSum)).setScale(2, BigDecimal.ROUND_HALF_UP);

            req.getSession().setAttribute("totalSum", totalSum);
        }
        resp.sendRedirect("/firstPage");
    }
}
