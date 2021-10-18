package service.servlet.useraccountserviceservlet;

import domain.UserAccount;
import service.impl.UserAccountServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class ShowWallet extends HttpServlet {

    UserAccountServiceImpl userAccountService=ApplicationContext.getApplicationContext().getUserAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        writer.println("<style>" +
                "body{direction:rtl}" +

                "</style");

        HttpSession session = req.getSession(false);
        UserAccount user = (UserAccount) session.getAttribute("user");

        UserAccount userAccount = userAccountService.findById(user.getId()).get();

        writer.println("<p>موجودی شما &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " + userAccount.getInventory() + "</p>");
        writer.println("<a href='/charge'>افزایش اعتبار</a>");
    }
}
