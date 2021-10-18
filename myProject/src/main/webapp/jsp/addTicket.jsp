<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10/10/2021
  Time: 11:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body{
            direction: rtl;
        }
        #mySubmit{
            width: 100px;
            height: 40px;
            background-color: aqua;
            border: 2px solid black;
            border-radius: 5px;
            font-weight: bold;
            font-size: 20px;
            margin-right:80px;
        }
        #origin-input{
            margin-right: 47px;
        }
        #dest-input{
            margin-right: 40px;
        }
        #travel-date{
            margin-right: 4px;
        }
        #travel-time{
            margin-right: 5px;
        }
        #travel-period{
            margin-right: 15px;
        }
        .last-input{
            margin-right: 47px;
        }
        .input{
            border: 2px solid black;
            border-radius: 4px;
            height: 40px;
        }
    </style>
</head>
<body>

<form action="/addTicket" method="post">
    <label id="origin-input" for="origin">مبدا:</label>
    <input class="input" type="text" id="origin" name="origin" list="cities1">

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
    <br><br>


    <label id="dest-input" for="dest">مقصد:</label>
    <input class="input" type="text" name="destination" id="dest" list="cities2" >

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

    <br><br>
    <label  id="travel-date" for="day">تاریخ حرکت:</label>
    <input class="input" type="text" id="day" name="flightDate" > <br><br>
    <label id="travel-time" for="timeFlight">ساعت پرواز:</label>
    <input class="input" type="text" id="timeFlight" name="timeFlight"> <br><br>
    <label id="travel-period" for="period">مدت پرواز:</label>
    <input class="input" type="text" id="period" name="period"> <br> <br>
    <label class="last-input" for="amount">قیمت:</label>
    <input class="input" type="text" id="amount" name="amount"> <br><br>
    <label class="last-input" for="quan">تعداد:</label>
    <input class="input" style="width: 170px;" type="number" id="quan" min="10" max="70" name="quantity"> <br><br>

    <input id="mySubmit" type="submit" value="ثبت پرواز">


</form>
</body>

</html>
