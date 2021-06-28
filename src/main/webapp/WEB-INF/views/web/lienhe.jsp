<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 04/19/2021
  Time: 4:53 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container">
    <div>&nbsp</div>
    <h3>Bản đồ</h3>
    <div style="">
        <iframe src="https://www.google.com/maps/embed?pb=!1m16!1m12!1m3!1d2684.3962155307695!2d105.78807329827335!3d21.03613069222058!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!2m1!1zQ-G7rWEgaMOgbmcgQm9va1N0b3Jl!5e0!3m2!1svi!2s!4v1618826594211!5m2!1svi!2s"
                width="100%" height="500" style="border:0;" allowfullscreen="" loading="lazy"></iframe>
    </div>
    <div>&nbsp</div>
    <div class="row">
        <div class="col-sm-6">
            <div class="main">
                <div class="row padding">
                    <form method="" id="formSingin">
                        <div><h4 class="userdetails">Viết nhận xét</h4></div>
                        <div>&nbsp</div>
                        <div class="form-group">
                            <label for="name">Tên của bạn:</label>
                            <input type="texr" class="form-control" id="name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="text" class="form-control" id="email" name="email">
                        </div>
                        <div class="form-group">
                            <label for="comment">Nhận xét:</label>
                            <textarea cols="30" type="text" rows="6" class="form-control" id="comment"
                                      name="comment"></textarea>

                        </div>
                        <div class="form-group" style="float: right;">
                            <button type="reset" class="btn btn-reset">Hủy</button>
                            &nbsp&nbsp&nbsp
                            <button type="button" class="btn btn-submit" id="btnAddOrUpdateNew">Gửi nhận xét</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-sm-1"></div>
        <div class="col-sm-5">
            <div class="main">
                <div class="row padding">
                    <div><h4 class="userdetails">Chúng tôi ở đây</h4></div>
                    <div>&nbsp</div>

                    <div><h4>Công ty TNHH Đầu Tư Và Phát Triển Văn Hoá Việt</h4></div>
                    <div>&nbsp</div>
                    <div class="form-group">
                        <i class="glyphicon glyphicon-map-marker"></i>&nbsp&nbsp
                        <label for="name">524, Đường Láng, Phường Láng Hạ, Quận Đống Đa, Thành Phố Hà Nội</label>

                    </div>
                    <div>&nbsp</div>
                    <div class="form-group">
                        <i class="glyphicon glyphicon-envelope"></i></i>&nbsp&nbsp
                        <label for="name">bookstore@gmail.com</label>

                    </div>
                    <div>&nbsp</div>
                    <div class="form-group">
                        <i class="fa fa-phone"></i></i>&nbsp&nbsp
                        <label for="name">0357549623</label>

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
