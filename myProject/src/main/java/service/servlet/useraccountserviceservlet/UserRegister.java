package service.servlet.useraccountserviceservlet;

import com.mysql.cj.conf.ConnectionUrlParser;
import domain.UserAccount;
import repository.impl.AccountRepositoryImpl;
import service.impl.UserAccountServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Map;


public class UserRegister extends HttpServlet  {


    private  final AccountRepositoryImpl accountRepository= ApplicationContext
            .getApplicationContext().getAccountRepository();

    private final UserAccountServiceImpl userAccountService=ApplicationContext
            .getApplicationContext().getUserAccountService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");


        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        String userName=req.getParameter("userName");

       if( accountRepository.findByUserName(userName).isPresent()){


           writer.println("<p style='color:red'> نام کاربری از قبل موجود است لطفا نام کاربری دیگری انتخاب کنید</p>");
          req.getRequestDispatcher("/html/UserRegister.html").include(req,resp);
       }
       else{
           HttpSession session = req.getSession();
           UserAccount account=new UserAccount(userName,req.getParameter("password"), LocalDateTime.now(),
                   req.getParameter("firstName"));

           account.setFamily(req.getParameter("lastName"));
           account.setNationalCode(req.getParameter("nationalCode"));

           session.setAttribute("user",account);

           userAccountService.register(account);
           req.getRequestDispatcher("/jsp/userPanel.jsp").forward(req,resp);


       }


    }
}
