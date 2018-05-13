package by.itacademy.servlet;

import by.itacademy.dto.PersonalDataDto;
import by.itacademy.dto.UserDto;
import by.itacademy.service.UserService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.PersonalDataValidator;
import by.itacademy.validation.UserValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/saveUser", name = "SaveUser")
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
        UserDto userDto = new UserDto(login, password);

        String fullName = req.getParameter("fullName");
        String dateOfBirth = req.getParameter("dateOfBirth");
        String telephoneNumber = req.getParameter("telephoneNumber");
        String address = req.getParameter("address");
        PersonalDataDto personalDataDto = new PersonalDataDto(fullName, dateOfBirth, telephoneNumber, address);

        List<String> PersonalDataValidatorResult = PersonalDataValidator.getInstance().validate(personalDataDto);
        List<String> UserValidatorResult = UserValidator.getInstance().validate(userDto);
        if (PersonalDataValidatorResult.isEmpty() && UserValidatorResult.isEmpty()) {
            UserService.getInstance().save(userDto, personalDataDto);
            resp.sendRedirect("/login");
        } else {
            req.setAttribute("errors", PersonalDataValidatorResult);
            req.setAttribute("UserErrors", UserValidatorResult);

            getServletContext()
                    .getRequestDispatcher(JspPath.get("save-user"))
                    .forward(req, resp);
        }
    }
}
