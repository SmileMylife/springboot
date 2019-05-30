<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>错误页面</title>
    <script type="text/javascript">
        window.onload = function() {
            setTimeout(function() {
                location.href="http://localhost:8080?isError=true";
            }, 3000)
        }
    </script>
</head>
<body>
    <h4 style="text-align: center">服务器发生了错误，请重新尝试，3秒后自动跳转至首页。</h4>
    <a style="text-align: center;display: block" href="http://localhost:8080?isError=true">点击这里直接跳转</a>
</body>
</html>