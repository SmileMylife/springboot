<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>语句替换及转置工具</title>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <style type="text/css">
        html,body {
            height: 100%;
            overflow: hidden;
        }
        .wrap_left {
            width: 50%;
            height: 100%;
            float: left;
        }

        .wrap_left textarea {
            width: 90%;
            height: 100px;
            display: block;
            margin: 0 auto;
            resize: none;
        }

        select {
            width: 115px;
            height: 30px;
            outline: none;
        }

        .wrap_right {
            width: 50%;
            height: 100%;
            float: right;
        }

        .column_wrap input {
            display: block;
            width: 100px;
            height: 25px;
            margin-bottom: 5px;
        }

        .column_wrap textarea {
            display: block;
            resize: none;
            width: 100px;
            height: 400px;
            margin-bottom: 5px;
            border-radius: 3px;
        }

        .column_wrap {
            float: left;
            margin: 0px 10px;
        }

        .btn_wrap {
            clear: both;
            text-align: center;
            margin-top: 30px;
        }

        .btn_wrap button {
            width: 120px;
            height: 30px;
            border-radius: 3px;
        }

        .btn_wrap button:hover {
            background-color: rgb(238, 240, 244);
            cursor: pointer;
        }

        #sqlProduct {
            display: none;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            //变更使用方式
            $("#useStyle").change(function() {
                if($("#useStyle").val() == "行列转置工具") {
                    $("#sqlProduct").css("display", "none");
                    $("#reverse").css("display", "block");
                } else if($("#useStyle").val() == "sql模板置换工具") {
                    $("#sqlProduct").css("display", "block");
                    $("#reverse").css("display", "none");
                }
            });

            //点击添加按钮
            $("#addColumn").click(function() {
                if($("#wrapRight input").length >= 5) {
                    alert("当前只允许添加5个变量！");
                    return;
                }
                var initContent = $("#wrapRight").html();

                var column = "<div class=\"column_wrap\">\n" +
                    "                <input type=\"text\" />\n" +
                    "                <textarea></textarea>\n" +
                    "            </div>";
                $("#wrapRight").html(initContent + column);
            });

            //点击提交按钮
            $("#submit").click(function() {
                var varArr = [];
                var varValArr = [];
                $("#wrapRight input").each(function() {
                    varArr.push($(this).val());
                });

                $("#wrapRight textarea").each(function() {
                    varValArr.push($(this).val());
                });

                //发送数据到后台处理
                $.ajax({
                    url: "/sqlReplaceByTemplate",
                    data: {
                        "vars": JSON.stringify(varArr),
                        "varVals": JSON.stringify(varValArr),
                        "sql": $("#sql").val()
                    },
                    type: "post",
                    error: function() {
                        alert("发送后台报错！");
                    },
                    success: function(data) {
                        console.log("sql替换成功！", data);
                        $("#result").val(data);
                    }
                })
            })
        })
    </script>
</head>
<body>
    <h2 style="text-align: center">语句替换及转置工具</h2>
    <div>
        <select id="useStyle">
            <option value="行列转置工具">行列转置工具</option>
            <option value="sql模板置换工具">sql模板置换工具</option>
        </select>
        <div id="sqlProduct">
            <div class="wrap_left">
                <div>
                    <h3>sql模板</h3>
                    <textarea id="sql"></textarea>
                </div>
                <div>
                    <h3>生成sql结果</h3>
                    <textarea id="result"></textarea>
                </div>
                <div class="btn_wrap">
                    <button id="addColumn">添加一列</button>
                    <button id="submit" type="button">提交</button>
                </div>
            </div>
            <div class="wrap_right" id="wrapRight">
                <div class="column_wrap">
                    <input type="text" />
                    <textarea></textarea>
                </div>
            </div>
        </div>
        <div id="reverse">
            <textarea></textarea>
            <textarea></textarea>
        </div>
    </div>
</body>
</html>