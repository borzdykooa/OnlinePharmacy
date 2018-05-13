package by.itacademy.servlet;

import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/basket", name = "Basket")
public class BasketServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        getServletContext()
                .getRequestDispatcher(JspPath.get("basket"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());

        req.getSession().removeAttribute("addToBasket");
        req.getSession().removeAttribute("totalSum");

        getServletContext()
                .getRequestDispatcher(JspPath.get("basket"))
                .forward(req, resp);
    }
}
