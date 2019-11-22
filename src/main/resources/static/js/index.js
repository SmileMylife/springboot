/**
 * Created by ZhangPei on 2019/11/21.
 */
//查询省份下拉列表
var url = "/queryAllProv";
$(document).ready(function () {
    console.log("是否错误页面，", $("#isError").val());
    $.ajax({
        url: url,
        type: "post",
        dataType: "json",
        async: false,
        success: function (data, status) {
            var innerContent = "";
            for (var i = 0; i < data.beans.length; i++) {
                innerContent += "<option value=" + data.beans[i].provNm + ">" + data.beans[i].provNm + "</option>";
            }
            $("#provNm").html("<option value=''>请选择</option><option value='全省'>全省</option>" + innerContent);
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
        debugger;
        console.log("用户填写的数据内容：" + data);

        //回滚sql加入进去
        /*if($("select[name = 'isRollback']").val() == 1) {
            data.push({
                name: "rollbackSql",
                        value: encodeURI($("#rollbackSql").val())
            })
        }*/

        // var getStr = "";
        var obj = {};

        for(var j = 0; j < data.length; j++) {
            // getStr += (data[j].name + "=" + data[j].value + "&");
            obj[data[j].name] = data[j].value;
            if (data[j].name == "provNmMulti" && $("#provNm").val() == "全省") {
                continue;
            }
            if(data[j].value == null || data[j].value == undefined || data[j].value == "") {
                alert("请完整填写表单!");
                return;
            }
        }

        //保存数据至localstorage
        localStorage.setItem("currentPeople", $("input[name='connUsername']").val());
        localStorage.setItem($("input[name='connUsername']").val(), JSON.stringify(data));

        // getStr = getStr.substring(0, getStr.length - 1);
        // location.href = "http://localhost:8080/productSqlFile?" + getStr;

        sendHttpPost("http://localhost:8080/productSqlFile", obj);
    });

    //加载完毕，如果是错误页面跳转过来，需要回填数据
    if($("#isError").val() === "true") {
        var currentPeople = localStorage.getItem("currentPeople");
        if(currentPeople) {
            //回填页面数据
            var jsonData = localStorage.getItem(currentPeople);
            var arr = JSON.parse(jsonData);
            console.log("页面回填数据：", jsonData);
            if(arr instanceof Array) {
                var isBackup = "";
                var operation = "";
                for(var k = 0; k < arr.length; k++) {
                    $("[name=" + arr[k].name + "]").val(decodeURI(arr[k].value));
                    if(arr[k].name === "isBackup") {
                        isBackup = arr[k].value;
                    }else if(arr[k].name === "operation") {
                        operation = arr[k].value;
                    }
                }
                if(operation === "UPDATE") {
                    $("#isBackup").css("display", "block");
                    $("#isBackup").val(isBackup);
                }
            }
        }
    }

    //是否生成回滚脚本
    $("select[name='operation']").change(function() {
        if($("select[name='operation']").val() == "UPDATE"
            || $("select[name='operation']").val() == "DELETE") {
            $("#isBackup").css("display", "block");
        } else {
            $("#isBackup").css("display", "none");
        }
        $("#isBackup").val("0");
    });

    var intputVal = "";

    //点击添加
    $("#add").click(function(){
        var inputVal = $("#provNmMulti").val();
        if (inputVal.indexOf($("#provNm").val()) != -1 || $("#provNm").val() == "") {
            return;
        }
        $("#provNmMulti").val(inputVal + $("#provNm").val() + ",");
    });


    //发送post请求

    function sendHttpPost(url, params) {
        let temp = document.createElement("form");
        temp.action = url;
        temp.method = "post";
        temp.style.display = "none";

        for (let x in params) {
            let opt = document.createElement("textarea");
            opt.name = x;
            opt.value = params[x];
            temp.appendChild(opt);
        }

        document.body.appendChild(temp);
        temp.submit();
        return temp;
    }

    /*//是否回滚改变时控制回滚脚本内容的显示
    $("select[name='isRollback']").change(function() {
        debugger;
        if($("select[name='isRollback']").val() == 1) {
            $(".sql_wrap_rollback").css("display", "block");
        } else {
            $(".sql_wrap_rollback").css("display", "none");
        }
    });*/

    //测试http发送请求
    /*var timeOut = setTimeout(function() {
        $.ajax("http://localhost:8080/testAjax", {
            type: "post",
            // contentType: "appplication/json",
            data: JSON.stringify({"username": "password"}),
        })
    }, 3000);*/
})