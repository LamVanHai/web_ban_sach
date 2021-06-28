<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 03/05/2021
  Time: 1:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="editCart" value="/api/chinh-sua-gio-hang"/>
<c:url var="deleteCart" value="/api/xoa-gio-hang"/>
<c:url var="userApI" value="/api/user"/>
<html>
<head>

    <title>Title</title>
</head>
<body>
<div id="giohang">
<div class="section-gray py-4">
    <div class="container-fluid">
        <div class="row clearfix">
            <div class="col-lg-12">
                <div class="card bg-none">
                    <div class="card-header">
                        <h3  class="card-title"><strong>Giỏ hàng của bạn</strong></h3>
                    </div>
                    <div class="card-body pt-0">
                        <div class="table-responsive table_e2">
                            <table class="table table-hover table-vcenter table_custom spacing5 text-nowrap mb-0">
                                <thead>
                                <tr>
                                    <th><strong>Tên sách</strong></th>
                                    <th><strong>Ảnh</strong></th>
                                    <th><strong>Số lượng</strong></th>
                                    <th><strong>Giá</strong></th>
                                    <th><strong>Chỉnh sửa</strong></th>
                                    <th><strong>Xóa</strong></th>
                                    <th><strong>Tổng giá</strong></th>

                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="item" items="${Cart}">
                                    <tr>
                                        <td>
                                            <img src="" class="rounded" alt="">
                                            <span class="c_name">${item.value.book.name}</span>
                                        </td>
                                        <td>
                                            <img src="${pageContext.request.contextPath}/image/${item.value.book.image} "/>

                                        </td>
                                        <td>
                                            <input type="number" id="soLuongEdit${item.value.book.id}" STYLE="width: 50px" value="${item.value.quanty}"> </input>
                                        </td>
                                        <td>
                                            <span><i ><fmt:formatNumber type="number" groupingUsed="true" value="${item.value.book.price}" /> ₫</i></span>
                                        </td>
                                        <td>
                                            <button type="button" data-id="${item.value.book.id}" class="btn btn-primary btn-sm edit-gio-hang" title="Edit">
                                                <i class="fa fa-edit"></i>
                                            </button>

                                        </td>
                                        <td>
                                           <button type="button"  class="btn btn-danger btn-sm delete-gio-hang" data-id="${item.value.book.id}" title="Delete"><i class="fa fa-trash-o"></i></button>
                                        </td>
                                        <td>
                                            <span><i ><fmt:formatNumber type="number" groupingUsed="true" value="${item.value.total_price}" /> ₫</i></span>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                            <div id="tongtien1">
                                <h3>
                                    <span>Tổng giá: </span>
                                    <span><i ><fmt:formatNumber type="number" groupingUsed="true" value="${CartTotalPrice}" /> ₫</i></span>
                                </h3>
                            </div>
<%--                            <ul class="pagination" id="pagination"></ul>--%>
                            <div>&nbsp</div>
                            <div>
                                <div id="btnthanhtoan1">
                                    <a href="<c:url value="/trang-chu"/> "><button  class="btn btngiohang" type="button">Tiếp tục mua hàng</button></a>
                                </div>
                                <div id="btnthanhtoan">
                                   <a href="<c:url value="/gio-hang/xac-nhan-thanh-toan"/> "><button class="btn btngiohang" type="button">Thanh toán</button></a>
                                </div>
                            </div>
                            <div>&nbsp</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script>
    <%--var totalPages = ${page.totalPage};--%>
    <%--var currentPage = ${page.page};--%>
    // $(function () {
    //     window.pagObj = $('#pagination').twbsPagination({
    //         totalPages: 100,
    //         visiblePages: 10,
    //         // startPage: currentPage,
    //         onPageClick: function (event, page) {
                // if (currentPage != page) {
                //     $('#limit').val(5);
                //     $('#page').val(page);
                //     $('#formSubmit').submit();
                // }
    //         }
    //     });
    // });
    $('.edit-gio-hang').on("click", function () {
        var id=$(this).data("id");
        var quanty=$('#soLuongEdit'+id).val();
        var data={id:id,
        quanty: quanty};
        $.ajax({
            url:'${editCart}',
            type:"POST",
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/gio-hang";
            },
            error: function (error) {
                window.location.href = "/gio-hang";
            }

        });
    })
    $('.delete-gio-hang').on("click", function () {
        var id=$(this).data("id");
        var data={id:id};
        $.ajax({
            url:'${deleteCart}',
            type:"POST",
            contentType:'application/json',
            data:JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/gio-hang";
            },
            error: function (error) {
                window.location.href = "/gio-hang";
            }

        });
    })
</script>
</body>
</html>
