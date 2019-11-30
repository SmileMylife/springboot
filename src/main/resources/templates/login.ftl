<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录页面</title>
    <script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
    <!-- DOM相关 -->
    <script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
    <!-- 支持JSX -->
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
    <link rel="stylesheet" href="../static/css/antd.css">
    <script src="../static/js/moment.min.js"></script>
    <script type="text/javascript" src="../static/js/antd.js"></script>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <link type="text/css" rel="stylesheet" href="../static/css/login.css" />
    <link type="text/css" rel="stylesheet" href="../static/css/global.css" />
    <script type="text/javascript">
        /*$(document).ready(function() {
            //刷新验证码
            $("#refreshQRcode").click(function() {
                $("#QRcode").attr("src", "/refreshQRcode?" + Math.random());
            });
            /!*$("#quikLogin").click(function() {
                //快捷登录，ajax响应是文本，不能用这种
                $.ajax("/loginSqlProduct", {
                    type: "post",
                    data: {username: "zhangpei", password: "123"},
                    success: function(data, status) {
                        console.log("请求成功响应：", data);
                    },
                    error: function() {
                        alert("一键登录失败！");
                    }
                })
            })*!/
        })*/
    </script>
    <script type="text/babel" src="../static/js/loginForm.js"></script>
</head>
<body>
    <div id="root"></div>
</body>
</html>