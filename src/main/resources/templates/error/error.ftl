<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <script type="text/javascript">
        window.onload = function() {
            var timeout = setTimeout(function() {
                location.href="http://localhost:8080?isError=true";
            }, 3000);

            var breakToIndex = document.getElementById("breakToIndex");
            breakToIndex.onclick = function() {
                console.log("点击了跳转！");
                clearTimeout(timeout);
            }
        }
    </script>
</head>
<body>
    <h4 style="text-align: center">服务器发生了错误，错误描述：<b style="color: red">${errorDesc}</b>请重新尝试，3秒后自动跳转至首页。</h4>
    <a id="breakToIndex" style="text-align: center;display: block" href="http://localhost:8080?isError=true">点击这里直接跳转</a>
</body>
</html>