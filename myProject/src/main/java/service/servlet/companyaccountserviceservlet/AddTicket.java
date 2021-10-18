package service.servlet.companyaccountserviceservlet;

import com.sun.net.httpserver.Headers;
import domain.CompanyAccount;
import domain.Ticket;
import domain.TicketCompanyAccount;
import service.impl.TicketServiceImpl;
import service.util.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.function.Predicate;

public class AddTicket extends HttpServlet {

    private final TicketServiceImpl ticketService = ApplicationContext.getApplicationContext().getTicketService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();


        HttpSession session = req.getSession(false);
        CompanyAccount company = (CompanyAccount) session.getAttribute("company");

        try {
            Optional<Ticket> ticket = hasAlreadyCompanyConfirmThisFlight(req, company.getName(),writer);
            if (ticket.isPresent()) {
                writer.println("<p>بلیط با موفقیت اضافه شد</p>");

                TicketCompanyAccount ticketCompanyAccount = new TicketCompanyAccount(company);
                ticketCompanyAccount.setTicket(ticket.get());
                ticket.get().getTicketCompanyAccounts().add(ticketCompanyAccount);
                ticketService.addNewTicket(ticket.get());
            } else {

                req.getRequestDispatcher("/jsp/addTicket.jsp").include(req, resp);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }

    private Optional<Ticket> hasAlreadyCompanyConfirmThisFlight(HttpServletRequest req, String companyName,PrintWriter writer)  {

        Optional<Ticket> optional = Optional.empty();

        String origin = req.getParameter("origin");
        String destination = req.getParameter("destination");
        LocalDate flightDate = LocalDate.parse(req.getParameter("flightDate"));
        System.out.println(flightDate);
        LocalTime timeFlight = LocalTime.parse(req.getParameter("timeFlight"));
        System.out.println(timeFlight);
        LocalTime period = LocalTime.parse(req.getParameter("period"));
        System.out.println(period);
        Long amount = Long.parseLong(req.getParameter("amount"));
        System.out.println(amount);
        int capacity = Integer.parseInt(req.getParameter("quantity"));
        System.out.println(capacity);

        if(origin.equals(destination)){
            writer.println("<p style='color:red'>مبدا و مقصد یکسان است</p>");
            return optional;
        }
        optional=Optional.of(new Ticket(origin, destination, amount, capacity,
                LocalDateTime.of(flightDate, timeFlight), period));

        Optional<Ticket> ticket = ticketService.findCompanyAlreadyHaveSameTicket
                (companyName, origin, destination, flightDate);

        if (ticket.isEmpty())
            return optional;

        else {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(Date.from(Instant.from(timeFlight)));
            calendar.add(Calendar.HOUR_OF_DAY, (2 * ticket.get().getFlightPeriod().getHour()));
            calendar.add(Calendar.MINUTE, (2 * ticket.get().getFlightPeriod().getMinute()));
            calendar.add(Calendar.SECOND, (2 * ticket.get().getFlightPeriod().getSecond()));

            Predicate<Calendar> predicate=(x ->x.after(ticket.get().getTakeOfTime().toLocalTime()));
           if( predicate.test(calendar)){
               return optional;
            }
           writer.println("<p style='color:red'>این پرواز هنوز از مقصد باز نگشته است</p>");

            return  Optional.empty();
        }
    }
}
