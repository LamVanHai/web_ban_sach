<%@include file="/common/taglib.jsp" %>
<c:url value="/api/saveCart" var="saveCartAPI"/>
<c:url value="/api/province" var="provinceApi"/>
<c:url value="/api/district" var="districtApi"/>
<c:url value="/api/ward" var="wardApi"/>
<%@ page import="com.bookshop.util.CheckUtil" %>


<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03/08/2021
  Time: 2:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<script>
    const numberFormat = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });
    $(document).ready(function () {
        var total = ${transport_fee}+${CartTotalPrice};
        var total2 = numberFormat.format(total);
        $('#tongcong').text(total2);

    })


</script>
<div>&nbsp</div>
<div>&nbsp</div>
<div class="container">
    <div class="col-sm-1"></div>
    <div class="col-sm-5">
        <div class="main">
            <div class="row padding">
                <div><h3 class="userdetails">Thông tin giao hàng</h3></div>
                <div>
                    <c:if test="${param.nullDetails != null}">
                        <div class="alert alert-danger">
                            Họ tên, địa chỉ, số điện thoại không được để trống!
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
                <form:form method="post" action="/api/saveCart" id="formSingin" modelAttribute="model">
                <div>&nbsp</div>
                <div class="form-group">
                    <label class="userdetails1">Họ và tên:</label>
                    <form:input path="name" type="text" class="form-control" id="name"/>
                </div>
                <div class="form-group">
                    <label class="userdetails1">Sô điện thoại:</label>
                    <form:input type="phone" path="phone_number" class="form-control" id="phone_number"/>
                </div>
                <div class="form-group">
                    <label class="userdetails1">Địa chỉ:</label>
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
                    <%--                        </div>--%>
            </div>
            <div>&nbsp</div>
            <div class="form-group" style="float: right;">
                <c:url var="giohang" value="/gio-hang"></c:url>
                <button style="padding-left: 1px;" type="reset" class="btn btn-reset"><a style="color: white"
                                                                                         href="${giohang}">Giỏ hàng</a>
                </button>
                &nbsp&nbsp&nbsp
                <c:url value="/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang" var="xacnhandonhang"/>
                <button type="submit" class="btn btn-submit" id="btnAddOrUpdateNew">Tiếp tục đến phương thức thanh
                    toán
                </button>
            </div>
            </form:form>
        </div>
    </div>

    <div class="col-sm-1">

    </div>
    <div class="col-sm-5">

        <div><h3 class="userdetails">Thông tin đơn hàng</h3></div>
        <div>&nbsp</div>
        <div>&nbsp</div>

        <c:forEach var="item" items="${Cart}">
            <li class="cart-item unliststyle">
                <div class="row">
                    <div class="col-xs-3">
                        <img style="width: 40px; height: 50px"
                             src="${pageContext.request.contextPath}/image/${item.value.book.image} "/>
                    </div>
                    <div class="col-xs-5"><p>${item.value.book.name}</p></div>
                    <div class="col-sm-4">
                        <p>${item.value.quanty} x <i><fmt:formatNumber type="number" groupingUsed="true"
                                                                       value="${item.value.book.price}"/> ₫</i></p>
                    </div>
                </div>

            </li>
            <hr style="width: 100%;height: 1px; color: #1C1D1E">
        </c:forEach>
        <div class="row">
            <div class="col-xs-8"><h4>Tạm tính:</h4></div>
            <div class="col-xs-4"><h4><i><fmt:formatNumber type="number" groupingUsed="true" value="${CartTotalPrice}"/>
                ₫</i></h4></div>

        </div>
        <div class="row">
            <div class="col-xs-8"><h4>Phí vận chuyển:</h4></div>
            <div class="col-xs-4"><h4 id="phivanchuyen"><i><fmt:formatNumber type="number" groupingUsed="true"
                                                                             value="${transport_fee}"/> ₫</i></h4></div>
        </div>
        <div>&nbsp</div>
        <div>&nbsp</div>
        <div class="row">
            <div class="col-xs-8"><h3>Tổng cộng</h3></div>
            <div class="col-xs-4"><h3 id="tongcong"><i><fmt:formatNumber type="number" groupingUsed="true"
                                                                         value="${CartTotalPrice}"/> ₫</i></h3></div>
        </div>
    </div>
</div>
<div>&nbsp</div>
<div>&nbsp</div>
<div>&nbsp</div>
<script>
    function btnHuy() {
        window.location.href = "/gio-hang";
    }


    // $('#btnthanhtoan').click(function (e) {
    //     e.preventDefault();
    //     var array = document.getElementsByTagName("input");
    //     var adress1 = $('.address1').val();
    //     var name1 = $('#name').val();
    //     var phone1 = $('#phone_number').val();
    //     var thanhtoan = array[3].checked;
    //     if (name1 == "" || adress1 == "" || phone1 == "") {
    //         alert("Trường 'Tên', 'Địa chỉ', 'Điện thoại' không được để trống !!");
    //         return;
    //     }
    //     if (!thanhtoan) {
    //         alert("Bạn chưa chọn hình thức thanh toán!!!");
    //         return;
    //     }
    //     var data = {};
    //     var formData = $('#formSubmit1').serializeArray();
    //     $.each(formData, function (i, v) {
    //         data["" + v.name + ""] = v.value;
    //     });
    //     addNew(data);
    //
    // });

    <%--function addNew(data) {--%>
    <%--    $.ajax({--%>
    <%--        url: '${saveCartAPI}',--%>
    <%--        type: 'PUT',--%>
    <%--        contentType: 'application/json',--%>
    <%--        data: JSON.stringify(data),--%>
    <%--        dataType: 'json',--%>
    <%--        success: function (result) {--%>
    <%--            alert(result);--%>
    <%--            window.location.href = "http://localhost:8081/gio-hang";--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            alert(error);--%>
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

    function changeward() {
        var id = $('#ward_id').val();
        var data = {
            ward_id: id
        };
        const numberFormat = new Intl.NumberFormat('vi-VN', {
            style: 'currency',
            currency: 'VND',
        });
        $.ajax({
            url: '${wardApi}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                var fee=numberFormat.format(result.transport_fee)
                $('#phivanchuyen').text(fee);
                var total3=numberFormat.format(result.total)
                $('#tongcong').text(total3);
            },
            error: function (error) {
                alert(error);
            }
        });
    }

</script>

</body>
<footer>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</footer>
</html>
