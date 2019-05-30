<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>脚本生成工具</title>
    <style type="text/css">
        html {
            font: "Menlo, 微软雅黑";
            font-size: 16px;
        }

        .form_wrap {
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid black;
            border-radius: 5px;
            overflow: hidden;
            background-color: rgb(238, 240, 244);
        }

        .button_wrap {
            text-align: center;
            margin-top: 20px;
        }

        .form_input {
            width: 200px;
            height: 20px;
            font-size: 13px;
        }

        label {
            float: left;
        }

        .form_ele_wrap input, select {
            float: right;
            background-color: rgb(255, 255, 255);
            outline: none;
            padding-left: 3px;
            border: 1px solid black;
            border-radius: 3px;
        }

        .form_ele_wrap {
            margin-top: 20px;
            overflow: hidden;
        }

        h3 {
            text-align: center;
        }

        textarea {
            font-size: 15px;
            width: 90%;
            height: 200px;
            overflow: auto;
            display: block;
            margin: 20px auto;
            resize: none;
        }

    </style>
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        //查询省份下拉列表
        var url = "/queryAllProv";
        $(document).ready(function () {
            console.log("是否错误页面，", $("#isError").val());
            $.ajax({
                url: url,
                type: "post",
                dataType: "json",
                success: function (data, status) {
                    var innerContent = "";
                    for (var i = 0; i < data.beans.length; i++) {
                        innerContent += "<option value=" + data.beans[i].provNm + ">" + data.beans[i].provNm + "</option>";
                    }
                    $("#provNm").html("<option value=''>请选择</option>" + innerContent);
                },
                error: function () {
                    alert("查询省份下拉值失败！");
                }
            });

            //点击提交表单
            $("#submit").click(function () {
                var data = $("#productSql").serializeArray();
                data.push({
                    name: "sql",
                    value: encodeURI($("#sql").val())
                });
                var getStr = "";

                for(var j = 0; j < data.length; j++) {
                    getStr += (data[j].name + "=" + data[j].value + "&");
                    /*if(data[j].value == null || data[j].value == undefined || data[j].value == "") {
                        alert("请完整填写表单!");
                        return;
                    }*/
                }

                //保存数据至localstorage
                localStorage.setItem("currentPeople", $("input[name='connUsername']").val());
                localStorage.setItem($("input[name='connUsername']").val(), JSON.stringify(data));

                getStr = getStr.substring(0, getStr.length - 1);
                location.href = "http://localhost:8080/productSqlFile?" + getStr;
            });

            //加载完毕，如果是错误页面跳转过来，需要回填数据
            if($("#isError").val() == "true") {
                debugger;
                var currentPeople = localStorage.getItem("currentPeople");
                if(currentPeople) {
                    //回填页面数据
                    var jsonData = localStorage.getItem(currentPeople);
                    var arr = JSON.parse(jsonData);
                    console.log("页面回填数据：", jsonData);
                    if(arr instanceof Array) {
                        for(var k = 0; k < arr.length; k++) {
                            $("[name=" + arr[k].name + "]").val(arr[k].value);
                        }
                    }
                }
            }
        })
    </script>
</head>
<body>
<input type="hidden" id="isError" value=${isError} />
<h3>脚本生成工具</h3>
<div class="form_wrap">
    <form id="productSql">
        <div>
            <div class="form_ele_wrap">
                <label for="operation">操作类型</label>
                <select name="operation">
                    <option value="">请选择</option>
                    <option value="INSERT">INSERT</option>
                    <option value="UPDATE">UPDATE</option>
                    <option value="DELETE">DELETE</option>
                </select>
            </div>
            <div class="form_ele_wrap">
                <label for="provNm">省份名称</label>
                <select name="provNm" id="provNm"></select>
            </div>
            <div class="form_ele_wrap">
                <label for="username">姓名拼音</label>
                <input class="form_input" type="text" name="username" placeholder="请输入姓名拼音"/>
            </div>
            <div class="form_ele_wrap">
                <label for="jira">任务编号</label>
                <input class="form_input" type="text" name="jira" placeholder="请输入任务编号"/>
            </div>
            <div class="form_ele_wrap">
                <label for="time">任务版本时间</label>
                <input type="date" name="time"/>
            </div>
            <div class="form_ele_wrap">
                <label for="connUsername">中文姓名</label>
                <input class="form_input" type="text" name="connUsername" placeholder="请输入中文姓名"/>
            </div>
            <div class="form_ele_wrap">
                <label for="connPhone">联系电话</label>
                <input class="form_input" type="text" name="connPhone" placeholder="请输入联系电话"/>
            </div>
            <div class="button_wrap">
                <input class="form_input" id="submit" value="提交" type="button"/>
            </div>
        </div>
    </form>
</div>
<div class="sql_wrap">
    <h4 style="text-align: center">请输入sql语句</h4>
    <textarea id="sql" name="sql"></textarea>
</div>

</body>
</html>