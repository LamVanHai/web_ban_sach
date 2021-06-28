<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="oderAPI" value="/api/huydonhang"/>
<c:url var="newURL" value="/quan-tri/don-hang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<div class="col-sm-10" >
    <div class="main-content">
        <form action="<c:url value='/quan-tri/don-hang'/>" id="formSubmit" method="get">
            <div class="main-content-inner">
                <h2 class="userdetails">Thông tin giao dịch</h2>
                <div class="page-content">
                    <div class="row">
                        <div class="col-xs-12">
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Thao tác</th>
                                                <th>Tên khách hàng</th>
                                                <th>Địa chỉ</th>
                                                <th>Số điện thoại</th>
                                                <th>Ghi chú</th>
                                                <th>Thanh toán</th>
                                                <th>Tổng giá</th>
                                                <th>Ngày đặt hàng</th>
                                                <th>Trạng thái</th>
                                                <th>Chi tiết đơn hàng</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="item" items="${model}">
                                                <tr>
                                                    <c:if test="${item.status==0}">
                                                        <td>
                                                            <button style="background: #1da1f2" type="button"
                                                                    class="xac-nhan" data-id="${item.id}">Hủy đơn hàng
                                                            </button>
                                                        </td>
                                                    </c:if>
                                                    <c:if test="${item.status!=0}">
                                                        <td>
                                                            <button type="button" disabled class="xac-nhan"
                                                                    data-id="${item.id}">Hủy đơn hàng
                                                            </button>
                                                        </td>
                                                    </c:if>
                                                    <th>${item.user_order.name}</th>
                                                    <th>${item.user_order.address}</th>
                                                    <th>${item.user_order.phone_number}</th>
                                                    <th>${item.note}</th>
                                                    <th>${item.payment_info}</th>
                                                    <th>${item.price_total}</th>
                                                    <th>${item.createdDate}</th>
                                                    <th>${item.info}</th>
                                                    <td>
                                                        <c:url var="chitiet"
                                                               value="/nguoi-dung/thong-tin-giao-dich/chi-tiet-don-hang?page=1&limit=5">
                                                            <c:param name="id" value="${item.id}"></c:param>
                                                        </c:url>
                                                        <a href='${chitiet}'>Xem chi tiết</a>
                                                    </td>
                                                </tr>
                                                <input type="hidden" class="status" value="${item.status}"/>
                                            </c:forEach>

                                            </tbody>
                                        </table>
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" id="status" name="status"/>
                                        <input type="hidden" value="" id="page" name="page"/>
                                        <input type="hidden" value="" id="limit" name="limit"/>

                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- /.main-content -->
<script>
    var totalPages = ${page.totalPage};
    var currentPage = ${page.page};
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#status').val(0);
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('.xac-nhan').on("click", function () {
        var id = $(this).data("id");
        var status = $('.status').val();

        swal({
            title: "Xác nhận hủy",
            text: "Bạn có chắc chắn muốn hủy đơn hàng này không",
            type: "warning",
            showCancelButton: true,
            showConfirmButton: true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
            closeOnConfirm: false,
        }).then(function (isConfirm) {
            if (isConfirm) {
                var data = {
                    id: id,
                    status: status
                };
                deleteOrder(data);
            } else {
                window.location.href = "/nguoi-dung/thong-tin-giao-dich?page=1&limit=5";
            }
            ;
        })
    })

    function deleteOrder(data) {
        $.ajax({
            url: '${oderAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/nguoi-dung/thong-tin-giao-dich?page=1&limit=5";
            },
            error: function (error) {
                window.location.href = "/nguoi-dung/thong-tin-giao-dich?page=1&limit=5";
            }
        })

    }

    <%--$('.xac-nhan').on("click",function () {--%>
    <%--    var id=$(this).data("id");--%>
    <%--    var status=$('.status').val();--%>
    <%--    var data={id:id,--%>
    <%--        status:status};--%>
    <%--    $.ajax({--%>
    <%--        url: '${oderAPI}',--%>
    <%--        type: 'POST',--%>
    <%--        contentType:'application/json',--%>
    <%--        data:JSON.stringify(data),--%>
    <%--        dataType: 'json',--%>
    <%--        success: function (result) {--%>
    <%--            window.location.href="/quan-tri/don-hang?status=${status1}&page=1&limit=5";--%>
    <%--        },--%>
    <%--        error: function (error) {--%>
    <%--            window.location.href="/quan-tri/don-hang?status=${status1}&page=1&limit=5";--%>
    <%--        }--%>
    <%--    })--%>
    <%--})--%>

</script>
</body>
</html>