<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Electronic Store</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/template/web/css/bootstrap.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/ownstyle.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/effect.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/responsive.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/main.css'/>">
    <%--	<link rel="stylesheet" href="<c:url value='/template/web/css/theme4.css'/>">--%>

    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css' />" class="ace-main-stylesheet" id="main-ace-style" />




    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

    <!-- sweetalert -->
    <script src="<c:url value='/template/admin/sweetalert/sweetalert2.min.js' />"></script>
    <link rel="stylesheet" href="<c:url value='/template/admin/sweetalert/sweetalert2.min.css' />" />
    <link rel="stylesheet" href="<c:url value='/template/admin/css/custumhai.css' />" />
</head>
<body>
<%@include file="/common/web/header.jsp"%>
<div class="container">
    <div>&nbsp</div>
    <div>&nbsp</div>
    <div>&nbsp</div>
    <div class="row">
        <div class="col-sm-2">
            <%@include file="/common/web/menu.jsp"%>
        </div>
            <dec:body/>
    </div>
    <div>&nbsp</div>
    <div>&nbsp</div>
    <div>&nbsp</div>
</div>


<%@include file="/common/web/footer.jsp"%>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<c:url value='/template/web/js/jquery-1.11.3.min.js'/>"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/template/web/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/template/web/js/ownscript.js'/>"></script>
<script src="<c:url value='/template/web/js/slide-show.js'/>"></script>

<script src="<c:url value='/template/web/js/lib.vendor.bundle.js'/>"></script>


<!-- start plugin js file  -->

<!-- Start core js and page js -->
<script src="<c:url value='/template/web/js/core.js'/>"></script>



<script src="<c:url value='/template/admin/paging/jquery.twbsPagination.js'/>"></script>
<script src="<c:url value='/template/admin/paging/jquery.twbsPagination.min.js'/>"></script>
</body>
</html>