<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="themgiohangAPI" value="/api/them-gio-hang"/>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">
    <title>Trang chủ</title>

</head>

<body>
<section>
    <div class="row">
        <div class="container">
            <div class="col-sm-3 col-xs-6">
                <div class="col-left">
                    <div class="category">
                        <ul class="unliststyle">
                            <c:forEach var="item" items="${listCategory}">
                                <li class="category-item"><a
                                        href="<c:url value='/trang-chu/chi-tiet?category=${item.keyWord}&page=1&limit=16'/>">${item.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="special">
                        <h4 class="special-name">Nhà xuất bản</h4>
                        <ul class="unliststyle special-content">
                            <c:forEach items="${publisher}" var="item">
                                <li class="special-item">
                                    <a href="<c:url value='/trang-chu/chi-tiet?publisher=${item.keyWord}&page=1'/>">${item.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="special">
                        <h4 class="special-name">Tác giả</h4>
                        <ul class="unliststyle special-content">
                            <c:forEach items="${writer}" var="item">
                                <li class="special-item">
                                    <a href="<c:url value='/trang-chu/chi-tiet?writer=${item.keyWord}&page=1'/>">${item.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>

                </div>
            </div>
            <div class="col-sm-9 col-xs-6">
                <div class="col-main">
                    <div class="link">
                        <ul class="unliststyle">
                            <li class="link-item"><a href="">
                                <i class="fa fa-home"></i>
                                <span>Trang Chủ</span>
                            </a></li>
                            <li class="link-item"><a href="">
                                <i class="fa fa-long-arrow-right"></i>
                                <span>Navigation link</span>
                            </a></li>
                            <li class="link-item"><a href="">
                                <i class="fa fa-long-arrow-right"></i>
                                <span>Navigation link</span>
                            </a></li>
                        </ul>
                    </div>
                    <div class="slide-show">
                        <div class="slide-stage">
                            <div class="slide-item active"><img
                                    src="<c:url value='/template/web/public/slideshow/s1.png'/>" alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s2.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s3.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s4.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s5.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s6.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s7.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s8.jpg'/>"
                                                         alt=""></div>
                            <div class="slide-item"><img src="<c:url value='/template/web/public/slideshow/s9.jpg'/>"
                                                         alt=""></div>


                        </div>
                        <div class="slide-control">
                            <ul class="slide-page unliststyle">
                                <!-- <li class="page-poiter fa fa-circle"></li>
                                <li class="page-poiter fa fa-circle-o"></li> -->
                            </ul>
                            <ul class="slide-arrow unliststyle">
                                <li class="arrow-left"><i class="fa fa-chevron-left"></i></li>
                                <li class="arrow-right"><i class="fa fa-chevron-right"></i></li>
                            </ul>
                        </div>
                    </div>
                    <div class="brand">
                        <ul class="unliststyle brand-stage">
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                            <li class="brand-item"><img src="<c:url value='/template/web/public/brand/brand.png'/>"
                                                        alt=""></li>
                        </ul>
                        <div class="arrow-left"><i class="fa fa-angle-left"></i></div>
                        <div class="arrow-right"><i class="fa fa-angle-right"></i></div>
                    </div>
                    <div class="product">
                        <div class="category-product">
                            <form action="<c:url value="/trang-chu/chi-tiet"/> " id="formSubmit" method="get">
                                <a href="<c:url value="/trang-chu" />"><h4 class="product-head">Sách mới</h4></a>
                                <div class="product-list" id="content1">
                                    <c:forEach var="item" items="${model.categories}">
                                        <div class="col-sm-4 col-md-3">
                                            <div class="product-item">
                                                <div class="product-img">
                                                    <a href="">
                                                        <img src="${pageContext.request.contextPath}/image/${item.image} " alt="">
                                                            <%--                                                        <span class="badge-new">New</span>--%>
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
                                                    <input type="hidden" value="${item.name}" id="bookName${item.id}" >
                                                    <!-- <div class="descrip">Descrip</div> -->
                                                </div>
                                                <div class="product-link">
                                                    <ul class="unliststyle">
                                                        <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                            <c:param name="id" value="${item.id}"/>
                                                        </c:url>
                                                        <li class="product-link-item gio-hang" data-id="${item.id}" ><i
                                                                class="fa fa-cart-plus"></i>
                                                        </li>

                                                        &nbsp&nbsp
                                                        <li  class="product-link-item"><i class="fa fa-info-circle">
                                                            <a href="#"></a></i>
                                                        </li>
                                                        &nbsp&nbsp
                                                        <a href="${bookDetail}" style="color: #1C1D1E">
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

                                </div>
                                <ul class="pagination" id="pagination"></ul>
                                <input type="hidden" value="" id="page" name="page"/>
                                <%--                                    <input type="hidden" value="" id="limit" name="limit"/>--%>
                            </form>
                        </div>
                        <input type="hidden" id="size" value="${size}"/>
                    </div>


                </div>

            </div>
        </div>
    </div>
</section>

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
                    // $('#limit').val(6);
                    $('#page').val(page);
                    $('#formSubmit').submit();
                }
            }
        });
    });

    $('.gio-hang').on("click", function () {
        var id = $(this).data("id");
        var name = $("#bookName" + id).val();
        var data = {
            id: id,
            quanty: 1
        };
        $.ajax({
            url: '${themgiohangAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),

            success: function (res) {
                alert(res);
            },
            error: function (res) {
                alert("Thêm thất bại!!")
            },
        });
    })

</script>
</body>

</html>