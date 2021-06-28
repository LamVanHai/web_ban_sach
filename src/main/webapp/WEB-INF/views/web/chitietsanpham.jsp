<%@include file="/common/taglib.jsp" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 02/28/2021
  Time: 9:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:url value="/gio-hang" var="giohang"/>
<c:url var="addCart" value="/api/them-gio-hang"/>
<html>
<head>
    <title>Chi tiết sản phẩm</title>
    <meta property="fb:app_id" content="184326453541960"/>
</head>
<body>
<div id="fb-root"></div>
<%--<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v10.0&appId=757244298315824&autoLogAppEvents=1" nonce="I8U6EUJn"></script>--%>
<div id="fb-root"></div>
<script async defer crossorigin="anonymous" src="https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v10.0&appId=184326453541960&autoLogAppEvents=1" nonce="CVT14fky">
</script>
<section>
    <div class="row">
        <div class="container">
            <div class="col-xs-12">
                <div class="link">
                    <ul class="unliststyle">
                        <li class="link-item"><a href="">
                            <i class="fa fa-home"></i>
                            <span>Trang Chủ</span>
                        </a></li>
<%--                        <li class="link-item"><a href="">--%>
<%--                            <i class="fa fa-long-arrow-right"></i>--%>
<%--                            <span>Navigation link</span>--%>
<%--                        </a></li>--%>
                        <li class="link-item"><a href="">
                            <i class="fa fa-long-arrow-right"></i>
                            <span>${model1.name}</span>
                        </a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <div class="col-xs-4">
                <div class="product-image">
                    <div class="img-stage">
                        <img  class="img-item" src="${pageContext.request.contextPath}/image/${model1.image} " alt="">
                        <!-- <img class="img-item" src="public/book/2.jpg" alt=""> -->
                    </div>
                    <!-- <span class="rotate-img"><i class="fa fa-refresh"></i></span> -->
                </div>
            </div>
            <div class="col-xs-8">
                <div class="product-info-quick">
                    <h4 class="product-name">${model1.name}</h4>
                    <div class="product-rate">
                        <ul class="unliststyle">
                            <li class="product-rate-item"><i class="fa fa-star"></i></li>
                            <li class="product-rate-item"><i class="fa fa-star"></i></li>
                            <li class="product-rate-item"><i class="fa fa-star"></i></li>
                            <li class="product-rate-item"><i class="fa fa-star-o"></i></li>
                            <li class="product-rate-item"><i class="fa fa-star-o"></i></li>
                            <span>(0) review</span>
                        </ul>
                    </div>
                    <div class="product-type">
                        <div class="col-xs-6 wrapper-type">

                            <div class="product-price">
                                <div>
                                    <h4 class="new-price">Giá</h4>
                                    <span class="percent">-${model1.sale_price}%</span>
                                </div>
                                <div>
                                    <p class="old-price"><fmt:formatNumber type="number" groupingUsed="true" value="${model1.price}" /> ₫</p>
                                    <p class="prices"><fmt:formatNumber type="number" groupingUsed="true" value="${model1.price1}" /> ₫</p>
                                </div>
                                &nbsp&nbsp
                                <div>
                                    <p style="font-size: 13px">Số lượng trong kho: ${model1.amount}</p>
                                </div>
                            </div>
                            <form action="">
                                <div class="form-group">
                                    <label for="quantity1">Số lượng:</label>
                                    <input type="text" value="1" class="form-control" id="quantity1"/>
                                </div>
                                <div class="form-group">
                                    <button value="${model1.id}" type="button" class="btn btn-addcart them-gio-hang">
                                        Thêm giỏ hàng
                                    </button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
            <div class="col-xs-12">
                <h4>Thông tin</h4>
                <div class="product-detail tab" data-tabcontrol="tabcontrol1" id="tabcontrol1">
                    <ul class="tab-control">
                        <li class="tab-page active" data-tab="tab-1">Mô tả ngắn</li>
                        <li class="tab-page" data-tab="tab-4">Bình luận</li>
                    </ul>
                    <div class="tab-content">
                        <div id="tab-1" class="page-content active">
                            ${model1.description}
                        </div>
                        <div id="tab-4" class="page-content">
                            <div class="fb-comments" data-href="http://localhost:8081/trang-chu/chi-tiet-san-pham?id=${model1.id}" data-width="100%" data-numposts="6"></div>                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="container">
            <div class="col-xs-12">
                <div class="recommend product">
                    <div class="category-product">
                        <h4 class="product-head">Sản phẩm vừa xem</h4>
                        <div class="product-list">
                            <c:forEach var="item" items="${viewed_detail}">
                                <div class="col-sm-2 col-md-2 col-xs-4">
                                    <div class="product-item">
                                        <div class="product-img">
                                            <a href="">
                                                <img src="${pageContext.request.contextPath}/image/${item.image} "
                                                     alt="">
                                                    <%--													<span class="badge-new">New</span>--%>
                                            </a>
                                        </div>
                                        <div class="product-info">
                                            <div class="product-price">
                                                <div>
                                                    <h4 class="new-price">Price</h4>
                                                    <span class="percent">-${item.sale_price}%</span>
                                                </div>
                                                <div>
                                                    <p class="old-price"><fmt:formatNumber type="number" groupingUsed="true" value="${item.price}" /> ₫</p>
                                                    <p class="prices"><fmt:formatNumber type="number" groupingUsed="true" value="${item.price1}" /> ₫</p>
                                                </div>
                                            </div>
                                            <div class="namebook"><p>${item.name}</p></div>
                                            <input type="hidden" value="${item.name}" id="bookName${item.id}">
                                            <!-- <div class="descrip">Descrip</div> -->
                                        </div>
                                        <div class="product-link">
                                            <ul class="unliststyle">
                                                <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                    <c:param name="id" value="${item.id}"/>
                                                </c:url>
                                                <button class="gio-hang" type="button" data-id="${item.id}">
                                                    <li class="product-link-item"><i
                                                            class="fa fa-cart-plus"></i></li>
                                                </button>

                                                &nbsp&nbsp
                                                <li class="product-link-item"><i class="fa fa-info-circle">
                                                    <a href="#"></a></i>
                                                </li>
                                                &nbsp&nbsp
                                                <a href="${bookDetail}">
                                                    <li class="product-link-item">
                                                        <i class="fa fa-eye"></i>
                                                    </li>
                                                </a>

                                            </ul>
                                        </div>
                                    </div>
                                    &nbsp&nbsp
                                </div>
                            </c:forEach>
                            <a href="<c:url value='/trang-chu/chi-tiet?viewed_product=all&page=1'/> " class="view-more">
                                <span>Xem tất cả</span>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<script>
    $('.them-gio-hang').on("click", function () {
        var id = $(this).val();
        var quanty = $('#quantity1').val();
        var data = {
            id: id,
            quanty: quanty
        };
        $.ajax({
            url: '${addCart}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (data) {
                alert(data.status);
                window.location.href = '${giohang}';
            },
            error: function (d) {
                window.location.href = '${giohang}';
            }
        });
    })
</script>
</body>
</html>
