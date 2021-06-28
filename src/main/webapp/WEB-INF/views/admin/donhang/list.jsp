<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="oderAPI" value="/api/Order"/>
<c:url var="newURL" value="/quan-tri/don-hang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Danh sách bài viết</title>
</head>

<body>
<div class="main-content">
    <div class="breadcrumbs ace-save-state" id="breadcrumbs">
        <ul class="breadcrumb">
            <li>
                <i class="ace-icon fa fa-home home-icon"></i>
                <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
            </li>
        </ul>
        <!-- /.breadcrumb -->
    </div>
    <h4 style="font-weight: bold">Quản lý đơn hàng</h4>
    <div class="col-xs-3">
    </div>
    <div class="col-xs-6">
        <div class="head-search">
            <input id="headSearch" type="text" placeholder="Nhập từ khóa... ">
            <button type="btn button" onclick="btnTimKiem()">Tìm kiếm</button>
        </div>
    </div>
    <div class="col-xs-3">
    </div>
    <form action="<c:url value='/quan-tri/don-hang'/>" id="formSubmit" method="get">
        <div class="main-content-inner">
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">
                                    ${message}
                            </div>
                        </c:if>
                        <div class="widget-box table-filter">
                            <div class="table-btn-controls">
                                <div class="pull-right tableTools-container">
                                    <div class="dt-buttons btn-overlap btn-group">
                                        <c:url var="createNewURL" value="/quan-tri/bai-viet/chinh-sua"/>

                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <c:if test="${status1!=2}">
                                                <th>Xác nhận</th>
                                                <th>Xóa đơn hàng</th>
                                            </c:if>
                                            <th>Tên khách hàng</th>
                                            <th>Địa chỉ</th>
                                            <th>Số điện thoại</th>
                                            <th>Ghi chú</th>
                                            <th>Thanh toán</th>
                                            <th>Tổng giá</th>
                                            <th>Ngày đặt hàng</th>
                                            <th>Ngày cập nhật</th>
                                            <th>Chi tiết đơn hàng</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.data}">
                                            <tr>
                                                <c:if test="${status1!=2}">
                                                    <td>
                                                        <button style="background: #1da1f2" type="button"
                                                                class="xac-nhan" data-id="${item.id}">xác nhận
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button style="background: #1da1f2" type="button" class="xoa"
                                                                data-id="${item.id}">xóa
                                                        </button>
                                                    </td>
                                                </c:if>

                                                <th>${item.user_order.name}</th>
                                                <th>${item.user_order.address}</th>
                                                <th>${item.user_order.phone_number}</th>
                                                <th>${item.note}</th>
                                                <th>${item.payment_info}</th>
                                                <th><p><fmt:formatNumber type="number" groupingUsed="true" value="${item.price_total}" /> ₫</p></th>
                                                <th>${item.createdDate}</th>
                                                <th>${item.modifiedDate}</th>
                                                <td>
                                                    <c:url var="chitiet"
                                                           value="/quan-tri/don-hang/chi-tiet-don-hang?page=1&limit=5">
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
                                    <input type="hidden" value="" id="search" name="search"/>

                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- /.main-content -->
<script>
    var totalPages = ${model.totalPage};
    var currentPage = ${model.page};
    var search="${search}";
    var status="${status1}"
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#status').val(status);
                    $('#search').val(search),
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('.xoa').on("click", function () {
        var id = $(this).data("id");
        var status = $('.status').val();
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa hay không",
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
                var data = {id: id,
                status:status};
                deleteOrder(data);
            } else {
                window.location.href = "/quan-tri/don-hang?status=${status1}&page=1";
            }
            ;
        })
    })

    function deleteOrder(data) {
        $.ajax({
            url: '${oderAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/quan-tri/don-hang?status=${status1}&search=all&page=1";
            },
            error: function (error) {
                window.location.href = "/quan-tri/don-hang?status=${status1}&search=all&page=1";
            }
        })

    }
    function btnTimKiem() {
        var data = {value: $('#headSearch').val()}
        $.ajax({
            url: '/tim-kiem',
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${newURL}?search=" + result.value +"&status=${status1}&page=1";
                $('#headSearch').text(result.value)
            },
            error: function (error) {
                window.location.href = "${newURL}?search=" + error.value + "&status=${status1}&page=1";
            },
        });
    }

    $('.xac-nhan').on("click", function () {
        var id = $(this).data("id");
        var status = $('.status').val();
        var data = {
            id: id,
            status: status
        };
        $.ajax({
            url: '${oderAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/quan-tri/don-hang?status=${status1}&search=all&page=1";
            },
            error: function (error) {
                window.location.href = "/quan-tri/don-hang?status=${status1}&search=all&page=1";
            }
        })
    })

</script>
</body>
</html>