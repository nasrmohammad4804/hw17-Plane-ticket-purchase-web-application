package service.servlet.useraccountserviceservlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class NotFoundPage extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset='UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<style>" +
                "body{direction:rtl}" +
                "</style>");
        writer.println("<h1 style='color:red'>خطای 504</h1>");
        writer.println("</p style='color:red'>متاسفانه چنین پروازی یافت نشد</p>");
    }
}
