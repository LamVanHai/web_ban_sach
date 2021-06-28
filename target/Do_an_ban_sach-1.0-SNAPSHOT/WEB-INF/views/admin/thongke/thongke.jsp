<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="userAPI" value="/api/users"/>
<c:url var="newURL" value="/quan-tri/nguoi-dung/danh-sach"/>
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
    <form action="<c:url value='/quan-tri/thong-ke'/>" id="formSubmit" method="get">
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
                                        <c:url var="createNewURL" value="/quan-tri/nguoi-dung/chinh-sua"/>

                                    </div>
                                </div>

                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>ID</th>
                                                <th>Tên sách</th>
                                                <th>Giá nhập</th>
                                                <th>Giá bán</th>
                                                <th>Đơn vị tính</th>
                                                <th>SL nhập vào</th>
                                                <th>SL bán ra</th>
                                                <th>SL tồn</th>
                                                <th>TT nhập vào</th>
                                                <th>TT bán ra</th>
                                                <th>Doanh thu</th>

                                            </tr>
                                            </thead>
                                            <tbody>
                                            <c:forEach var="item" items="${model}">
                                                <tr>
                                                    <th>${item.id}</th>
                                                    <th>${item.name}</th>
                                                    <th><p class="prices"><fmt:formatNumber type="number"
                                                                                            groupingUsed="true"
                                                                                            value="${item.input_price}"/>
                                                        ₫</p></th>
                                                    <th><p class="prices"><fmt:formatNumber type="number"
                                                                                            groupingUsed="true"
                                                                                            value="${item.price}"/>
                                                        ₫</p></th>
                                                    <th>${item.init}</th>
                                                    <th>${item.input_amount}</th>
                                                    <th>${item.output_amount}</th>
                                                    <th>${item.amount}</th>
                                                    <th><p class="prices"><fmt:formatNumber type="number"
                                                                                            groupingUsed="true"
                                                                                            value="${item.total_input_price}"/>
                                                        ₫</p></th>
                                                    <th><p class="prices"><fmt:formatNumber type="number"
                                                                                            groupingUsed="true"
                                                                                            value="${item.total_output_price}"/>
                                                        ₫</p></th>
                                                    <th><p class="prices"><fmt:formatNumber type="number"
                                                                                            groupingUsed="true"
                                                                                            value="${item.revenue}"/>
                                                        ₫</p></th>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <ul class="pagination" id="pagination"></ul>
                                        <input type="hidden" value="" id="page" name="page"/>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="table-responsive">
                                        <table class="table table-bordered">
                                            <thead>
                                            <tr>
                                                <th>Tổng số sản phẩm nhập vào</th>
                                                <th>Tổng số sản phẩm bán ra</th>
                                                <th>Tổng số tiền nhập vào</th>
                                                <th>Tổng số tiền bán ra</th>
                                                <th>Tổng doanh thu</th>

                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th>${item.total_input_amount}</th>
                                                <th>${item.total_output_amount}</th>
                                                <th><p class="prices"><fmt:formatNumber type="number"
                                                                                        groupingUsed="true"
                                                                                        value="${item.total_input_price}"/>
                                                    ₫</p></th>
                                                <th><p class="prices"><fmt:formatNumber type="number"
                                                                                        groupingUsed="true"
                                                                                        value="${item.total_output_price}"/>
                                                    ₫</p></th>
                                                <th><p class="prices"><fmt:formatNumber type="number"
                                                                                        groupingUsed="true"
                                                                                        value="${item.revenue}"/>
                                                    ₫</p></th>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
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
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

</script>
</body>
</html>