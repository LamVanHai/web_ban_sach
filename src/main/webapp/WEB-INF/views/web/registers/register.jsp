<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02/28/2021
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="newURL" value="/create_customer"/>
<c:url var="editNewURL" value="/dang-ky"/>
<c:url var="userApI" value="/api/user"/>
<html>
<head>
    <title>Đăng ký</title>
</head>
<body>
<section>
    <div class="row">
        <div class="container">
            <div class="col-xs-12">
                <div class="link">
                    <ul class="unliststyle">
<%--                        <li class="link-item"><a href="">--%>
<%--                            <i class="fa fa-home"></i>--%>
<%--                            <span>Trang Chủ</span>--%>
<%--                        </a></li>--%>
<%--                        <li class="link-item"><a href="">--%>
<%--                            <i class="fa fa-long-arrow-right"></i>--%>
<%--                            <span>Navigation link</span>--%>
<%--                        </a></li>--%>
<%--                        <li class="link-item"><a href="">--%>
<%--                            <i class="fa fa-long-arrow-right"></i>--%>
<%--                            <span>Product name</span>--%>
<%--                        </a></li>--%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <div class="col-sm-6 col-sm-offset-3 col-xs-12">
                <div class="wrapper-authen">
                    <div class="authen-head">
                        <span>Đăng ký</span>
                    </div>
                    <p id="checkpassword"></p>

                    <c:if test="${param.error_system != null}">
                        <div class="alert alert-danger">
                            Lỗi hệ thống!
                        </div>
                    </c:if>
                    <c:if test="${param.email_exist != null}">
                        <div class="alert alert-danger">
                            E-mail đã tồn tại!
                        </div>
                    </c:if>
                    <form  method="" id="formSingin">
                        <div class="form-group">
                            <label for="name">Họ và tên:</label>
                            <input type="texr" class="form-control" id="name" name="name" value="${nameSession}" placeholder="họ và tên...">
                        </div>
                        <div class="form-group">
                            <label for="email">E-mail:</label>
                            <input type="text" class="form-control" id="email" value="${emailSession}" name="email" placeholder="e-mail...">
                        </div>
                        <div class="form-group">
                            <label for="password">Mật khẩu:</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <div class="form-group">
                            <label for="confirmpassword">Xác nhận mật khẩu:</label>
                            <input type="password" class="form-control" id="confirmpassword" name="confirmpassword">
                        </div>
                        <div class="form-group" style="float: right;">
                            <button class="btn btn-reset">Hủy</button>
                            <button type="button" class="btn btn-submit" id="btnAddOrUpdateNew">Đăng ký</button>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<script>
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        var data = {};
        var formData = $('#formSingin').serializeArray();
        $.each(formData, function (i, v) {
            data[""+v.name+""] = v.value;
        });
        var name=$('#name').val();
        var passWord=$('#password').val();
        var confirmPassWord=$('#confirmpassword').val();
        var email=$('#email').val();
        var input=document.getElementById("name");
        var input3=document.getElementById("email");
        var input1=document.getElementById("password");
        var input2=document.getElementById("confirmpassword");
        if(name==""||passWord==""||confirmPassWord==""||email==""){
            if(name==""){
                input.style.borderColor="red";
            }
            else {
                input.style.borderColor="#979494";
            }
            if(passWord==""){
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
            if(email==""){
                input3.style.borderColor="red";
            }
            else {
                input3.style.borderColor="#979494";
            }
        }
        else if(passWord!=confirmPassWord){
            $('#checkpassword').html("<div class=\"alert alert-danger\">\n" +
                "                        Mật khẩu không khớp!</div>")
        }else if(!emailValidate(email)){
            $('#checkpassword').html("<div class=\"alert alert-danger\">\n" +
                "                        Định dạng g-mail không đúng!</div>")
        }else {
            addUser(data);
        }

    });
    function emailValidate(email) {
        var validator=/^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
        if(validator.test(email)){
            return true;
        }
        return false;
    }
    function addUser(data) {
        $.ajax({
            url: '${userApI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType:'Text',
            success: function (result) {
                if(result=="success"){
                    window.location.href = "${newURL}";
                }
                else {
                    window.location.href = "${editNewURL}?"+result;
                }

            },
            error: function (error) {
                window.location.href = "${editNewURL}?"+error;
            }
        });
    }

</script>
</body>
</html>
