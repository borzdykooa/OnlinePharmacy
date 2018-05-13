package by.itacademy.filter;

import by.itacademy.dto.UserLoginDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"DeleteMedicine", "DeleteOrderByDate", "SaveGroup", "SaveMedicine",
        "UpdateOrderStatusDate", "Orders","Users","OrdersByMedicineId", "OrdersByUserId"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            UserLoginDto currentUser = (UserLoginDto) ((HttpServletRequest) servletRequest).getSession().getAttribute("currentUser");
            if (currentUser.getLogin().equals("borzdykooa@mail.ru")) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/firstPage");
            }
        }
    }
}
