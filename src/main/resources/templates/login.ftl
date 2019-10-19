<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>脚本生成工具</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //刷新验证码
            $("#refreshQRcode").click(function() {
                $("#QRcode").attr("src", "/refreshQRcode?" + Math.random());
            });
            /*$("#quikLogin").click(function() {
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
            })*/
        })
    </script>
</head>
<body>
    <h3>登录页面</h3>
    <a href="/loginSqlProduct" id="quikLogin">一键快捷登录</a>
    <img src="/refreshQRcode" id="QRcode" />
    <button id="refreshQRcode">刷新二维码</button>
</body>
</html>