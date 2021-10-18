package service.servlet.useraccountserviceservlet;

import domain.UserAccount;
import service.impl.UserAccountServiceImpl;
import service.util.ApplicationContext;
import service.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class IncreaseInventory extends HttpServlet {
    UserAccountServiceImpl userAccountService=ApplicationContext.getApplicationContext().getUserAccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter writer = resp.getWriter();

        HttpSession session = req.getSession(false);
        UserAccount userAccount = (UserAccount) session.getAttribute("user");

       UserAccount user= userAccountService.findById(userAccount.getId()).get();
        writer.println(
                "  <style>" +
                        "body{direction: rtl;}" +
                        " #inventory{ border: 1px grey solid;    border-radius: 2px; width: 70%;  height: 140px;}" +
                        ".currentInventory{  padding-right: 60%; padding-left: 10px; box-sizing: border-box;}" +
                        " #myLink{ text-align: center;  float: right;  color: white; background-color: rgb(30, 111, 187);" +
                        "width:200px; height: 30px; border: 1px solid black;border-radius: 4px;  text-decoration: none;" +
                        "margin-right: 10px;}" +
                        "  </style>" +
                        " <body>" +
                        "<h3>اعتبار خود را در علی بابا شارژ کنید</h3>" +
                        " <p>با شارژ موجودی حساب خود میتوانید با سرغت وسهولت بیشتری خرید کنسد</p><br>" +
                        " <div id=inventory>" +
                        "<p class=currentInventory>مبلغ شارژ فعلی:&nbsp;&nbsp;&nbsp;&nbsp;" +
                        user.getInventory() + "هزار تومان " + " </p><br>" + "" +
                        " <div style=float: right;>" +
                        " <form action='/jsp/payment.jsp' method='get'>" +
                        " <label for=input>مبلغ افزایش شارژ را وارد کنید</label>" +
                        "  <input style='height: 25px;' type='text' name='cost' id='input' >" +
                        "<input type='hidden' name='TransactionType' value='increase'>" +
                        "<input type='hidden' name='ticketCompanyAccount' value='-1' >" +
                        "<input type='hidden' name='passengerNumber' value='-1'>" +
                        "  </div><div>" +
                        "  <input type='submit' id='myLink' value='پرداخت' >" +
                        "  </div> </form> </div> </body>");
    }
}
