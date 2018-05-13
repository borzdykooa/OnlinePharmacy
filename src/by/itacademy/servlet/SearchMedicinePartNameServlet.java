package by.itacademy.servlet;

import by.itacademy.entity.Medicine;
import by.itacademy.service.MedicineService;
import by.itacademy.util.JspPath;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/medicinesPartName", name = "MedicinesPartName")
public class SearchMedicinePartNameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String partName = req.getParameter("partName");
        if (partName != null) {
            List<Medicine> medicines = MedicineService.getInstance().findMedicinesByPartName(partName);
            req.setAttribute("medicines", medicines);
            getServletContext()
                    .getRequestDispatcher(JspPath.get("medicines-list"))
                    .forward(req, resp);
        }
    }
}
