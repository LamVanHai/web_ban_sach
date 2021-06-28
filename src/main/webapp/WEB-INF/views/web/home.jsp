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
            <div class="col-sm-3 col-xs-4">
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
                        <h4 class="special-name">Top sản phẩm giảm giá</h4>

                        <c:forEach var="item" items="${bookResponse.findByTopDiscount}">
                            <ul class="unliststyle special-content">
                                <li class="special-item">
                                    <div class="product-item">
                                        <div class="product-img">
                                            <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                <c:param name="id" value="${item.id}"/>
                                            </c:url>
                                            <a href="${bookDetail}">
                                                <img src="${pageContext.request.contextPath}/image/${item.image} "
                                                     alt="">
                                            </a>
                                        </div>

                                        <div class="product-info">
                                            <div class="product-price">
                                                <div>
                                                    <h4 class="new-price">Giá</h4>
                                                    <span class="percent">-${item.sale_price}%</span>
                                                </div>
                                                <div>
                                                    <p class="old-price"><fmt:formatNumber type="number"
                                                                                           groupingUsed="true"
                                                                                           value="${item.price}"/>
                                                        ₫</p>
                                                    <p class="prices"><fmt:formatNumber type="number"
                                                                                        groupingUsed="true"
                                                                                        value="${item.price1}"/>
                                                        ₫</p>
                                                </div>
                                            </div>
                                            <input type="hidden" value="${item.name}" id="bookName${item.id}">

                                            <!-- <div class="descrip">Descrip</div> -->
                                        </div>
                                    </div>
                                </li>

                            </ul>
                        </c:forEach>
                    </div>
                    <%--                    <div class="review">--%>
                    <%--                        <h4 class="review-name">Review</h4>--%>
                    <%--                        <ul class="unliststyle review-content">--%>
                    <%--                            <li class="review-item">--%>
                    <%--                                <div class="product-item">--%>
                    <%--                                    <div class="product-img">--%>
                    <%--                                        <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">--%>
                    <%--                                            <c:param name="id" value="${item.id}"/>--%>
                    <%--                                        </c:url>--%>
                    <%--                                        <a href="">--%>
                    <%--                                            <img src="<c:url value='/template/web/public/book/4.jpg'/>" alt="">--%>
                    <%--                                        </a>--%>
                    <%--                                    </div>--%>
                    <%--                                    <div class="product-info">--%>
                    <%--                                        <div class="product-price">--%>
                    <%--                                            <div>--%>
                    <%--                                                <h4 class="new-price">Giá</h4>--%>
                    <%--                                                <span class="percent">-${item.sale_price}%</span>--%>
                    <%--                                            </div>--%>
                    <%--                                            <div>--%>
                    <%--                                                <p class="old-price"><fmt:formatNumber type="number"--%>
                    <%--                                                                                       groupingUsed="true"--%>
                    <%--                                                                                       value="${item.price}"/>--%>
                    <%--                                                    ₫</p>--%>
                    <%--                                                <p class="prices"><fmt:formatNumber type="number"--%>
                    <%--                                                                                    groupingUsed="true"--%>
                    <%--                                                                                    value="${item.price1}"/>--%>
                    <%--                                                    ₫</p>--%>
                    <%--                                            </div>--%>

                    <%--                                        </div>--%>
                    <%--                                        <div class="namebook"><p>${item.name}</p></div>--%>
                    <%--                                        <input type="hidden" value="${item.name}" id="bookName${item.id}">--%>

                    <%--                                        <!-- <div class="descrip">Descrip</div> -->--%>
                    <%--                                    </div>--%>
                    <%--                                    <div class="product-rate">--%>
                    <%--                                        <ul class="unliststyle">--%>
                    <%--                                            <li class="product-rate-item"><i class="fa fa-star"></i></li>--%>
                    <%--                                            <li class="product-rate-item"><i class="fa fa-star"></i></li>--%>
                    <%--                                            <li class="product-rate-item"><i class="fa fa-star"></i></li>--%>
                    <%--                                            <li class="product-rate-item"><i class="fa fa-star-o"></i></li>--%>
                    <%--                                            <li class="product-rate-item"><i class="fa fa-star-o"></i></li>--%>
                    <%--                                        </ul>--%>
                    <%--                                    </div>--%>
                    <%--                                </div>--%>
                    <%--                            </li>--%>

                    <%--                        </ul>--%>
                    <%--                    </div>--%>
                </div>
            </div>
            <div class="col-sm-9 col-xs-8">
                <div class="col-main">
                    <div class="link">
                        <ul class="unliststyle">
                            <%--                            <li class="link-item"><a href="">--%>
                            <%--                                <i class="fa fa-home"></i>--%>
                            <%--                                <span>Trang Chủ</span>--%>
                            <%--                            </a></li>--%>
                            <%--                            <li class="link-item"><a href="">--%>
                            <%--                                <i class="fa fa-long-arrow-right"></i>--%>
                            <%--                                <span>Navigation link</span>--%>
                            <%--                            </a></li>--%>
                            <%--                            <li class="link-item"><a href="">--%>
                            <%--                                <i class="fa fa-long-arrow-right"></i>--%>
                            <%--                                <span>Navigation link</span>--%>
                            <%--                            </a></li>--%>
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
                    <div class="product row row-cols-4">
                        <div class="category-product">
                            <form action="<c:url value="/trang-chu"/> " id="formSubmit" method="get">
                                <a href="<c:url value="/trang-chu" />"><h4 class="product-head">Sách mới</h4></a>
                                <div class="product-list" id="content1">
                                    <c:forEach var="item" items="${bookResponse.newBook}">
                                        <div class="col-sm-4 col-md-3 col-xs-6">
                                            <div class="product-item">
                                                <div class="product-img">
                                                    <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a href="${bookDetail}">
                                                        <img src="${pageContext.request.contextPath}/image/${item.image} "
                                                             alt="">
                                                        <span class="badge-new">New</span>
                                                    </a>
                                                </div>
                                                <div class="product-info">
                                                    <div class="product-price">
                                                        <div>
                                                            <h4 class="new-price">Giá</h4>
                                                            <span class="percent">-${item.sale_price}%</span>
                                                        </div>
                                                        <div>
                                                            <p class="old-price"><fmt:formatNumber type="number"
                                                                                                   groupingUsed="true"
                                                                                                   value="${item.price}"/>
                                                                ₫</p>
                                                            <p class="prices"><fmt:formatNumber type="number"
                                                                                                groupingUsed="true"
                                                                                                value="${item.price1}"/>
                                                                ₫</p>
                                                        </div>

                                                    </div>
                                                    <div class="namebook"><p>${item.name}</p></div>
                                                    <input type="hidden" value="${item.name}" id="bookName${item.id}">
                                                    <!-- <div class="descrip">Descrip</div> -->
                                                </div>
                                                <div class="product-link">
                                                    <ul class="unliststyle">

                                                        <li class="product-link-item gio-hang" data-id="${item.id}"><i
                                                                class="fa fa-cart-plus"></i>
                                                        </li>
                                                        &nbsp&nbsp
                                                        <li class="product-link-item"><i class="fa fa-info-circle">
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
                                <%--								<ul class="pagination" id="pagination"></ul>--%>
                                <%--								<input type="hidden" value="" id="category" name="category"/>--%>
                                <%--								<input type="hidden" value="" id="page" name="page"/>--%>
                                <%--								<input type="hidden" value="" id="limit" name="limit"/>--%>
                            </form>
                        </div>

                        <input type="hidden" id="size" value="${size}"/>
                    </div>
                    <div class="product">
                        <div class="category-product">
                            <form action="<c:url value="/trang-chu"/> " id="formSubmit2" method="get">
                                <a href="<c:url value="/trang-chu" />"><h4 class="product-head">Tất cả sách</h4></a>
                                <div class="product-list" id="content1">
                                    <c:forEach var="item" items="${bookResponse.top8}">
                                        <div class="col-sm-4 col-md-3 col-xs-6">
                                            <div class="product-item">
                                                <div class="product-img">
                                                    <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a href="${bookDetail}">
                                                        <img src="${pageContext.request.contextPath}/image/${item.image} "
                                                             alt="">
                                                            <%--													<span class="badge-new">New</span>--%>
                                                    </a>
                                                </div>
                                                <div class="product-info">
                                                    <div class="product-price">
                                                        <div>
                                                            <h4 class="new-price">Giá</h4>
                                                            <span class="percent">-${item.sale_price}%</span>
                                                        </div>
                                                        <div>
                                                            <p class="old-price"><fmt:formatNumber type="number"
                                                                                                   groupingUsed="true"
                                                                                                   value="${item.price}"/>
                                                                ₫</p>
                                                            <p class="prices"><fmt:formatNumber type="number"
                                                                                                groupingUsed="true"
                                                                                                value="${item.price1}"/>
                                                                ₫</p>
                                                        </div>
                                                    </div>
                                                    <div class="namebook"><p>${item.name}</p></div>
                                                    <input type="hidden" value="${item.name}" id="bookName${item.id}">
                                                    <!-- <div class="descrip">Descrip</div> -->
                                                </div>
                                                <div class="product-link">
                                                    <ul class="unliststyle">

                                                        <li class="product-link-item gio-hang" data-id="${item.id}"><i
                                                                class="fa fa-cart-plus"></i>
                                                        </li>

                                                        &nbsp&nbsp
                                                        <li class="product-link-item"><i class="fa fa-info-circle">
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
                                <%--								<ul class="pagination" id="pagination"></ul>--%>
                                <%--								<input type="hidden" value="" id="category" name="category"/>--%>
                                <%--								<input type="hidden" value="" id="page" name="page"/>--%>
                                <%--								<input type="hidden" value="" id="limit" name="limit"/>--%>
                                <a href="<c:url value='/trang-chu/chi-tiet?search=all&page=1'/> ">Xem tất cả</a>
                            </form>
                        </div>
                        <input type="hidden" id="size1" value="${size}"/>
                    </div>

                    <div class="product">
                        <div class="category-product">
                            <form action="<c:url value="/trang-chu"/> " id="formSubmit4" method="get">
                                <a href="<c:url value="/trang-chu" />"><h4 class="product-head">Sách kỹ năng sống</h4>
                                </a>
                                <div class="product-list" id="content1">
                                    <c:forEach var="item" items="${bookResponse.ky_nang_song}">
                                        <div class="col-sm-4 col-md-3 col-xs-6">
                                            <div class="product-item">
                                                <div class="product-img">
                                                    <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a href="${bookDetail}">
                                                        <img src="${pageContext.request.contextPath}/image/${item.image} "
                                                             alt="">
                                                            <%--													<span class="badge-new">New</span>--%>
                                                    </a>
                                                </div>
                                                <div class="product-info">
                                                    <div class="product-price">
                                                        <div>
                                                            <h4 class="new-price">Giá</h4>
                                                            <span class="percent">-${item.sale_price}%</span>
                                                        </div>
                                                        <div>
                                                            <p class="old-price"><fmt:formatNumber type="number"
                                                                                                   groupingUsed="true"
                                                                                                   value="${item.price}"/>
                                                                ₫</p>
                                                            <p class="prices"><fmt:formatNumber type="number"
                                                                                                groupingUsed="true"
                                                                                                value="${item.price1}"/>
                                                                ₫</p>
                                                        </div>
                                                    </div>
                                                    <div class="namebook"><p>${item.name}</p></div>
                                                    <input type="hidden" value="${item.name}" id="bookName${item.id}">
                                                    <!-- <div class="descrip">Descrip</div> -->
                                                </div>
                                                <div class="product-link">
                                                    <ul class="unliststyle">

                                                        <li class="product-link-item gio-hang" data-id="${item.id}"><i
                                                                class="fa fa-cart-plus"></i>
                                                        </li>

                                                        &nbsp&nbsp
                                                        <li class="product-link-item"><i class="fa fa-info-circle">
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
                                <%--								<ul class="pagination" id="pagination"></ul>--%>
                                <%--								<input type="hidden" value="" id="category" name="category"/>--%>
                                <%--								<input type="hidden" value="" id="page" name="page"/>--%>
                                <%--								<input type="hidden" value="" id="limit" name="limit"/>--%>
                                <a href="<c:url value='/trang-chu/chi-tiet?search=ky-nang-song&page=1'/> ">Xem tất
                                    cả</a>
                            </form>
                        </div>
                        <input type="hidden" id="size4" value="${size}"/>
                    </div>
                    <div class="product">
                        <div class="category-product">
                            <form action="<c:url value="/trang-chu"/> " id="formSubmit3" method="get">
                                <a href="<c:url value="/trang-chu" />"><h4 class="product-head">Sách bán chạy</h4></a>
                                <div class="product-list" id="content1">
                                    <c:forEach var="item" items="${bookResponse.sachBanChay}">
                                        <div class="col-sm-4 col-md-3 col-xs-6">
                                            <div class="product-item">
                                                <div class="product-img">
                                                    <c:url var="bookDetail" value="/trang-chu/chi-tiet-san-pham">
                                                        <c:param name="id" value="${item.id}"/>
                                                    </c:url>
                                                    <a href="${bookDetail}">
                                                        <img class=""
                                                             src="${pageContext.request.contextPath}/image/${item.image} "
                                                             alt="">
                                                            <%--													<span class="badge-new">New</span>--%>
                                                    </a>

                                                </div>
                                                <div class="product-info">
                                                    <div class="product-price">
                                                        <div>
                                                            <h4 class="new-price">Giá</h4>
                                                            <span class="percent">-${item.sale_price}%</span>
                                                        </div>
                                                        <div>
                                                            <p class="old-price"><fmt:formatNumber type="number"
                                                                                                   groupingUsed="true"
                                                                                                   value="${item.price}"/>
                                                                ₫</p>
                                                            <p class="prices"><fmt:formatNumber type="number"
                                                                                                groupingUsed="true"
                                                                                                value="${item.price1}"/>
                                                                ₫</p>
                                                        </div>
                                                    </div>
                                                    <div class="namebook"><p>${item.name}</p></div>
                                                    <input type="hidden" value="${item.name}" id="bookName${item.id}">
                                                    <!-- <div class="descrip">Descrip</div> -->
                                                </div>
                                                <div class="product-link">
                                                    <ul class="unliststyle">

                                                        <li class="product-link-item gio-hang" data-id="${item.id}"><i
                                                                class="fa fa-cart-plus"></i>
                                                        </li>

                                                        &nbsp&nbsp
                                                        <li class="product-link-item"><i class="fa fa-info-circle">
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
                                <%--								<ul class="pagination" id="pagination"></ul>--%>
                                <%--								<input type="hidden" value="" id="category" name="category"/>--%>
                                <%--								<input type="hidden" value="" id="page" name="page"/>--%>
                                <%--								<input type="hidden" value="" id="limit" name="limit"/>--%>
                                <button type="button" onclick="btnXemThem()">Xem thêm</button>
                            </form>
                        </div>
                        <input type="hidden" id="size3" value="${size}"/>
                    </div>

                </div>

            </div>
        </div>
    </div>
</section>

<script>
    <%--var totalPages = ${page.totalPage};--%>
    <%--var currentPage = ${page.page};--%>
    <%--var category="${category}";--%>
    <%--$(function () {--%>
    <%--	window.pagObj = $('#pagination').twbsPagination({--%>
    <%--		totalPages: totalPages,--%>
    <%--		visiblePages: 10,--%>
    <%--		startPage: currentPage,--%>
    <%--		category :category,--%>
    <%--		onPageClick: function (event, page) {--%>
    <%--			if (currentPage != page) {--%>
    <%--				$('#limit').val(3);--%>
    <%--				$('#page').val(page);--%>
    <%--				$('#category').val(category);--%>
    <%--				$('#formSubmit').submit();--%>
    <%--			}--%>
    <%--		}--%>
    <%--	});--%>
    <%--});--%>

    $(document).ready(function () {
        $('.gio-hang').on("click", function () {
            var id = $(this).data("id");
            var data = {
                id: id,
                quanty: 1
            };
            $.ajax({
                url: '${themgiohangAPI}',
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(data),
                dataType: 'json',
                success: function (res) {
                    alert(res.status);
                    $('#itemInCart').text(res.amount + " Sản phẩm");

                },
                error: function (res) {
                    alert("Lỗi hệ thống!");
                },
            });
        })
    })


    <%--function btnThemGioHang() {--%>
    <%--	var id=$(this).data("id");--%>
    <%--	alert(id);--%>
    <%--	var data={--%>
    <%--		id:id--%>
    <%--	};--%>
    <%--	$.ajax({--%>
    <%--		url: '${themgiohangAPI}',--%>
    <%--		type:'POST',--%>
    <%--		contentType: 'application/json',--%>
    <%--		data: JSON.stringify(data),--%>
    <%--		success:function (res) {--%>
    <%--		},--%>
    <%--		error:function (res) {--%>
    <%--		},--%>
    <%--	});--%>
    <%--}--%>


</script>
</body>

</html>