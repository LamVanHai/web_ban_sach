<%@include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<c:url var="categoryAPI" value="/api/category"/>
<c:url var="newURL" value="/quan-tri/chu-de/danh-sach"/>
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
    <h4 style="font-weight: bold">Quản lý chủ đề</h4>
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
    <form action="<c:url value='/quan-tri/chu-de/danh-sach'/>" id="formSubmit" method="get">

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
                                        <c:url var="createNewURL" value="/quan-tri/chu-de/chinh-sua"/>
                                        <a flag="info"
                                           class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                           data-toggle="tooltip"
                                           title='Thêm bài viết' href='${createNewURL}'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                        </a>
                                        <button id="btnDelete" type="button" onclick="warningBeforeDelete()"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip" title='Xóa bài viết'>
																<span>
																	<i class="fa fa-trash-o bigger-110 pink"></i>
																</span>
                                        </button>
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
                                            <th>Tên chủ đề</th>
                                            <th>Mô tả</th>
                                            <th>Tên định danh</th>
                                            <td>Ngày tạo</td>
                                            <td>Người tạo</td>
                                            <td>Ngày Chỉnh sửa</td>
                                            <td>NGười chỉnh sửa</td>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${model.data}">
                                            <tr>
                                                <td><input type="checkbox" id="checkbox_${item.id}" value="${item.id}">
                                                </td>
                                                <th>${item.name}</th>
                                                <th>${item.description}</th>
                                                <th>${item.keyWord}</th>
                                                <th>${item.createdDate}</th>
                                                <th>${item.createdBy}</th>
                                                <th>${item.modifiedDate}</th>
                                                <th>${item.modifiedBy}</th>
                                                <td>
                                                    <c:url var="updateNewURL" value="/quan-tri/chu-de/chinh-sua">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
                                                       title="Cập nhật bài viết" href='${updateNewURL}'><i
                                                            class="fa fa-pencil-square-o" aria-hidden="true"></i>
                                                    </a>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination" id="pagination"></ul>
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
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#page').val(page);
                    $('#search').val(search),
                    $('#formSubmit').submit();
                }
            }
        });
    });

    function btnTimKiem() {
        var data = {value: $('#headSearch').val()}
        $.ajax({
            url: '/tim-kiem',
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${newURL}?search=" + result.value + "&page=1";
                $('#headSearch').text(result.value)
            },
            error: function (error) {
                window.location.href = "${newURL}?search=" + error.value + "&page=1";
            },
        });
    }


    function warningBeforeDelete() {
        swal({
            title: "Xác nhận xóa",
            text: "Bạn có chắc chắn muốn xóa hay không",
            type: "warning",
            showCancelButton: true,
            confirmButtonClass: "btn-success",
            cancelButtonClass: "btn-danger",
            confirmButtonText: "Xác nhận",
            cancelButtonText: "Hủy bỏ",
        }).then(function (isConfirm) {
            if (isConfirm) {
                var ids = $('tbody input[type=checkbox]:checked').map(function () {
                    return $(this).val();
                }).get();
                deleteNew(ids);
            }
        });
    }

    function deleteNew(data) {
        $.ajax({
            url: '${categoryAPI}',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${newURL}?page=1&search=all&message=delete_success";
            },
            error: function (error) {
                window.location.href = "${newURL}?page=1&search=all&message=error_system";
            }
        });
    }
</script>
</body>
</html>