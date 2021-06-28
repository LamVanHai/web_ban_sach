<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>
</head>
<body>
<section>
	<div class="row">
		<div class="container">
			<div class="col-xs-12">
				<div class="link">
<%--					<ul class="unliststyle">--%>
<%--						<li class="link-item"><a href="">--%>
<%--							<i class="fa fa-home"></i>--%>
<%--							<span>Trang Chủ</span>--%>
<%--						</a></li>--%>
<%--						<li class="link-item"><a href="">--%>
<%--							<i class="fa fa-long-arrow-right"></i>--%>
<%--							<span>Navigation link</span>--%>
<%--						</a></li>--%>
<%--						<li class="link-item"><a href="">--%>
<%--							<i class="fa fa-long-arrow-right"></i>--%>
<%--							<span>Product name</span>--%>
<%--						</a></li>--%>
<%--					</ul>--%>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="container">
			<div class="col-sm-6 col-sm-offset-3 col-xs-12">
				<div class="wrapper-authen">
					<div class="authen-head">
						<span>Đăng nhập</span>
					</div>
					<c:if test="${param.incorrectAccount != null}">
						<div class="alert alert-danger">
							Username or password không chính xác!
						</div>
					</c:if>
					<c:if test="${param.accessDenied != null}">
						<div class="alert alert-danger">
							Bạn không có quyền truy cập!
						</div>
					</c:if>
					<form action="j_spring_security_check" id="formLogin" method="post">
						<div class="form-group">
							<label for="username">E-mail:</label>
							<input type="text"  class="form-control" id="userName" name="j_username" placeholder="e-mail">
						</div>
						<div class="form-group">
							<label for="password">Mật khẩu:</label>
							<input type="password" class="form-control" id="password" name="j_password" placeholder="Mật khẩu">
						</div>
						<div class="form-group">
							<input type="checkbox" id="remember">
							<label for="remember">Remember:</label>
						</div>
						<div class="form-group" style="float: right;">
							<button class="btn btn-reset">Reset</button>
							<button type="submit" class="btn btn-submit">Đăng nhập</button>
						</div>
						<div class="form-group">
							<button class="login-social facebook"><i class="fa fa-facebook-square"></i></button>
							<button class="login-social googleplus"><i class="fa fa-google-plus-square"></i></button>
						</div>
						<hr>
						<ul class="unliststyle form-group">
							<li><a href="<c:url value="/quen-mat-khau"/> ">Quên mật khẩu</a></li>
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>