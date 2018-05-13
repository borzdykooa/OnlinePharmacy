package by.itacademy.servlet;

import by.itacademy.dto.UserLoginDto;
import by.itacademy.entity.User;
import by.itacademy.service.UserService;
import by.itacademy.util.JspPath;
import by.itacademy.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users = UserService.getInstance().findAllUsers();

        Map<String, String> allUsers = new HashMap<String, String>();
        for (int i = 0; i < users.size(); i++) {
            allUsers.put(users.get(i).getLogin(), users.get(i).getPassword());
        }

        Map<String, Long> idLogins = new HashMap<String, Long>();
        for (int j = 0; j < users.size(); j++) {
            idLogins.put(users.get(j).getLogin(), users.get(j).getId());
        }

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        List<String> errors = new ArrayList<>();
        if (StringUtil.isEmpty(login) || StringUtil.isEmpty(password)) {
            errors.add("Поле 'Логин' не заполнено и/или поле 'Пароль' не заполнено");
            req.setAttribute("errors", errors);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("login"))
                    .forward(req, resp);
        } else if (!StringUtil.isEmpty(login) && !StringUtil.isEmpty(password)) {
            if (!allUsers.containsKey(login)) {
                errors.add("Такой пользователь не зарегистрирован");
                req.setAttribute("errors", errors);
                getServletContext()
                        .getRequestDispatcher(JspPath.get("login"))
                        .forward(req, resp);
            } else if (allUsers.containsKey(login) && !allUsers.get(login).equals(password)) {
                errors.add("Введен неверный пароль");
                req.setAttribute("errors", errors);
                getServletContext()
                        .getRequestDispatcher(JspPath.get("login"))
                        .forward(req, resp);
            } else if (allUsers.containsKey(login) && allUsers.get(login).equals(password)) {
                req.getSession().setAttribute("currentUser", new UserLoginDto(idLogins.get(login), login));
                resp.sendRedirect("/firstPage");
            } else {

                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
