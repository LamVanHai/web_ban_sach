<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<c:url var="oderAPI" value="/api/Order"/>
<c:url var="newURL" value="/quan-tri/don-hang"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Chi tiết hóa đơn</title>
</head>
<body>
<div class="main-content">
    <form action="<c:url value='/quan-tri/don-hang/chi-tiet-don-hang'/>" id="formSubmit" method="get">

        <div class="main-content-inner">
            <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value='/trang-chu'/>">Trang chủ</a>
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
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
                                            <th><input type="checkbox" id="checkAll"></th>
                                            <th>Tên sách</th>
                                            <th>Ảnh</th>
                                            <th>Số lượng</th>
                                            <th>giá</th>
                                            <th>Ngày thêm</th>
                                            <th>Ngày cập nhật</th>

                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model}">
                                            <tr>
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}"></td>
                                                <th>${item.book3.name}</th>
                                                <th><img src="${pageContext.request.contextPath}/image/${item.book3.image} "/></th>
                                                <th><input type="number" id="amount+${item.id}" value="${item.amount}"/></th>
                                                <th><p class="prices"><fmt:formatNumber type="number" groupingUsed="true" value="${item.unit_price}" /> ₫</p></th>
                                                <th>${item.createdDate}</th>
                                                <th>${item.modifiedDate}</th>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
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
                    $('#order').val(0);
                    $('#limit').val(5);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('.xoa').on("click",function () {
        var id=$(this).data("id");
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa hay không",
            type: "warning",
            showCancelButton: true,
            showConfirmButton:true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
            closeOnConfirm: false,
        }).then(function(isConfirm) {
            if (isConfirm) {
                var data={id:id};
                deleteOrder(data);
            }else {
                window.location.href="/quan-tri/don-hang?status=0&page=1&limit=5";
            };
        })
    })

    $('.editOrder_detail').on("click",function () {
        var id=$(this).data("id");
        var amount=$('#amount'+id).val();
        var data={id:id,
            amount:amount};
        $.ajax({
            url: '/api/order_detail',
            type: 'POST',
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href="/quan-tri/don-hang/chi-tiet-don-hang?page=1&limit=5";
            },
            error: function (error) {
                window.location.href="/quan-tri/don-hang/chi-tiet-don-hang?page=1&limit=5";
            }
        })
    })

</script>
</body>
</html>