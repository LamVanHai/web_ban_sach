<%@ page import="com.bookshop.util.SecurityUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<c:url value="/trang-chu/chi-tiet" var="search"/>
<%@page import="com.bookshop.util.SecurityUtils" %>
<header>
    <div class="row">
        <div class="container">
            <div class="col-xs-12">
                <div class="head-top">
                    <ul class="head-social unliststyle">
                        <li><a href="https://www.facebook.com/lam.hai.79025/"><i class="fa fa-facebook"></i></a></li>
                        <li><a href=""><i class="fa fa-google-plus"></i></a></li>
                        <li><a href=""><i class="fa fa-twitter"></i></a></li>
                        <li><a href=""><i class="fa fa-youtube"></i></a></li>
                    </ul>
                    <ul class="head-account unliststyle">
                        <security:authorize access="isAnonymous()">
                            <li><a href="<c:url value='/dang-nhap'/> ">
                                <i class="fa fa-sign-in"></i>
                                <span>Đăng Nhập</span>
                            </a></li>
                            <li><a href="<c:url value='/dang-ky'/>">
                                <i class="fa fa-user-plus"></i>
                                <span>Đăng Ký</span>
                            </a></li>

                        </security:authorize>
                        <security:authorize access="isAuthenticated()">
                            <ul class="nav ace-nav">
                                <li class="transparent dropdown-modal">
                                <li class="light-blue dropdown-modal">
                                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                                        <img class="nav-user-photo"
                                             src="<c:url value='/template/web/public/book/user.jpg'/>"/>
                                        <span class="user-info">
												<%=SecurityUtils.getPrincipal().getFullName()%>
												</span>

                                        <i class="ace-icon fa fa-caret-down"></i>
                                    </a>

                                    <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                                        <li>
                                            <a href="<c:url value="/quan-tri/trang-chu"/> ">
                                                <i class="ace-icon fa fa-cog"></i>
                                                Quản trị
                                            </a>
                                        </li>

                                        <li>
                                            <a href="<c:url value='/nguoi-dung/thong-tin-chi-tiet'/> ">
                                                <i class="ace-icon fa fa-user"></i>
                                                Thông tin cá nhân
                                            </a>
                                        </li>

                                        <li class="divider"></li>

                                        <li>
                                            <a href="<c:url value="/j_spring_security_logout"/> ">
                                                <i class="ace-icon fa fa-power-off"></i>
                                                Đăng xuất
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                </li>
                            </ul>
                        </security:authorize>

                    </ul>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="head-logo">
                    <a href="">bookShop</a>
                </div>
            </div>
            <div class="col-xs-6">
                <div class="head-search">
                    <input id="headSearch"  type="text" placeholder="Nhập tên sách... ">
                    <button type="button"  onclick="btnTimKiem()">Tìm kiếm</button>
                </div>
            </div>
            <div class="col-xs-3">
                <div class="head-hotline">
                    <i class="fa fa-phone"></i>
                    <span>0357 549 623</span>
                </div>
            </div>
        </div>
    </div>
</header>
<nav>
    <div class="row">
        <div class="container">
            <div class="col-xs-3">
                <h4 class="category-head">
                    <i class="fa fa-bars"></i>
                    <span>Chủ đề</span>
                </h4>
            </div>
            <div class="col-xs-7">
                <ul class="navigation unliststyle">
                    <li class="nav-item"><a href="<c:url value='/trang-chu'/> ">
                        <i class="fa fa-home"></i>
                        <span>TRANG CHỦ</span>
                    </a></li>

                    <li class="nav-item"><a href="<c:url value="/san-pham-giam-gia?page=1"/> ">KHUYẾN MÃI</a></li>
                    <li class="nav-item"><a href="<c:url value="/gioi-thieu"/> ">GIỚI THIỆU</a></li>
                    <li class="nav-item"><a href="<c:url value="/trang-chu/lien-he"/> ">LIÊN HỆ</a></li>
                </ul>
            </div>
            <div class="col-xs-2">
                <div class="cart">
                    <h4 class="cart-head">
                        <a href="<c:url value='/gio-hang'/>">
                            <i class="fa fa-shopping-cart"></i>
                            <span id="itemInCart">${cartItem} sản phẩm</span>
                            <i class="fa fa-angle-down"></i>
                        </a>
                    </h4>
                    <ul class="cart-product1" id="cart-product1">
                        <c:forEach var="item" items="${Cart}">
                            <li class="cart-item unliststyle">
                                <div class="row">
                                    <div class="col-xs-4">
                                        <img style="width: 40px; height: 50px"
                                             src="${pageContext.request.contextPath}/image/${item.value.book.image} "/>
                                    </div>
                                    <div class="col-sm-8">
                                        <p>${item.value.book.name}</p>
                                        <p>${item.value.quanty} x ${item.value.book.sale_price} đ</p>
                                    </div>
                                </div>

                            </li>
                            <hr style="width: 100%;height: 1px; color: #1C1D1E">
                        </c:forEach>
                        <li>
                            Tổng giá: ${CartTotalPrice} đ
                        </li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>
<script>

    function btnTimKiem() {
        var data = {value: $('#headSearch').val()}
        $.ajax({
            url: '/tim-kiem',
            type: "POST",
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${search}?search=" + result.value+"&page=1";
                $('#headSearch').text(result.value)
            },
            error: function (error) {
                window.location.href = "${search}?search=" + error.value;
            },
        });
    }

</script>