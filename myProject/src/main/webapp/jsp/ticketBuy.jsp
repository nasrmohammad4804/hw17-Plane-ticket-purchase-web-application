<%@ page import="java.time.LocalDate" %>
<%@ page import="service.util.ApplicationContext" %>
<%@ page import="service.impl.TicketCompanyAccountServiceImpl" %>
<%@page import="java.util.List" %>
<%@ page import="domain.TicketCompanyAccount" %>
<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>


<%--

  Created by IntelliJ IDEA.
  User: User
  Date: 10/9/2021
  Time: 9:24 AM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            direction: rtl;
        }

        .master {
            width: 100%;
            height: 100px;
            margin-top: 20px;
            background-color: #c2c5aa;
            border-radius: 5px;
            border: 2px solid black;
        }

        .master4 {

            float: left;
            border-right: dashed 2px rgb(131, 118, 118);
            width: 19%;
            height: 100%;
            text-align: center;
        }

        .master1 {

            width: 12%;

            float: right;
            padding-right: 10px;
        }

        .master2 {
            float: right;
            width: 14%;
            font-weight: bold;
            margin-right: 3%;
            margin-left: 4%;
        }

        .master3 {

            float: right;
            width: 20%;
            text-align: center;

            margin-right: 5px;
        }

        .header {
            float: right;
            text-align: center;
            margin: 5px 0;
        }

        .arr {

            padding-top: 50px;
        }

        .arr2 {

            padding-top: 15px;
        }

        .master5 {
            float: right;
            width: 14%;
            margin-right: 18%;
            padding-bottom: 30px;
            padding-top: 15px;
            padding-right: 2px;
        }

        .mybutton {

            width: 80px;
            height: 30px;
            background-color: darkcyan;
            border-radius: 5px;
            font-size: 18px;
            font-weight: bold;
        }



    </style>
</head>

<body>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<%

    TicketCompanyAccountServiceImpl ticketCompanyAccountService = ApplicationContext
            .getApplicationContext().getTicketCompanyAccountService();

    String origin = request.getParameter("origin");
    String destination = request.getParameter("destination");
    String localDate = request.getParameter("flightDate");
    int passengerNumber = Integer.parseInt(request.getParameter("quantity"));


    List<TicketCompanyAccount> ticketList = ticketCompanyAccountService.findAllByDestinationAndOrigin
            (origin, destination, LocalDate.parse(localDate), passengerNumber);


    if (ticketList.isEmpty())
        request.getRequestDispatcher("/notFound").forward(request, response);

    for (TicketCompanyAccount ticketCompanyAccount : ticketList) {

%>
<%response.addCookie(new Cookie("passengerNumber", request.getParameter("quantity")));%>
<div class="master">


    <div class=" master4"><p> تومان <%=ticketCompanyAccount.getTicket().getAmount()%>
    </p>

        <form action="/jsp/getDataOfUserForBuyTicket.jsp" method="post">
            <input type="hidden" name="ticketCompanyAccount" value=<%=ticketCompanyAccount.getId()%>>
            <input class="mybutton" type="submit" value="انتخاب بلیط">
        </form>
        <span style="color: red"> نفر <%=ticketCompanyAccount.getTicket().getCapacity()%> ظرفیت: </span>
    </div>
    <h2 class="header"><%=ticketCompanyAccount.getTicket().getTakeOfTime().toLocalDate()%>
    </h2>

    <div class="arr master1"> مبدا: <%=ticketCompanyAccount.getTicket().getOriginCity()%>
    </div>
    <div class="arr master2"> ساعت حرکت &nbsp;
        &nbsp; <%=ticketCompanyAccount.getTicket().getTakeOfTime().toLocalTime()%>
    </div>
    <div class=" arr2 master3"> مدت پرواز <%=ticketCompanyAccount.getTicket().getFlightPeriod()%>
    </div>
    <div class=" master5">مقصد : <%=ticketCompanyAccount.getTicket().getDestinationCity()%>
    </div>

    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</div>
<%
    }
%>
</body>
</html>


