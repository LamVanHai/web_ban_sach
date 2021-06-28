
let stompClient;
let selectedUser='admin';
// let newMessages = new Map();
function connectToChat(userName) {
    console.log("connecting to chat...")
    let socket = new SockJS('http://localhost:8081' + '/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log("connected to: " + frame);
        stompClient.subscribe("/topic/messages/" + userName, function (response) {
            let data = JSON.parse(response.body);
            if (selectedUser === data.fromLogin) {
                render(data.message, data.fromLogin);
            } else {
                // newMessages.set(data.fromLogin, data.message);
                $('#userNameAppender_'+ data.fromLogin).append('<span id="newMessage_' + data.fromLogin + '" style="color: #ff0000; font-weight: bold"> +1</span>');
            }
        });
    });
}
function show() {
    let username=$('#userName').val();
    let phone = $('#phone').val();
    var input2 =document.getElementById('userName');
    var input1 = document.getElementById('phone');
    if(username==""||phone=="") {
        if (username == "") {
            input2.style.borderColor = "red";
        } else {
            input2.style.borderColor = "#979494";
        }
        if (phone == "") {
            input1.style.borderColor = "red";
        } else {
            input1.style.borderColor = "#979494";
        }
    }
    else {
        document.getElementById('input').style.display = 'none';
        document.getElementById('show').style.display = 'block';
        $('#username1').val(username);
        $('#phone1').val(phone);

    }


}

function sendMsg(from, text) {
    stompClient.send("/app/chat/" + selectedUser, {}, JSON.stringify({
        fromLogin: from,
        message: text
    }));
}

function registration() {
    let userName = document.getElementById('userName').value;
    if(userName==="admin"){
        alert("Đã tồn tại");
    }else {
        $.get('http://localhost:8081' + "/registration/" + userName, function (response) {
            connectToChat(userName);
            show();
        }).fail(function (error) {
            if (error.status === 400) {
                alert("Đã tồn tại!")
            }
        })
    }

}

function selectUser(userName) {
    console.log("selecting users: " + userName);
    selectedUser = userName;
    // let isNew = document.getElementById("newMessage_" + userName) !== null;
    // if (isNew) {
    //     let element = document.getElementById("newMessage_" + userName);
    //     element.parentNode.removeChild(element);
    //     render(newMessages.get(userName), userName);
    // }
    $('#selectedUserId').html('');
    $('#selectedUserId').append('Đang chat với ' + userName);
}
