package by.itacademy.servlet;

import by.itacademy.dto.GroupDto;
import by.itacademy.service.GroupService;
import by.itacademy.util.JspPath;
import by.itacademy.validation.GroupValidator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/saveGroup")
public class SaveGroupServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(JspPath.get("save-group"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        GroupDto groupDto = new GroupDto(name);

        List<String> validateResult = GroupValidator.getInstance().validate(groupDto);
        if (validateResult.isEmpty()) {
            GroupService.getInstance().save(groupDto);
            resp.sendRedirect("/success");
        } else {
            req.setAttribute("errors", validateResult);
            req.setAttribute("name", name);

            getServletContext()
                    .getRequestDispatcher(JspPath.get("save-group"))
                    .forward(req, resp);
        }
    }
}
