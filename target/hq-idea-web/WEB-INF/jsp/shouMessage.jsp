<%--
  Created by IntelliJ IDEA.
  User: hq
  Date: 16/10/27
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>show</title>
</head>
<script src="http://cdn.sockjs.org/sockjs-0.3.min.js"></script>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="/cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">
<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="/cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="/cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
<!--<script type="text/javascript" src="js/jquery-1.7.2.js"></script>-->
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="/cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<body>
    <input type="text"/>
    <textarea id="msg"></textarea>
</body>
<script type="text/javascript">
    $(function(){
        var websocket;
        if ('WebSocket' in window) {
            alert("WebSocket");
            websocket = new WebSocket("ws://127.0.0.1:8080/echo");
        } else if ('MozWebSocket' in window) {
            alert("MozWebSocket");
            websocket = new MozWebSocket("ws://echo");
        } else {
            alert("SockJS");
            websocket = new SockJS("http://127.0.0.1:8080/sockjs/echo");
        }
        websocket.onopen = function (evnt) {
            alert("链接服务器成功!")
        };
        websocket.onmessage = function (evnt) {
            $("#msg").html($("#msg").html() + "<br/>" + evnt.data);
        };
        websocket.onerror = function (evnt) {
            alert("链接出现异常");
        };
        websocket.onclose = function (evnt) {
            alert("与服务器断开了链接!")
        }
        $('#send').bind('click', function() {
            send();
        });
        function send(){
            if (websocket != null) {
                var message = document.getElementById('message').value;
                var title = "message";
                var obj = {message:message,title:title};
                var str = JSON.stringify(obj);
                websocket.send(str);
            } else {
                alert('未与服务器链接.');
            }
        }
    });
</script>
</html>
