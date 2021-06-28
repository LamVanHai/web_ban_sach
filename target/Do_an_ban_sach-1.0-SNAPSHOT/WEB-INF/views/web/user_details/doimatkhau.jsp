<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/08/2021
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:url value="/api/changepassword" var="apiChangePassword"/>
<c:url var="newUrl" value="/nguoi-dung/doi-mat-khau"/>;

<head>
    <title></title>
</head>
<body>
<div class="col-sm-2"></div>
<div class="col-sm-4">
    <div class="main">
        <div class="row padding">
            <form method="" id="formSingin">
                <div><h2 class="userdetails">Đổi mật khẩu</h2></div>
                <div>&nbsp</div>
                <div>
                    <c:if test="${not empty message}">
                        <div class="alert alert-${alert}">
                            ${message}
                        </div>
                    </c:if>
                    <label ></label>

                    <label id="checkpassword"></label>


                </div>
                <div class="form-group">
                    <label class="userdetails1">Mật khẩu cũ:</label>
                    <input type="password"  class="form-control" id="password" name="password" placeholder="nhập mật khẩu cũ">
                </div>
                <div class="form-group">
                    <label class="userdetails1">Mật khẩu mới:</label>
                    <input type="password" class="form-control" id="new_password" placeholder="nhập mật khẩu mới" name="new_password">
                </div>
                <div class="form-group">
                    <label class="userdetails1" >Xác nhận mật khẩu mới:</label>
                    <input type="password" class="form-control" id="confirm_new_password" placeholder="xác nhận mật khẩu mới" name="confirm_new_password">
                </div>

                <div class="form-group" style="float: right;">
                    <button type="reset" class="btn btn-reset">Hủy</button>
                    &nbsp&nbsp&nbsp
                    <button type="button" class="btn btn-submit" id="btnChangePassword">Lưu thay đổi</button>
                </div>
            </form>
        </div>
    </div>
</div>
<div class="col-sm-4"></div>
<script>
    $('#btnChangePassword').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSingin').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        var passWord=$('#new_password').val();
        var confirmPassWord=$('#confirm_new_password').val();
        var input=document.getElementById("new_password");
        var input1=document.getElementById("password");
        var input2=document.getElementById("confirm_new_password");
        var a=document.getElementById("checkpassword");
        if($('#password').val()==""||passWord==""||confirmPassWord==""){
            if(passWord==""){
                input.style.borderColor="red";
            }
            else {
                input.style.borderColor="#979494";
            }
            if($('#password').val()==""){
                input1.style.borderColor="red";
            }
            else {
                input1.style.borderColor="#979494";
            }
            if(confirmPassWord==""){
                input2.style.borderColor="red";
            }
            else {
                input2.style.borderColor="#979494";
            }
        }
        else if(passWord!=confirmPassWord){
            a.style.color="red";
            a.textContent="Mật khẩu mới không khớp! Vui lòng nhập lại";
        }
        else {
            a.textContent="";
            addUser(data);
        }
    });

    function addUser(data) {
        $.ajax({
            url: '${apiChangePassword}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${newUrl}?&message="+result;
            },
            error: function (error) {
                window.location.href = "${newUrl}?message="+error;
            }
        });
    }

</script>
</body>
</html>
