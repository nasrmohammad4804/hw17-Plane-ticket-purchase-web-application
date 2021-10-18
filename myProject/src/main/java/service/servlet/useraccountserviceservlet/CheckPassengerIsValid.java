package service.servlet.useraccountserviceservlet;

import domain.UserAccount;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class CheckPassengerIsValid extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            resp.setContentType("text/html;charset=UTF-8");
            resp.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            PrintWriter writer=resp.getWriter();

            long passengerNumber = Long.parseLong(req.getParameter("passengerNumber"));

            ArrayList<UserAccount> userAccounts = new ArrayList<>();

            for (int i = 1; i <= passengerNumber; i++) {
                String firstName = req.getParameter("name" + i);
                String lastName = req.getParameter("family" + i);
                String nationalCode = req.getParameter("nationalCode" + i);
                String gender = req.getParameter("gender" + i);
                userAccounts.add(UserAccount.builder().firstName(firstName).lastName(lastName).nationalCode(nationalCode)
                        .gender(gender).build());
            }
            long distinctNumber= userAccounts.stream().map(UserAccount::getNationalCode).distinct().count();

            if(distinctNumber==userAccounts.size()){
                req.setAttribute("allPassenger",userAccounts);
                req.setAttribute("ticketCompanyAccount",req.getParameter("ticketCompanyAccount"));

                req.getRequestDispatcher("/jsp/verificationStep.jsp").forward(req,resp);
            }
            else{
                writer.println("<body> <style>" +
                        "body{ direction:rtl}" +
                        "</style>");
                writer.println("<p style='color:red'>کد ملی تکراری وجود دارد </p>");
                writer.println("</body>");
            }
    }


}

