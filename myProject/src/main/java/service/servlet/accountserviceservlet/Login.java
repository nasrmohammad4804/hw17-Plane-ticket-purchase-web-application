package service.servlet.accountserviceservlet;

import domain.Account;
import domain.UserAccount;
import service.impl.AccountServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class Login extends HttpServlet {
    private final AccountServiceImpl accountService = ApplicationContext.getApplicationContext().getAccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        Optional<Account> optional = accountService.findByUserName(userName);


        if (optional.isEmpty()) {
            writer.println("<p style='color:red'>نام کاربری موجود نمی باشد</p>");
            req.getRequestDispatcher("/html/loginPage.html").include(req, resp);
        } else if (!optional.get().getPassword().equals(password)) {
            writer.println("<ps style='color:red'>رمز عبور نادرست است</p>");
            req.getRequestDispatcher("/html/loginPage.html").include(req, resp);
        } else {
            optional.ifPresent(x -> {
                HttpSession session = req.getSession();

                if (optional.get() instanceof UserAccount) {

                    session.setAttribute("user", optional.get());
                    try {
                        req.getRequestDispatcher("/jsp/userPanel.jsp").forward(req,resp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                    else {
                        session.setAttribute("company", optional.get());
                        try {
                            req.getRequestDispatcher("/jsp/addTicket.jsp").forward(req, resp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                });
        }
    }
}




