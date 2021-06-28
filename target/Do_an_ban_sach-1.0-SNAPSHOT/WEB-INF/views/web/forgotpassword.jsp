<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="newUrl" value="/quen-mat-khau"/>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Đăng nhập</title>

</head>
<body>
<section>
    <div class="row">
        <div class="container">
            <div class="col-xs-12">
                <div class="link">
                    <ul class="unliststyle">
                        <li class="link-item"><a href="">
                            <i class="fa fa-home"></i>
                            <span>Trang Chủ</span>
                        </a></li>
                        <li class="link-item"><a href="">
                            <i class="fa fa-long-arrow-right"></i>
                            <span>Navigation link</span>
                        </a></li>
                        <li class="link-item"><a href="">
                            <i class="fa fa-long-arrow-right"></i>
                            <span>Product name</span>
                        </a></li>
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
                        <span>Lấy lại mật khẩu</span>
                    </div>
<%--                    <c:if test="${param.format_incorrect != null}">--%>
<%--                        <div class="alert alert-danger">--%>
<%--                            Định dạng gmail không đúng!--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
                    <c:if test="${param.not_robot != null}">
                        <div class="alert alert-danger">
                            Chưa xác thực!
                        </div>
                    </c:if>
                    <c:if test="${param.emails != null}">
                        <div class="alert alert-danger">
                            Email không được để trống!
                        </div>
                    </c:if>
                    <c:if test="${param.format_incorrect != null}">
                        <div class="alert alert-danger">
                            Định dạng gmail không đúng!
                        </div>
                    </c:if>
                    <c:if test="${param.success != null}">
                        <div class="alert alert-danger">
                            Gửi thành công !
                            Bạn vui lòng kiểm tra email của bạn.
                        </div>
                    </c:if>
                    <c:if test="${param.gmail_not_exist != null}">
                        <div class="alert alert-danger">
                            Email không tồn tại!
                        </div>
                    </c:if>
                    <c:if test="${param.error_system != null}">
                        <div class="alert alert-danger">
                            Lỗi hệ thống!
                        </div>
                    </c:if>
                    <form action="api/mail" id="formLogin" method="post">
                        <div class="form-group">
                            <label for="email">Nhập email bạn:</label>
                            <input type="text"  class="form-control" id="email" value="${email}" name="email" placeholder="nhập gmail...">
                        </div>
                        <c:if test="${count >2}">
                        <div class="g-recaptcha"
                             data-sitekey="6LdJwbUaAAAAAHCzP5Q3s3fRiQNYdgCuKPlYqQUO">
                        </div>
                        </c:if>
                        <div class="form-group" style="float: right;">
                            <button type="submit"  class="btn btn-submit">Xác nhận</button>
                        </div>

                        <hr>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    <%--$('#btnxacnhan').click(function (e) {--%>
    <%--    e.preventDefault();--%>
    <%--    var email=$('#email').val();--%>
    <%--    var g_captcha=$('#g_captcha_response').val();--%>
    <%--    var data = {"email":email,--%>
    <%--    "g_recaptcha":g_captcha};--%>

    <%--    if(email==""){--%>
    <%--        var input=document.getElementById("email");--%>
    <%--        input.style.borderColor="red";--%>
    <%--    }else {--%>
    <%--        addUser(data)--%>
    <%--    }--%>
    <%--});--%>

    <%--function addUser(data) {--%>
    <%--    $.ajax({--%>
    <%--        url: 'api/mail',--%>
    <%--        type: 'POST',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        success: function (result) {--%>
    <%--            window.location.href = "${newUrl}?"+result;--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            window.location.href = "${newUrl}?"+error;--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>
</script>
</body>
</html>