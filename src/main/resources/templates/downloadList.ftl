<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>下载列表</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            //请求后台下载列表数据
            $.ajax({
                url: ,
                data: {

                },
                type: "post",
                error: function() {
                    alert("请求后台出错！");
                },
                success: function(data, status) {
                    console.log("请求后台成功：", data);
                }
            })
        })
    </script>
</head>
<body>
    
</body>
</html>