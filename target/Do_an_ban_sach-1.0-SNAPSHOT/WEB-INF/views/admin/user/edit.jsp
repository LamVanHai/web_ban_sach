<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="newURL" value="/quan-tri/nguoi-dung/danh-sach"/>
<c:url var="editNewURL" value="/quan-tri/nguoi-dung/chinh-sua"/>
<c:url var="userAPI" value="/api/users"/>
<c:url var="enableAPI" value="/setEnable"/>
<html>
<head>
    <title>Chỉnh sửa bài viết</title>
</head>
<body>

<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Home</a>
                </li>

                <li><a href="#">Forms</a></li>
                <li class="active">Form Elements</li>
            </ul>
            <!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <div class="col-sm-6 col-sm-offset-3 col-xs-12">
                        <div class="wrapper-authen">
                            <p id="checkpassword"></p>
                            <c:if test="${not empty message}">
                                <div class="alert alert-${alert}">
                                        ${message}
                                </div>
                            </c:if>
                            <c:if test="${param.error_system != null}">
                                <div class="alert alert-danger">
                                    Lỗi hệ thống!
                                </div>
                            </c:if>
                            <c:if test="${param.email_exist != null}">
                                <div class="alert alert-danger">
                                    E-mail đã tồn tại!
                                </div>
                            </c:if>
                            <c:if test="${param.success != null}">
                                <div class="alert alert-danger">
                                    Đăng ký thành công!
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label>Chọn quyền:</label>
                                <input name="role12" type="radio" id="admin"  style="margin-left: 14px" />
                                &nbsp ADMIN <br>
                                <input name="role12" type="radio" id="user" style="margin-left: 100px" />&nbsp
                                USER
                            </div>
                            <div class="form-group">
                                <label>Trạng thái:</label>
                                <input name="enable12" type="radio" id="enable" value="1" style="margin-left: 14px"/>
                                &nbsp Kích hoạt <br>
                                <input name="enable12" type="radio" id="disEnable" value="0" style="margin-left: 87px"/>
                                Chưa kích hoạt
                            </div>
                            <form:form class="form-horizontal" role="form" id="formSubmit" modelAttribute="model">
                                <div class="form-group">
                                    <label for="email">Email:</label>
                                    <form:input path="email" class="form-control" id="email"/>
                                </div>
                                <div class="form-group">
                                    <label for="name">Tên:</label>
                                    <form:input path="name" type="texr" class="form-control" id="name"/>
                                </div>
                                <div class="form-group">
                                    <label for="new_password">Mật khẩu:</label>
                                    <form:input path="new_password"  type="password" class="form-control" id="new_password"/>
                                </div>
                                <div class="form-group">
                                    <label for="confirm_password">Xác nhận mật khẩu:</label>
                                    <form:input path="confirm_password" type="password" class="form-control"
                                                id="confirm_password"/>
                                </div>
                                <div class="form-group">
                                    <label for="phone_number">Số điện thoại:</label>
                                    <form:input path="phone_number" type="text" class="form-control" id="phone_number"/>
                                </div>
                                <div class="form-group">
                                    <label for="address">Địa chỉ:</label>
                                    <form:input path="address" type="text" class="form-control" id="address"/>
                                </div>

                                <form:hidden path="id" id="newId"/>
                                <form:hidden path="role" id="role"/>
                                <form:hidden path="enable" id="newEnable"/>
                                <div class="clearfix form-actions">
                                    <div>
                                        <c:if test="${not empty model.id}">
                                            <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                Cập nhật người dùng
                                            </button>
                                        </c:if>
                                        <c:if test="${empty model.id}">
                                            <button class="btn btn-info" type="button" id="btnAddOrUpdateNew">
                                                <i class="ace-icon fa fa-check bigger-110"></i>
                                                Thêm người dùng
                                            </button>
                                        </c:if>&nbsp; &nbsp; &nbsp;
                                            <button class="btn" type="reset">
                                                <i class="ace-icon fa fa-undo bigger-110"></i>
                                                Hủy
                                            </button>&nbsp;
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    $(document).ready(function () {
        let id=$('#newId').val();
        let enable=$('#newEnable').val();
        let role=$('#role').val();
        if(id==""){
            document.getElementById("enable").checked=true;
            document.getElementById("user").checked = true;
        }
        else {
            if (enable==0){
                document.getElementById("disEnable").checked=true;
            }else {
                document.getElementById("enable").checked=true;
            }
            if(role=="ADMIN"){
                document.getElementById("admin").checked = true;
            }else {
                document.getElementById("user").checked = true;
            }
        }


    })
    $('#btnAddOrUpdateNew').click(function (e) {
        e.preventDefault();
        let role1;
        let enable1;
        if(document.getElementById("admin").checked===true){
            role1="admin";
        }else {
            role1="user";
        }
        if(document.getElementById("enable").checked===true){
            enable1=1;
        }else {
            enable1=0;
        }

        let data = {role1:role1,
        enable1:enable1,};
        let name=$('#name').val();
        let new_password=$('#new_password').val();
        let confirmPassWord=$('#confirm_password').val();
        let email=$('#email').val();
        let input=document.getElementById("name");
        let input3=document.getElementById("email");
        let input1=document.getElementById("new_password");
        let input2=document.getElementById("confirm_password");
        if(name==""||new_password==""||confirmPassWord==""||email==""){
            if(name==""){
                input.style.borderColor="red";
            }
            else {
                input.style.borderColor="#979494";
            }
            if(new_password==""){
                input1.style.borderColor="red";
            }
            else {
                input1.style.borderColor="#979494";
            }
            if(confirmPassWord==""){
                input2.style.borderColor="red";
            }
            else {
                input2.style.borderColor="#979494";
            }
            if(email==""){
                input3.style.borderColor="red";
            }
            else {
                input3.style.borderColor="#979494";
            }
        }
        else if(new_password!=confirmPassWord){
            $('#checkpassword').html("<div class=\"alert alert-danger\">\n" +
                "                        Mật khẩu không khớp!</div>")
        }else if(!emailValidate(email)){
            $('#checkpassword').html("<div class=\"alert alert-danger\">\n" +
                "                        Định dạng g-mail không đúng!</div>")
        }else {
            let formData = $('#formSubmit').serializeArray();
            $.each(formData, function (i, v) {
                data["" + v.name + ""] = v.value;
            });
            let id = $('#newId').val();
            if (id == "") {
                addNew(data);
            } else {
                updateNew(data);
            }
        }

    });
    function emailValidate(email) {
        var validator=/^[_A-Za-z0-9-\+]+(\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\.[A-Za-z0-9]+)*(\.[A-Za-z]{2,})$/;
        if(validator.test(email)){
            return true;
        }
        return false;
    }

    function addNew(data) {
        $.ajax({
            url: '${userAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            success: function (result) {
                window.location.href = "${newURL}?search=all&page=1";
            },
            error: function (error) {
                window.location.href = "${newURL}?search=all&page=1";
            }
        });
    }

    function updateNew(data) {
        $.ajax({
            url: '${userAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "${editNewURL}?id=" + result + "&message=update_success";
            },
            error: function (error) {
                window.location.href = "${editNewURL}?id=" + result+ "&message=error_system";
            }
        });
    }

</script>
</body>
</html>
