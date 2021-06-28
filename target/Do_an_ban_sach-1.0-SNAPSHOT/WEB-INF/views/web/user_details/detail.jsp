<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/08/2021
  Time: 4:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:url value="/api/user_detail" var="userApi"/>
<c:url value="/api/province" var="provinceApi"/>
<c:url value="/api/district" var="districtApi"/>
<c:url value="/api/ward" var="wardApi"/>
<c:url var="newUrl" value="/nguoi-dung/thong-tin-chi-tiet"/>
<html>
<head>
    <title></title>
</head>
<body>
<div class="col-sm-2"></div>
<div class="col-sm-6">
    <div class="main">
        <div class="row padding">
            <div><h2 class="userdetails">Thông tin nhận hàng</h2></div>
            <div>
                <c:if test="${param.nullDetails != null}">
                    <div class="alert alert-danger">
                        Họ tên, địa chỉ, số điện thoại không được để trống!
                    </div>
                </c:if>
                <c:if test="${param.success != null}">
                    <div class="alert alert-success">
                        Cập nhật thành công!
                    </div>
                </c:if>
                <c:if test="${param.nullArea != null}">
                    <div class="alert alert-danger">
                        Tỉnh/thành phố, quận/huyện, phường/xã không được để trống!
                    </div>
                </c:if>

                <c:if test="${param.phone != null}">
                    <div class="alert alert-danger">
                        SỐ điện thoại đã tồn tại! vui lòng nhập số khác
                    </div>
                </c:if>
                <c:if test="${param.phonevalidate != null}">
                    <div class="alert alert-danger">
                        Số điện thoại không đúng định dạng!
                    </div>
                </c:if>
            </div>
            <form:form  action="/api/user_detail" method="post" id="formSingin" modelAttribute="model">

                <div>&nbsp</div>
                <div class="form-group">
                    <label class="userdetails1">Full name:</label>
                    <form:input path="name" type="text" class="form-control" id="name"/>
                </div>
                <div class="form-group">
                    <label class="userdetails1">Phone:</label>
                    <form:input type="text" path="phone_number" class="form-control" id="phone_number"/>
                </div>
                <div class="form-group">
                    <label class="userdetails1">Address:</label>
                    <form:input path="address" type="text" class="form-control" id="address"/>
                </div>
                <div>&nbsp</div>
                <div><label class="col-sm-3 control-label no-padding-right">Chọn tỉnh/thành phố:</label></div>
                <div class="form-group">
                    <div class="col-sm-9">
                        <form:select path="province_id" id="province_id" onchange="changprovince()">
                            <form:option value="" label="-- Chọn tỉnh/thành phố--"/>
                            <form:options items="${province}"/>
                        </form:select>
                    </div>
                </div>
                <div>&nbsp</div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right">Chọn quận/huyện:</label>
                    <div class="col-sm-9">
                        <form:select path="district_id" id="district_id" onchange="changedistrict()">
                            <form:option value="" label="-- Chọn quận/huyện--"/>
                            <form:options items="${district}"/>
                        </form:select>
                    </div>
                </div>
                <div>&nbsp</div>
                <div class="form-group">
                    <label class="col-sm-3 control-label no-padding-right">Chọn phường/xã:</label>
                    <div class="col-sm-9">
                        <form:select path="ward_id" id="ward_id" onchange="changeward()">
                            <form:option value="" label="-- Chọn phường/xã--"/>
                            <form:options items="${ward}"/>
                        </form:select>
                    </div>
                </div>

                <div class="form-group" style="float: right;">
                    <button type="reset" class="btn btn-reset">Hủy</button>&nbsp&nbsp&nbsp
                    <button type="submit" class="btn btn-submit" id="btnAddOrUpdateNew">Lưu thay đổi</button>
                </div>
            </form:form>
        </div>
    </div>
</div>
<div class="col-sm-2"></div>
<script>
    <%--$('#btnAddOrUpdateNew').click(function (e) {--%>
    <%--    e.preventDefault();--%>
    <%--    var data = {};--%>
    <%--    var formData = $('#formSingin').serializeArray();--%>
    <%--    $.each(formData, function (i, v) {--%>
    <%--        data[""+v.name+""] = v.value;--%>
    <%--    });--%>
    <%--    var name=$('#name').val();--%>
    <%--    var phone=$('#phone_number').val();--%>
    <%--    var address=$('#address').val();--%>

    <%--    if (name==""||phone==""||address=="") {--%>
    <%--        if(name==""){--%>
    <%--            var input=document.getElementById("name");--%>
    <%--            input.style.borderColor="red";--%>
    <%--        }--%>
    <%--        if(phone==""){--%>
    <%--            var input=document.getElementById("phone");--%>
    <%--            input.style.borderColor="red";--%>
    <%--        }--%>
    <%--        if(address==""){--%>
    <%--            var input=document.getElementById("address");--%>
    <%--            input.style.borderColor="red";--%>
    <%--        }--%>

    <%--    } else {--%>
    <%--        update(data);--%>
    <%--    }--%>
    <%--});--%>
    <%--function update(data) {--%>
    <%--    $.ajax({--%>
    <%--        url:'${userApi}',--%>
    <%--        contentType:'application/json',--%>
    <%--        method:'POST',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        success: function (result) {--%>
    <%--            window.location.href = "${newUrl}?&message="+result;--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            window.location.href = "${newUrl}message=error_system";--%>
    <%--        }--%>

    <%--    });--%>
    <%--}--%>

    function changprovince() {
        var id = $('#province_id').val();
        var data = {
            province_id: id
        };
        $.ajax({
            url: '${provinceApi}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function (result) {
                var input = document.getElementById("district_id");
                input.innerHTML = result;
            },
            error: function (error) {
                alert(error);
            }
        });
    }

    function changedistrict() {
        var id = $('#district_id').val();
        var data = {
            district_id: id
        };
        $.ajax({
            url: '${districtApi}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'html',
            success: function (result) {
                var input = document.getElementById("ward_id");
                input.innerHTML = result;
            },
            error: function (error) {
                alert(error);
            }
        });
    }

    <%--function changeward() {--%>
    <%--    var id = $('#ward_id').val();--%>
    <%--    var data = {--%>
    <%--        ward_id: id--%>
    <%--    };--%>
    <%--    $.ajax({--%>
    <%--        url: '${wardApi}',--%>
    <%--        type: 'POST',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        dataType: 'json',--%>
    <%--        success: function (result) {--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            alert(error);--%>
    <%--        }--%>
    <%--    });--%>
    <%--}--%>

</script>
</body>
</html>
