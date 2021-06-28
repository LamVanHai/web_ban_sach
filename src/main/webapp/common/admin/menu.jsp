<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<div id="sidebar" class="sidebar responsive   ace-save-state">
    <script type="text/javascript">
        try{ace.settings.loadState('sidebar')}catch(e){}
    </script>
    <div class="sidebar-shortcuts">
        <div class="sidebar-shortcuts-large">

        </div>
        <div class="sidebar-shortcuts-mini">
            <span class="btn btn-success"></span>

            <span class="btn btn-info"></span>

            <span class="btn btn-warning"></span>

            <span class="btn btn-danger"></span>
        </div>
    </div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/bai-viet/danh-sach?search=all&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý sản phẩm
            </a>
<%--            <ul class="submenu">--%>
<%--                <li>--%>
<%--                    <a href="<c:url value='/quan-tri/bai-viet/danh-sach?page=1&limit=2'/>">--%>
<%--                        <i class="menu-icon fa fa-caret-right"></i>--%>
<%--                        Quản lý bài viết bài viết--%>
<%--                    </a>--%>
<%--                    <b class="arrow"></b>--%>
<%--                </li>--%>
<%--            </ul>--%>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/nguoi-dung/danh-sach?search=all&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý người dùng
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/chu-de/danh-sach?search=all&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý chủ đề
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/tac-gia/danh-sach?search=all&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý tác giả
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/nha-xuat-ban/danh-sach?search=all&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý nhà xuất bản
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/don-hang?search=all&status=0&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Xác nhận đơn hàng
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/don-hang?search=all&status=1&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Đơn hàng đang vận chuyển
            </a>
        </li>
    </ul>
    <div>&nbsp</div>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/don-hang?search=all&status=2&page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Danh sách đơn hàng
            </a>
        </li>
    </ul>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/thong-ke?page=1'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Thống kê
            </a>
        </li>
    </ul>
    <ul class="nav nav-list">
        <li >
            <a href="<c:url value='/quan-tri/tro-chuyen'/>" >
                <i class="menu-icon fa fa-list"></i>
                <span class="menu-text"></span>
                Quản lý chat
            </a>
        </li>
    </ul>
    <div class="sidebar-toggle sidebar-collapse">
        <i class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
    </div>
</div>