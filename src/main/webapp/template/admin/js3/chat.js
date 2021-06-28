const url = 'http://localhost:8081';
let stompClient;
let selectedUser="";
// let newMessages = new Map();
function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS(url + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            $('#newMessage').html("<span>Tin nhắn mới</span>");
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);
            } else {
                // newMessages.set(data.fromLogin, data.message);
                // $('#newMessage_'+data.fromLogin).html('');
                // $('#userNameAppender_'+ data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: #ff0000; font-weight: bold"> +1 </span>')
                $('#newMessage1_'+data.fromLogin).html("<span style=\"color: #ff0000; font-weight: bold\">+1</span>")
            }
        });
    });
}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: from,
        message: text
    }));
}

function sendMsg1() {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: "admin",
        message: "đã ngắt kết nối",
    }));
}

function registration() {
    var userName = document.getElementById('userName').value;
    $.get(url + "/registration/" + userName, function (response) {
        connectToChat(userName);
    }).fail(function (error) {
        if (error.status === 400) {
            alert("Đã tồn tại!")
        }
    })
}
function deleteUser(userName){
    sendMsg1();
    deleteUser1(userName);
}

function selectUser(userName) {
    $chatHistoryList.html('<ul></ul>')
    console.log("selecting users: " + userName);
    selectedUser = userName;
    // let isNew = document.getElementById("newMessage_" + userName) !== null;
    // if (isNew) {
    //     let element = document.getElementById("newMessage_" + userName);
    //     element.parentNode.removeChild(element);
    //     render(newMessages.get(userName), userName);
    // }
    $('#newMessage1_'+userName).html('');
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Đang chat với ' + userName);

    var data={
        fromLogin:userName
    }
    getListMessage(data);

}

function getListMessage(data) {
    $.ajax({
        url:'/listMessage',
        type:"POST",
        data: JSON.stringify(data),
        contentType: 'application/json',
        dataType:'json',
        success: function (result) {
            $.each($(result),function (index, itemData){
                if(itemData.fromLogin!="admin"){
                    render(itemData.message,itemData.fromLogin)

                }else {
                    sendMessage1(itemData.message,itemData.fromLogin);
                }
            })
        },
        error: function (error) {
           alert(error.message+"loi")
        }
    })
}

function fetchAll() {
    $('#newMessage').html('');
    $.ajax({
        url: '/fetchAllUsers',
        type: "GET",
        success: function (result) {
            // let users = result;
            // let usersTemplateHTML = "";
            // for (let i = 0; i < users.length; i++) {
            //     usersTemplateHTML = usersTemplateHTML + '<a href="#" onclick="selectUser(\'' + users[i] + '\')"><li class="clearfix">\n' +
            //         '                <img  width="55px" height="55px" alt="avatar" />\n' +
            //         '                <div class="about">\n' +
            //         '                    <div id="userNameAppender_' + users[i] + '" class="name">' + users[i] + '</div>\n' +
            //         '                    <div class="status">\n' +
            //         '                        <i class="fa fa-circle offline"></i>\n' +
            //         '                    </div>\n' +
            //         '                </div>\n' +
            //         '            </li></a>';
            // }
            $('#usersList').html(result);
        },
        error: function (error) {
            alert("lỗi");
        },
    });
}

function deleteUser1(userName) {
    var data={
        fromLogin: userName
    }
    $.ajax({
        url:'/deleteUser',
        type: 'POST',
        data:JSON.stringify(data),
        contentType:'application/json',
        success: function (result) {
            $('#usersList').html(result);
        },
        error: function (error) {
            $('#usersList').html(error);
        }
    })

}
