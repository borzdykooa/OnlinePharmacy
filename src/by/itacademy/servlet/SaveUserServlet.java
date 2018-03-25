package by.itacademy.servlet;

import by.itacademy.dto.MedicineDto;
import by.itacademy.dto.UserDto;
import by.itacademy.entity.Role;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.service.UserService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.MedicineValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/saveUser")
public class SaveUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
//        String role = (req.getParameter("role"));


        UserDto userDto=new UserDto(login,password);

//        List<String> validateResult = MedicineValidator.getInstance().validate(medicineDto);
//        if (validateResult.isEmpty()) {
            UserService.getInstance().save(userDto);
            resp.sendRedirect("/savePersonalData");
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
