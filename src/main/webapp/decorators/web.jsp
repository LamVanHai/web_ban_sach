<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Electronic Store</title>
    <meta property="fb:app_id" content="184326453541960"/>
    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/template/web/css/bootstrap.min.css'/>">

    <link rel="stylesheet" href="<c:url value='/template/web/css/ownstyle.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/effect.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/responsive.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/font-awesome.min.css'/>">
    <link rel="stylesheet" href="<c:url value='/template/web/css/main.css'/>">
    <%--	<link rel="stylesheet" href="<c:url value='/template/web/css/theme4.css'/>">--%>

    <link rel="stylesheet" href="<c:url value='/template/admin/font-awesome/4.5.0/css/font-awesome.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/assets/css/ace.min.css' />" class="ace-main-stylesheet"
          id="main-ace-style"/>


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
    <link rel="stylesheet" href="<c:url value='/template/admin/sweetalert/sweetalert2.min.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/admin/css/custumhai.css' />"/>
    <link rel="stylesheet" href="<c:url value='/template/web/css/main2.css' />"/>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
<%--    --%>
<%--    phan chat--%>
    <script src="<c:url value='/template/web/js/js3/chat.js'/>"></script>
    <script src="//cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <%--	hander--%>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.0/handlebars.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/list.js/1.1.1/list.min.js"></script>
    <%--end hander--%>

</head>
<body>
<div style="position: static">
    <%@include file="/common/web/header.jsp" %>

    <dec:body/>

    <%@include file="/common/web/footer.jsp" %>
</div>

<button style="border: none" onclick="openMenuChat()">
    <div class="iconchat" style="position: fixed;top: 550px; right: 10px; width: 60px;height: 60px">
        <img src="/template/web/public/img/iconchat1.jpg">
    </div>
</button>
<div id="menuchat" class="menuchat" style="display: none; z-index: 10000">
    <div style="background-color: #2f7bba; height: 20px;width: 100%;margin-top: 0px"><h5
            style="font-weight: bold; text-align: center ">Chat với cửa hàng</h5></div>
    <div class="chat">
        <div class="chat-header clearfix">
            <div class="search">
                <div id="input" style="display: block">

                    <table>
                        <tr>
                            <td><label>Tên:</label></td>
                            <td>
                                <input
                                        id="userName"
                                        placeholder="nhập tên"
                                        type="text"
                                />
                            </td>
                        </tr>
                        <tr>
                            <td><label>Số điện thoại:</label></td>
                            <td>
                                <input
                                        id="phone"
                                        placeholder="nhập số điện thoại"
                                        type="text"
                                />
                            </td>
                        </tr>

                    </table>
                    <div>&nbsp</div>
                    <div>
                        <button style="background-color: #d58512; width: 100%" onclick="registration()">
                            Bắt đầu chat
                        </button>
                    </div>
                    <c:if test="${status1==0}">
                        <div>
                            <button style="background-color: #d58512; width: 100%" onclick="reConnect()">
                                Kết nối lại
                            </button>
                        </div>
                    </c:if>
                </div>
                <div id="show" style="display: none">
                    <table>
                        <tr>
                            <td><label>Tên:</label></td>
                            <td>
                                <input disabled id="username1"></input>
                            </td>
                        </tr>
                        <tr>
                            <td><label>Số điện thoại:</label></td>
                            <td>
                                <input disabled id="phone1"></input>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="chat-about">
                <div class="chat-with" id="selectedUserId"></div>
                <div class="chat-num-messages"></div>
            </div>
        </div>
        <!-- end chat-header -->

        <div class="chat-history">
            <ul></ul>
        </div>
        <!-- end chat-history -->

        <div class="chat-message clearfix">
                <textarea
                        id="message-to-send"
                        name="message-to-send"
                        placeholder="nhập..."
                        rows="2"
                ></textarea>
            <button id="sendBtn">Gửi</button>
        </div>
        <!-- end chat-message -->
    </div>
    <!-- end chat -->

    <script id="message-template" type="text/x-handlebars-template">
        <li class='clearfix'>
            <div class='message-data align-right'>
                <span class='message-data-time'>{{time}}, Today</span>
                &nbsp; &nbsp;
                <span class='message-data-name'>Tôi</span>
                <i class='fa fa-circle me'></i>
            </div>
            <div class='message other-message float-right'>
                {{messageOutput}}
            </div>
        </li>
    </script>

    <script
            id="message-response-template"
            type="text/x-handlebars-template"
    >
        <li>
            <div class='message-data'>
                    <span class='message-data-name'><i
                            class='fa fa-circle online'
                    ></i>
                        {{userName}}</span>
                <span class='message-data-time'>{{time}}, Today</span>
            </div>
            <div class='message my-message'>
                {{response}}
            </div>
        </li>
    </script>
    <script>
        function openMenuChat() {
            if(document.getElementById('menuchat').style.display === 'none'){
                document.getElementById('menuchat').style.display = 'block';
            }else if(document.getElementById('menuchat').style.display = 'block') {
                document.getElementById('menuchat').style.display = 'none';
            }


        }


    </script>
</div>

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
<footer>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <img style="border-radius:50%;"
         src="https://www.google.com/search?q=%E1%BA%A3nh+ch%C3%A2n+dung&tbm=isch&ved=2ahUKEwjjspXV4MvwAhUEgpQKHdx5DjQQ2-cCegQIABAA&oq=%E1%BA%A3nh+ch%C3%A2n+dung&gs_lcp=CgNpbWcQAzIFCAAQsQMyAggAMgIIADICCAAyAggAMgIIADICCAAyAggAMgIIADICCAA6BAgjECc6BwgAELEDEEM6BAgAEEM6CggAELEDEIMBEEM6BggAEAgQHjoGCAAQChAYUNY9WJlcYJpeaAJwAHgAgAGbAYgByweSAQQxMS4xmAEAoAEBqgELZ3dzLXdpei1pbWfAAQE&sclient=img&ei=jMefYOO8NYSE0gTc87mgAw&bih=880&biw=1707&rlz=1C1KNTJ_enVN928VN928#imgrc=wmagygjvwCWrZM">
</footer>
<%--phan chat--%>
<script src="<c:url value='/template/web/js/js3/chat.js' />"></script>
<script src="<c:url value='/template/web/js/js3/custom.js' />"></script>

</html>