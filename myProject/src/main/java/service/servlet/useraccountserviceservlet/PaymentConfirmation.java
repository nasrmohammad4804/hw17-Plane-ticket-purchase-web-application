package service.servlet.useraccountserviceservlet;

import domain.Ticket;
import domain.UserAccount;
import service.impl.TicketServiceImpl;
import service.impl.UserAccountServiceImpl;
import service.util.ApplicationContext;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class PaymentConfirmation extends HttpServlet {

    private TicketServiceImpl ticketService = ApplicationContext.getApplicationContext().getTicketService();
    private UserAccountServiceImpl userAccountService = ApplicationContext
            .getApplicationContext().getUserAccountService();

    private final EntityManager entityManager=ApplicationContext.getApplicationContext().getEntityManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();
        writer.println("<style>" +
                "body{direction:rtl" +
                "}" +
                "</style>");
        writer.println("<body>");

        String securityCode=req.getParameter("securityCode");
        String actualSecurityCode= req.getParameter("actualSecurityCode");

        if(!securityCode.equals(actualSecurityCode)){
            writer.println("<p style='color:red'>کد امنیتی وارد شده اشتباه میباشد</p>");
            return;
        }

        String transactionType = req.getParameter("TransactionType");
        long cost = Long.parseLong(req.getParameter("cost"));
        long ticketCompanyId = Long.parseLong(req.getParameter("ticketCompanyAccount"));
        int number = Integer.parseInt(req.getParameter("passengerNumber"));


        HttpSession session = req.getSession(false);
        UserAccount userAccount = (UserAccount) session.getAttribute("user");

        UserAccount user = userAccountService.findById(userAccount.getId()).get();


        if (transactionType.equals("increase")) {
            user.setInventory(user.getInventory() + cost);

            userAccountService.update(user);
         writer.println("کیف پولتان "+cost+"شارژ شد " );

        } else {

            if (user.getInventory() < cost) {
                writer.println("<p>موجودی کافی نیست</p>");

            } else {
                entityManager.getTransaction().begin();
                user.setInventory(user.getInventory() - cost);

                Ticket ticket = ticketService.findById(ticketCompanyId).get();
                ticket.setCapacity(ticket.getCapacity() - number);
                entityManager.getTransaction().commit();
                writer.println("<p style='color:green'>عملیات با موفقیت انجام شد</p>");
            }
        }

    }
}

