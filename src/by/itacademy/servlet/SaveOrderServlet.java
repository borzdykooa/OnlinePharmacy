package by.itacademy.servlet;

import by.itacademy.dto.MedicineDto;
import by.itacademy.dto.OrderDto;
import by.itacademy.dto.OrderMedicineDto;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.service.OrderService;
import by.itacademy.service.PersonalDataService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.MedicineValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/saveOrder")
public class SaveOrderServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("medicines", MedicineService.getInstance().findAllMedicines());
        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-order"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String dateOfOrder = req.getParameter("dateOfOrder");
        String orderClothingDate = req.getParameter("orderClothingDate");
        String status = req.getParameter("status");
        String user = req.getParameter("user");
        OrderDto orderDto=new OrderDto(dateOfOrder,orderClothingDate,status,user);

        String order = req.getParameter("order");
        String medicine = req.getParameter("medicine");
        String quantity = req.getParameter("quantity");
        OrderMedicineDto orderMedicineDto=new OrderMedicineDto(order,medicine,quantity);

//        List<String> validateResult = MedicineValidator.getInstance().validate(medicineDto);
//        if (validateResult.isEmpty()) {
            OrderService.getInstance().save(orderDto,orderMedicineDto);
            resp.sendRedirect("/success");
//        } else {
//            req.setAttribute("errors", validateResult);
//            req.setAttribute("name", name);
//            req.setAttribute("description", description);
//            req.setAttribute("price", price);
//            req.setAttribute("quantity", quantity);
//            req.setAttribute("groups", GroupService.getInstance().findAllGroups());
//
//            getServletContext()
//                    .getRequestDispatcher(JspPath.get("save-medicine"))
//                    .forward(req, resp);
//        }
    }
}
