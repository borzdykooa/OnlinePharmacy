package by.itacademy.servlet;

import by.itacademy.entity.Group;
import by.itacademy.entity.Medicine;
import by.itacademy.entity.PersonalData;
import by.itacademy.entity.User;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.service.PersonalDataService;
import by.itacademy.service.UserService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/users")
public class ViewAllUsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<PersonalData> users = PersonalDataService.getInstance().findAllClients();
        req.setAttribute("users", users);
        getServletContext()
                .getRequestDispatcher(JspPath.get("users-list"))
                .forward(req, resp);
    }
}
//        req.setAttribute("users", PersonalDataService.getInstance().findAllClients());
