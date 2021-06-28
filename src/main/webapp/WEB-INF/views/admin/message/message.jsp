<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html lang="en">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Custom messanger</title>

</head>
<body>

<div class="container clearfix">
    <div class="row">
        <div class="col-sm-4">
            <div class="people-list" id="people-list">
                <div class="search">
                    <input id="userName" value="admin" disabled placeholder="nhập tên" type="text"/>
                    <button onclick="registration()">Bắt đầu chat</button>
                    <button onclick="fetchAll()">Làm mới</button><label id="newMessage" style="color: red"></label>
                </div>
                <ul class="list" id="usersList">
                </ul>
            </div>


        </div> <!-- end container -->
        <div class="col-sm-8">
            <div class="chat">
                <div class="chat-header clearfix">
                    <img alt="avatar" height="55px" style="border-radius: 50%"
                         src="/template/web/public/img/avatarUser.jpg"
                         width="55px"/>

                    <div class="chat-about">
                        <div class="chat-with" id="selectedUserId"></div>
                        <div class="chat-num-messages"></div>
                    </div>
                    <i class="fa fa-star"></i>
                </div> <!-- end chat-header -->

                <div  class="chat-history">
                    <ul>

                    </ul>

                </div> <!-- end chat-history -->

                <div class="chat-message clearfix">
                <textarea id="message-to-send" name="message-to-send" placeholder="nhập ..."
                          rows="3"></textarea>


                    <button id="sendBtn">Gửi</button>

                </div> <!-- end chat-message -->

            </div> <!-- end chat --></div>
    </div>

</div>
<script id="newMessage1" type="text/x-handlebars-template">
    <label>{{response}}</label>
</script>

<script id="message-template" type="text/x-handlebars-template">
    <li class="clearfix">
        <div class="message-data align-right">
            <span class="message-data-time">{{time}}, Today</span> &nbsp; &nbsp;
            <span class="message-data-name">Tôi</span> <i class="fa fa-circle me"></i>
        </div>
        <div class="message other-message float-right">
            {{messageOutput}}
        </div>
    </li>
</script>

<script id="message-response-template" type="text/x-handlebars-template">
    <li>
        <div class="message-data">
            <span class="message-data-name"><i class="fa fa-circle online"></i> {{userName}}</span>
            <span class="message-data-time">{{time}}, Today</span>
        </div>
        <div class="message my-message">
            {{response}}
        </div>
    </li>
</script>
</body>
</html>
