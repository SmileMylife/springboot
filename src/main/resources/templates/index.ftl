<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>脚本生成工具</title>
    <style type="text/css">
        .form_wrap {
            width: 400px;
            margin: 0 auto;
        }
        label {
            float: left;
        }

        .form_wrap input, select {
            float: right;
        }

        .form_ele_wrap {
            overflow: hidden;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
    <script type="text/javascript">
        var url = "/queryAllProv";
        $(document).ready(function() {
            $.ajax({
                url: url,
                type: "post",
                data: {
                    "username": "zhangpei"
                },
                dataType: "json",
                success: function(data, status) {
                    var innerContent = "";
                    for(var i = 0; i < data.beans.length; i++) {
                        innerContent += "<option value=" + data.beans[i].provNm + ">" + data.beans[i].provNm + "</option>";
                    }
                    $("#provNm").html(innerContent);
                },
                error: function() {
                    alert("请求发送失败！");
                }
            })

            //点击提交表单
            $("#submit").click(function() {
                console.log($("#productSql").serializeArray());

            })
        })
    </script>
</head>
<body>
    <form id="productSql" enctype="multipart/form-data" class="form_wrap">
        <div>
            <div class="form_ele_wrap">
                <label for="operation">操作类型</label>
                <select name="operation">
                    <option>INSERT</option>
                    <option>UPDATE</option>
                    <option>DELETE</option>
                </select>
            </div>
            <div class="form_ele_wrap">
                <label for="provNm">省份名称</label>
                <select id="provNm"></select>
            </div>
            <div class="form_ele_wrap">
                <label for="username">姓名拼音</label>
                <input type="text" name="username" placeholder="请输入姓名拼音" />
            </div>
            <div class="form_ele_wrap">
                <label for="jira">任务编号</label>
                <input type="text" name="jira" placeholder="请输入任务编号" />
            </div>
            <div class="form_ele_wrap">
                <label for="time">任务版本时间</label>
                <input type="date" name="time" />
            </div>
            <div class="form_ele_wrap">
                <label for="connUsername">中文姓名</label>
                <input type="text" name="connUsername" placeholder="请输入中文姓名" />
            </div>
            <div class="form_ele_wrap">
                <label for="connPhone">联系电话</label>
                <input type="text" name="connPhone" placeholder="请输入联系电话" />
            </div>
            <input id="submit" type="button" value="提交" />
        </div>
    </form>
</body>
</html>