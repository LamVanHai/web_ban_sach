<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 05/16/2021
  Time: 3:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>Custom messanger</title>
    <link rel="stylesheet" href="main2.css" />
</head>
<body>
<div class="chat">
    <div class="chat-header clearfix">
        <div class="search">
            <div id="input" style="display: block">
                <table>
                    <tr>
                        <td><label>Tên:</label></td>
                        <td>
                            <input
                                    id="username"
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
                    <tr>
                        <td></td>
                        <td>
                            <button onclick="registration()">
                                Bắt đầu
                            </button>
                        </td>
                    </tr>
                </table>
            </div>
            <div id="show" style="display: none">
                <table>
                    <tr>
                        <td><label>Tên:</label></td>
                        <td>
                            <label id="username1" ></label>
                        </td>
                    </tr>
                    <tr>
                        <td><label>Số điện thoại:</label></td>
                        <td>
                            <label id="phone1" ></label>
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
        <i class="fa fa-star"></i>
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
                        rows="3"
                ></textarea>

        <i class="fa fa-file-o"></i> &nbsp;&nbsp;&nbsp;
        <i class="fa fa-file-image-o"></i>

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
            <span class='message-data-name'>You</span>
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
    function registration() {
        var username = document.getElementById('username').value;
        var phone = document.getElementById('phone').value;

        document.getElementById('input').style.display = 'none';
        document.getElementById('show').style.display = 'block';

        var username1 = document.getElementById('username1');
        var phone1 = document.getElementById('phone1');
        username1.value(username);
    }
</script>
</body>
</html>
