<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/10/2021
  Time: 11:10 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="domain.UserAccount" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <style>
        #imag1 {

            background-image: url('https://gozarino.com/wp-content/uploads/2021/05/%D9%87%D9%88%D8%A7%D9%BE%DB%8C%D9%85%D8%A7-%D9%85%D8%B3%D8%A7%D9%81%D8%B1%D8%A8%D8%B1%DB%8C-%D8%A8%D9%88%D8%A6%DB%8C%D9%86%DA%AF-.jpg');
            background-repeat: no-repeat;
            width: 100%;
            height: 600px;
            background-size: 100% 600px;

        }

        body {
            direction: rtl;
        }

        .master-div {
            float: right;
            margin-top: 60px;
            margin-right: 20px;
        }

        .travel {
            width: 80px;
            border: 3px solid grey;
            border-radius: 5px;
            background-color: darkslategrey;
            color: white;

        }

        .myinput {
            margin-right: 10px;
            color: white;

        }

        #date {
            background-color: darkslategrey;
            border: 3px solid grey;
            border-radius: 5px;
            color: white;
        }

        #quan {
            background-color: darkslategrey;
            border: 3px solid grey;
            border-radius: 5px;
            color: white;
        }

        #link {
            box-sizing: border-box;
            margin-top: 550px;
            float: right;
            color: red;
            width: 100px;
            height: 40px;
            border: 3px solid black;
            background-color: aqua;
            font-size: 18px;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            padding-top: 5px;
            margin-right: 45px;
        }
    </style>
</head>

<body>

<%UserAccount userAccount= (UserAccount) session.getAttribute("user");%>
<h3>سلام  <%=userAccount.getName()+"   "+userAccount.getFamily()%> خوش امدید به علی بابا</h3>

<div id="imag1">

    <form action="/jsp/ticketBuy.jsp" method="post">

        <div class="master-div">
            <input class="travel" list="cities1" name="origin" placeholder="مبدا" required>
            <datalist id="cities1">

                <option value="تهران">تهران</option>
                <option value="مشهد">مشهد</option>
                <option value="کرج">کرج</option>
                <option value="اصفهان">اصفهان</option>
                <option value="شیراز">شیراز</option>
                <option value="قم">قم</option>
                <option value="بندر عباس">بندر عباس</option>
                <option value="کرمان">کرمان</option>
                <option value="تبریز">تبریز</option>
                <option value="سمنان">سمنان</option>
                <option value="یزد">یزد</option>
                <option value="قشم">قشم</option>
                <option value="کیش">کیش</option>
                <option value="اردبیل">اردبیل</option>
                <option value="یاسوج">یاسوج</option>
            </datalist>
        </div>
        <div class="master-div" id="origin-city">

        </div>
        <div id="destination-city" class="master-div">

            <input list="cities2" name="destination" placeholder="مقصد" required class="travel">
            <datalist id="cities2">

                <option value="تهران">تهران</option>
                <option value="مشهد">مشهد</option>
                <option value="کرج">کرج</option>
                <option value="اصفهان">اصفهان</option>
                <option value="شیراز">شیراز</option>
                <option value="قم">قم</option>
                <option value="بندر عباس">بندر عباس</option>
                <option value="کرمان">کرمان</option>
                <option value="تبریز">تبریز</option>
                <option value="سمنان">سمنان</option>
                <option value="یزد">یزد</option>
                <option value="قشم">قشم</option>
                <option value="کیش">کیش</option>
                <option value="اردبیل">اردبیل</option>
                <option value="یاسوج">یاسوج</option>
            </datalist>

        </div>

        <div class="master-div">
            <label class="myinput" for="date">تاریخ پرواز:</label>
            <input type="text" id="date" name="flightDate" required placeholder="">

        </div>
        <div class="master-div ">
            <label class="myinput" for="quan">تعداد مسافران:</label>
            <input type="number" max="5" min="1" id="quan" name="quantity" required>

        </div>
        <div>

            <input id="link" type="submit" value="جستجوی پرواز">
        </div>

    </form>
</div>

</body>
</html>

