package by.itacademy.servlet;

import by.itacademy.dto.UserLoginDto;
import by.itacademy.util.JspPath;
import by.itacademy.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Map<String, String> USERS = new HashMap<String, String>() {
        {
            put("user", "pass");
            put("admin", "admin");
        }
    };

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (!StringUtil.isEmpty(login) && !StringUtil.isEmpty(password)) {
            if (USERS.containsKey(login) && USERS.get(login).equals(password)) {
                req.getSession().setAttribute("currentUser", new UserLoginDto(1L, login));
                resp.sendRedirect("/medicines");
            } else {
                resp.sendRedirect("/login");
            }
        } else {
            resp.sendRedirect("/login");
        }
    }
}
