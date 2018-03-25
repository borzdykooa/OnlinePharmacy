package by.itacademy.servlet;

import by.itacademy.dto.MedicineDto;
import by.itacademy.dto.PersonalDataDto;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.service.PersonalDataService;
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

@WebServlet("/savePersonalData")
public class SavePersonalDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setAttribute("users", UserService.getInstance().findAllUsers());
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-personal-data"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("surname");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String telephoneNumber = req.getParameter("telephoneNumber");
        String address = req.getParameter("address");
        String user = req.getParameter("user");
        PersonalDataDto personalDataDto = new PersonalDataDto(fullName,dateOfBirth,telephoneNumber,address,user);

//        List<String> validateResult = MedicineValidator.getInstance().validate(personalDataDto);
//        if (validateResult.isEmpty()) {
            PersonalDataService.getInstance().save(personalDataDto);
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
