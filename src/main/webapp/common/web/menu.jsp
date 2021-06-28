<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/08/2021
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="menu1">
    <ul class="list-group list-group-flush">
        <li class="list-group-item"><i class="fa fa-user"></i>&nbsp&nbsp<a style="font-size: 16px" href="<c:url value="/nguoi-dung/thong-tin-chi-tiet"/> ">Thông tin nhận hàng</a></li>
        <li class="list-group-item"><i class="fa fa-key"></i>&nbsp&nbsp<a style="font-size: 16px" href="<c:url value="/nguoi-dung/doi-mat-khau"/> ">Đổi mật khẩu</a></li>
        <li class="list-group-item"><i class="fa fa-book"></i>&nbsp&nbsp<a style="font-size: 16px" href="<c:url value="/nguoi-dung/thong-tin-giao-dich?page=1"/> ">Thông tin giao dịch</a></li>
    </ul>
</div>
</body>
</html>
