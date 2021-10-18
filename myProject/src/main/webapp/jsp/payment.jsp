<%@ page import="java.util.Random" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/12/2021
  Time: 2:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

        #first-div {
            float: right;
            margin-left: 20px;
            width: 45%;

        }

        .div {
            border: 3px solid grey;
            border-radius: 2px;
            height: 250px;
        }

        #second-div {

            float: right;
            border: 2px solid grey;
            width: 35%;
        }

        #cancel {
            background-color: rgb(209, 49, 49);
            font-size: 18px;
            color: white;
            width: 110px;
            height: 40px;
            text-align: center;
            float: right;
            text-decoration: none;
            border-radius: 3px;
            box-sizing: border-box;
            padding: 11px 0;
        }

        #confirm {
            float: right;
            background-color: rgb(90, 228, 90);
            font-size: 18px;
            color: white;
            width: 110px;
            height: 40px;
            margin-left: 10px;
            text-align: center;
            border-radius: 3px;
            box-sizing: border-box;
            padding: 11px 0;
            margin-right: 5px;

        }
    </style>
</head>
<body>

<%
    String cost = request.getParameter("cost");
    String transactionType = request.getParameter("TransactionType");
%>

<div id="first-div" class="div">

    <form action="/payment" method="get" >
        <label for="cardNumber">شماره کارت</label>
        <input type="text" id="cardNumber" name="cardNumber" pattern="[0-9]{16}"><br><br>
        <label for="cvv2">شماره شناسایی دوم(cvv2)</label>
        <input type="text" name="cvv2Number" id="cvv2" pattern="[0-9]{3}"> <br><br>
        <label>تاریخ انقضای کارت</label>
        <input type="text" placeholder="ماه" pattern="([0-9]|1[0-2])">
        <input type="text" placeholder="سال"> <br><br>

        <%
            int securityCode = new Random().nextInt(1000 - 100 + 1) + 100;

        %>
        <label for="securityCode">کد امنیتی</label>
        <input type="text" id="securityCode" name="securityCode">
        <input type="hidden" name="actualSecurityCode" value="<%=securityCode%>">
        <span><%=securityCode%></span>
        <br><br>

        <input id="confirm" type="submit" value="پرداخت">
        <a href="#" id="cancel">انصراف</a>
        <input type="hidden" name="cost" value="<%=cost%>">
        <input type="hidden" name="TransactionType" value="<%=transactionType%>">
        <input type="hidden" name="passengerNumber" value="<%=request.getParameter("passengerNumber")%>">
        <input type="hidden" name="ticketCompanyAccount" value="<%=request.getParameter("ticketCompanyAccount")%>">
    </form>
</div>

<div id="second-div" class="div">
    <p style="background-color: deepskyblue; font-size: 17px;">اطلاعات پذیرنده</p>

    <p><span style="font-weight: bold;">نام پذیرنده</span> &nbsp; &nbsp;&nbsp;&nbsp; سفر های علی بابا</p>
    <p><span style="font-weight: bold;">ادرس سایت پذیرنده</span> &nbsp; &nbsp;&nbsp;&nbsp;alibaba.ir</p>
    <p><span style="font-weight: bold">مبلغ قابل پرداخت</span> &nbsp; &nbsp;&nbsp;&nbsp;<%=cost%>
    </p>

</div>
</body>
</html>
