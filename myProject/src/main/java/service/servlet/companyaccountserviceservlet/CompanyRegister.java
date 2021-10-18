package service.servlet.companyaccountserviceservlet;

import domain.CompanyAccount;
import repository.impl.AccountRepositoryImpl;
import repository.impl.CompanyAccountRepositoryImpl;
import service.impl.CompanyAccountServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class CompanyRegister extends HttpServlet {

    private final AccountRepositoryImpl accountRepository = ApplicationContext.getApplicationContext()
            .getAccountRepository();

    private final CompanyAccountServiceImpl companyAccountService = ApplicationContext
            .getApplicationContext().getCompanyAccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");


        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();

        String userName = req.getParameter("userName");

        if (accountRepository.findByUserName(userName).isPresent()) {
            writer.println("<p style='color:red'> نام کاربری از قبل موجود است لطفا نام کاربری دیگری انتخاب کنید</p>");
            req.getRequestDispatcher("/html/companyRegister.html").include(req, resp);
        } else {
            CompanyAccount companyAccount = CompanyAccount.builder().companyNumber(req.getParameter("companyNumber"))
                    .localDateTime(LocalDateTime.now()).password(req.getParameter("password")).
                            name(req.getParameter("companyName")).userName(req.getParameter("userName")).build();

            HttpSession session = req.getSession();
            session.setAttribute("company", companyAccount);
            req.getRequestDispatcher("/jsp/addTicket.jsp");
            companyAccountService.register(companyAccount);

        }
    }
}
