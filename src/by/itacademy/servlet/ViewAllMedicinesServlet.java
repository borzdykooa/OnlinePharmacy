package by.itacademy.servlet;

import by.itacademy.entity.Group;
import by.itacademy.entity.Medicine;
import by.itacademy.service.GroupService;
import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/medicines")
public class ViewAllMedicinesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Medicine> medicines = MedicineService.getInstance().findAllMedicines();
        req.setAttribute("medicines", medicines);
        getServletContext()
                .getRequestDispatcher(JspPath.get("medicines-list"))
                .forward(req, resp);
    }
}
