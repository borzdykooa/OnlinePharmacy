package by.itacademy.servlet;

import by.itacademy.entity.Group;
import by.itacademy.service.GroupService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/groups")
public class ViewAllGroupsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Group> groups = GroupService.getInstance().findAllGroups();
        req.setAttribute("groups", groups);
        getServletContext()
                .getRequestDispatcher(JspPath.get("groups"))
                .forward(req, resp);
    }
}