<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
    $(document).ready(function () {
        var total = ${transport_fee}+${CartTotalPrice};
        var total2 = numberFormat.format(total);
        $('#tongcong').text(total2)
    })
    const numberFormat = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });

</script>
<div>&nbsp</div>
<div>&nbsp</div>
<div class="container">
    <div class="col-sm-1"></div>
    <div class="col-sm-6">
        <div class="main">
            <div class="row padding">
                <div><h3 class="userdetails">Thông tin giao hàng</h3></div>
                <div>
                    <c:if test="${param.book != null}">
                        <div class="alert alert-danger">
                            Đặt hàng thất bại, sách đã hết hàng!
                        </div>
                    </c:if>
                    <c:if test="${param.success != null}">
                        <div class="alert alert-success">
                            Đặt hàng thành công! Nhân viên cửa hàng sẽ gọi điện xác nhận đơn hàng trong 5 phút nữa. Cảm
                            ơn bạn đã mua sách trên BookShop.
                        </div>
                    </c:if>
                </div>
                <form:form id="formSingin" modelAttribute="model" method="post" action="/api/newOrder">
                    <div>&nbsp</div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-5"><label for="fee" class="userdetails1">Phương thức vận chuyển :</label>
                            </div>
                            <div class="col-xs-7">
                                <input type="checkbox" id="fee"/>
                                <label for="fee">Có phí</label></div>
                        </div>
                    </div>
                    <div>&nbsp</div>
                    <div class="form-group">
                        <div class="row">
                            <div class="col-xs-5"><label for="status" class="userdetails1">Phương thức thanh
                                toán:</label></div>
                            <div class="col-xs-7">
                                <input type="checkbox"
                                       id="status"/>
                                <label for="status">Thanh toán khi nhận hàng</label>
                            </div>
                        </div>
                    </div>
                    <div>&nbsp</div>
                    <div>&nbsp</div>
                    <div class="form-group">
                        <label for="note" class="col-sm-3 control-label no-padding-right">Ghi chú:</label>
                        <div class="col-sm-9">
                            <form:textarea path="note" rows="5" cols="10" cssClass="form-control" id="note"/>
                        </div>
                    </div>
                    <div>&nbsp</div>
                    <div>&nbsp</div>
                    <div class="form-group" style="float: right;">
                        <c:url var="thanhtoan" value="/gio-hang/xac-nhan-thanh-toan"></c:url>
                        <button style="padding-left: 1px;" type="reset" class="btn btn-reset"><a
                                style="color: white"
                                href="${thanhtoan}">Quay
                            lại</a></button>
                        &nbsp&nbsp&nbsp

                        <button type="submit" class="btn btn-submit">Xác nhận đơn hàng
                        </button>
                    </div>
                </form:form>
            </div>
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
            <div class="col-xs-4"><h4><i><fmt:formatNumber type="number" groupingUsed="true"
                                                           value="${CartTotalPrice}"/> ₫</i></h4></div>
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
            <div class="col-xs-4"><h3 id="tongcong"></h3></div>
        </div>
    </div>
</div>
<div>&nbsp</div>
<div>&nbsp</div>
<div>&nbsp</div>
<script>
    $(document).ready(function () {
        document.getElementById("fee").checked = true;
        document.getElementById("status").checked = true;
    })
</script>
</body>
</html>
