<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="domain.UserAccount" %>
<%@ page import="service.impl.TicketCompanyAccountServiceImpl" %>
<%@ page import="service.util.ApplicationContext" %>
<%@ page import="domain.TicketCompanyAccount" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.function.Predicate" %>
<%@ page import="java.util.function.Function" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="java.time.*" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/10/2021
  Time: 8:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    .master{
        width: 80%;
        margin-bottom: 25px;
    }
    body{
        direction: rtl;
    }
    .subsidiary-row{
        width: 75%;
        height: 50px;
        background-color:#f4f1de;
        text-align: center;
        padding: 0;
    }
    .master-row{

        background-color: #ccc5b9;
        width: 25%;
        height:50px;
        text-align: center;
        padding: 0;
        /* border: 1px solid gainsboro; */
    }
    .master-information{
        padding-left: 5rem;
        padding-right: 5rem;
        text-align: center;
        color: #4b5259;
        background-color: #bcb8b1;
        width: 20%;
    }
    .information{
        padding-left: 5rem;
        padding-right: 5rem;
        text-align: center;

        background-color: #bcb8b1;
        width: 20%;
    }
    .personal{
        width: 84%;
        border: 1px solid grey;
        margin-bottom: 35px;

    }
    #discountCode{
        width: 84%;
        border: 1px solid #bcb8b1;
        box-sizing: border-box;
        padding-right: 10px;
        margin-bottom: 25px;
    }
    #input{
        width: 220px;
        height: 40px;
        border-radius: 3px;
        border: 1px solid black;
        font-size: 18px;
        padding-right: 10px;
    }
    #myButton{

        width: 130px;
        height: 43px;
        background-color: cornflowerblue;
        font-size: 18px;
        text-decoration: none;
        border-radius: 3px;
        color: black;
        font-weight: bold;
    }
    div>a{

        float: left;
        box-sizing: border-box;

        width: 220px;
        height: 45px;
        background-color: rgb(0, 86, 158) ;
        font-size: 20px;
        color: white;
        text-decoration: none;
        border: 1px solid black;
        border-radius: 3px;
        text-align: center;

        margin-top: 20px;

    }
    .last-tag{

        border: 1px black solid;
        box-sizing: border-box;
        width: 67%;
        height: 120px;
    }
    #payment{
        float: right;
        padding-right: 45%;
        padding-top: 15px;
    }
    #wallet{
        float: left;
        text-decoration: none;
        width: 70px;
        height: 30px;
        background-color: sandybrown;
        border: 1px solid black;
        border-radius: 3px;
        text-align: center;
    }
</style>
<body>

<a href="/wallet" id="wallet">کیف پول</a>
<%ArrayList<UserAccount> userAccounts=new ArrayList<>();
    TicketCompanyAccountServiceImpl ticketCompanyAccountService= ApplicationContext.getApplicationContext()
            .getTicketCompanyAccountService();

    long id=Long.parseLong( String.valueOf(request.getAttribute("ticketCompanyAccount")));
    Optional<TicketCompanyAccount> ticketCompanyAccount = ticketCompanyAccountService.findById(id);
%>
<h3 style="font-weight: bold; ">اطلاعات بلیط</h3>
<div class="master">
    <table>
        <tr>
            <th class="master-row">مبدا</th>
            <td class="subsidiary-row"><%=ticketCompanyAccount.get().getTicket().getOriginCity()%></td>
        </tr>
        <tr>
            <th class="master-row">مفصد</th>
            <td class="subsidiary-row"><%=ticketCompanyAccount.get().getTicket().getDestinationCity()%></td>
        </tr>
        <tr>
            <th class="master-row">شرکت هواپیمایی</th>
            <td class="subsidiary-row"><%=ticketCompanyAccount.get().getCompanyAccount().getName()%></td>
        </tr>
        <tr>
            <th class="master-row">تاریخ وساعت پرواز</th>
            <td class="subsidiary-row"><%=ticketCompanyAccount.get().getTicket().getTakeOfTime()%> </td>
        </tr>
        <tr>
            <th class="master-row">مدت پرواز</th>
            <td class="subsidiary-row"><%=ticketCompanyAccount.get().getTicket().getFlightPeriod()%></td>
        </tr>
    </table>
</div>

<h3 style="font-weight: bold;">مشخصات مسافران</h3>

<div class="personal">
    <table>

        <tr>

            <th class="master-information">نام و نام خانوادگی</th>
            <th  class="master-information">جنسیت</th>
            <th  class="master-information">کد ملی</th>
            <th  class="master-information">قیمت</th>
        </tr>
    <%
        try {
            userAccounts.addAll( (ArrayList<UserAccount>) request.getAttribute("allPassenger"));
        }catch (Exception e){
            out.print(e.getMessage());
        }

        for(int i=0; i<userAccounts.size(); i++){

            %>
        <tr>
            <td class="information"><%=userAccounts.get(i).getName()+" "+userAccounts.get(i).getFamily()%></td>
            <td class="information"><%=userAccounts.get(i).getGender()%>  </td>
            <td class="information"><%=userAccounts.get(i).getNationalCode()%></td>
            <td class="information"><%=ticketCompanyAccount.get().getTicket().getAmount()%></td>
        </tr>
        <%
            }
    %>

    </table>
</div>

<div id="discountCode">
    <h2>کد تخفیف</h2>
        <p style="font-size: 18px; color: #666563">اگر کد تخفیف دارید ان را در بخش زیر وارد کنید و دکمه اعمال را بزنید</p>
        <input id="input" type="text"  placeholder="کد تخفیف"; >

        <button id="myButton" href="">اعمال</button>
</div>

<%
    long totalPrice;
    boolean result=false;
    LocalTime currentTime=LocalTime.now();
   LocalTime differenceTime= ticketCompanyAccount.get().getTicket().getTakeOfTime().toLocalTime().minusHours
           (currentTime.getHour()).minusMinutes(currentTime.getMinute()).minusSeconds(currentTime.getSecond());

   LocalDate currentDate=LocalDate.now();

   if( currentDate.isEqual(ticketCompanyAccount.get().getTicket().getTakeOfTime().toLocalDate()) &&
           differenceTime.isBefore(LocalTime.of(1,0,0))){
       totalPrice=(ticketCompanyAccount.get().getTicket().getAmount()/2)*userAccounts.size();
        result=true;
   }

   else
       totalPrice=ticketCompanyAccount.get().getTicket().getAmount()*userAccounts.size();

%>
<div class="last-tag">
    <div>
        <p  id="payment">مبلغ قابل پرداخت</p>
        <span   style="padding-right: 30px; color: dodgerblue; font-size: 18px; float: left; padding-top: 30px;">
            <%if(result){
                %>
            <strike><%out.println( totalPrice*2);
           %> </strike>

            <span style="color: red">50 درصد تخفیف برای ساعت اخر</span>


            <%=totalPrice%> هزار تومان  </span>
        <%
            }
        %>
    </div>

    <form action="/jsp/payment.jsp" method="post">
        <input type="hidden" name="cost" value="<%=totalPrice%>">
        <input type="hidden" name="passengerNumber" value="<%=userAccounts.size()%>">
        <input type="hidden" name="TransactionType" value="decrease">
        <input type="hidden" name="ticketCompanyAccount" value="<%=ticketCompanyAccount.get().getId()%>">
        <input type="submit" value="انتقال به درگاه پرداخت" >
    </form>

</div>
</body>
</html>
