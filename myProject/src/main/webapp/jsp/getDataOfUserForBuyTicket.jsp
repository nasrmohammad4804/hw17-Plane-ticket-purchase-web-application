<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/10/2021
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    body{

        direction: rtl;
    }
    .master{

        margin-bottom: 30px;
        width: 100%;
        height: 90px;
    }
    .input{

        width:20%;
        height: 30%;
        border: 2px solid gray;
        border-radius: 5pc;
        margin-right: 10px;
        font-size: 20px;
        font-weight: bold;
        text-align: center;
    }
    #mySubmit{

        width: 150px;
        height: 35px;
        background-color: cornflowerblue;
        font-size: 18px;
        border: 2px black solid;
        border-radius: 3px;
        background-position: left bottom;
        position: absolute;
        top: 92%;
        right: 77%;
    }
</style>
<body>


    <form action="/checkPassenger" method="get" >
        <input type="hidden" name="ticketCompanyAccount" value=<%=request.getParameter("ticketCompanyAccount")%>>
        <%
           long passengerNumber=Long.parseLong( Arrays.stream(request.getCookies()).
                   filter(x -> x.getName().equals("passengerNumber")).findFirst().get().getValue());



           for(int i=1; i<=passengerNumber;i++){
               %>
        <div class="master">


            <input class="input" type="text" name="<%="name"+i%>" placeholder="نام">
            <input type="hidden" name="passengerNumber" value="<%=passengerNumber%>">
            <input class="input" type="text" placeholder="نام خانوادگی" name='<%="family"+i%>'>
            <input class="input" list="gender" required name= '<%="gender"+i%>' placeholder="جنسیت" >
            <datalist id="gender">

                <option value="men">مرد</option>
                <option value="women">زن</option>
            </datalist>
            <input class="input" type="text" name='<%="nationalCode"+i%>' placeholder="کد ملی" >
        </div>

        <div>
            <input type="submit" id="mySubmit" value="تایید و ادامه پرداخت">
        </div>

        <%
            }
        %>
    </form>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
</body>
</html>
