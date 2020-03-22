<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图文识别</title>
    <script src="../static/plugin_js/jquery.min.js"></script>
    <script src="../static/plugin_js/snowfall.jquery.js"></script>
    <script src="../static/plugin_js/snowfall.js"></script>
    <style type="text/css">
        html, body {
            font-family: Menlo, 微软雅黑;
            padding: 0;
            margin: 0;
            height: 100%;
            overflow: hidden;
            background: #ffe url("static/img/snow/snow.jpg");
            background-size: cover;
        }

        h2 {
            text-align: center;
        }

        img {
            position: absolute;
            left: 50%;
            top: 50%;
            display: block;
            transform: translate(-50%, -50%) scale(1);
        }

        textarea {
            font-size: 15px;
            width: 90%;
            height: 200px;
            overflow: auto;
            display: block;
            margin: 20px auto;
            resize: none;
            border: 1px solid black;
        }

        .img_wrap {
            opacity: 0.8;
            background-color: #343434;
            width: 840px;
            height: 524px;
            float: left;
            overflow: hidden;
            position: relative;
        }

        .show_result {
            opacity: 0.8;
            width: 340px;
            height: 524px;
            outline: none;
            float: left;
            background-color: rgb(250, 250, 250);
        }

        .show_result_top {
            font-size: 16px;
            color: rgb(0, 0, 0);
            padding-left: 20px;
            height: 50px;
            border-top: 1px solid #e1e1e1;
            border-bottom: 1px solid #e1e1e1;
            line-height: 50px;
            background-color: rgb(250, 250, 250);
        }

        .show_result_bottom {
            height: 420px;
            font-size: 16px;
            line-height: 2;
            color: rgb(106, 106, 106);
            box-sizing: border-box;
            overflow-y: auto;
            word-wrap: break-word;
            border: none;
            outline: none;
            background-color: rgb(250, 250, 250);
        }

        .ocr_wrap {
            width: 1190px;
            margin: 0 auto;
        }

        .tech-recognition-scan {
            position: absolute;
            z-index: 7;
            top: 0;
            left: 0;
            width: 840px;
            border-bottom: 3px solid #3e88f1;
            animation: scan 1.2s infinite;
            background: linear-gradient(180deg, transparent, #3e88f1);
        }

        @keyframes scan {
            0% {
                height: 0
            }
            to {
                opacity: 0;
                height: 524px
            }
        }

        .image-notice {
            position: absolute;
            bottom: 15px;
            left: 10px;
            margin-top: 10px;
            color: #ccc;
            font-size: 12px;
        }

        .snowfall-flakes {
            animation: sakura 1s linear 0s infinite;
        }

        @keyframes sakura {
            0% {
                transform: rotate3d(0, 0, 0, 0deg);
            }
            25% {
                transform: rotate3d(1, 1, 0, 60deg);
            }
            50% {
                transform: rotate3d(1, 1, 0, 0deg);
            }
            75% {
                transform: rotate3d(1, 0, 0, 60deg);
            }
            100% {
                transform: rotate3d(1, 0, 0, 0deg);
            }
        }
    </style>
</head>
<body>
<h2>图文识别</h2>
<div class="ocr_wrap">
    <div class="img_wrap">
        <div class="tech-recognition-scan" style="display: none;" id="scan"></div>
        <img src="" id="img"/>
        <div class="image-notice">图片文件类型支持PNG、JPG、JPEG、BMP，图片大小不超过2M。</div>
    </div>
<#--可以考虑使用模板引擎做-->
    <div class="show_result">
        <div class="show_result_top">识别结果</div>
        <textarea class="show_result_bottom" id="wordResult"></textarea>
    </div>
</div>
</body>
<script type="text/javascript">
    window.onload = function () {
        var base64;
        //粘贴事件
        document.addEventListener('paste', function (event) {
            if (event.clipboardData || event.originalEvent) {
                var clipboardData = (event.clipboardData || event.originalEvent.clipboardData);
                //如果粘贴的是文字不做处理
                if (clipboardData.types.indexOf("text/plain") != -1
                        || clipboardData.types.indexOf("text/html") != -1
                        || clipboardData.types.indexOf("text/rtf") != -1) {
                    console.error("粘贴内容为文字！");
                    return;
                }
                if (clipboardData.items) {
                    var blob;
                    for (var i = 0; i < clipboardData.items.length; i++) {
                        if (clipboardData.items[i].type.indexOf("image") !== -1) {
                            blob = clipboardData.items[i].getAsFile();
                        }
                    }
                }
                var reader = new FileReader();
                reader.onload = function (evt) {
                    //输出base64编码
                    base64 = evt.target.result;
                    console.log("图片信息：", base64);
                    document.getElementById('img').setAttribute('src', base64);     //图片粘贴后开始执行扫描
                    document.getElementById("scan").style.display = "block";
                    if (base64) {
                        //发送请求到后台
                        $.post("/imagToword", {
                            base64Str: base64
                        }, function (data) {
                            if (data.rtnCode == 0) {
                                if (!data.bean.word) {
                                    alert("未识别到文字！");
                                    document.getElementById("scan").style.display = "none";
                                    return;
                                }
                                document.getElementById("wordResult").value = data.bean.word;
                                document.getElementById("scan").style.display = "none";
                            }
                        })
                    }
                };
                reader.readAsDataURL(blob);
            }
        });

        //雪花飘落
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake1.png",
            flakeCount: 2,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake2.png",
            flakeCount: 3,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake3.png",
            flakeCount: 4,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake4.png",
            flakeCount: 5,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake5.png",
            flakeCount: 6,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
        $("body").snowfall({
            image: "static/img/boy/roses/snowflake6.png",
            flakeCount: 7,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });

        $("body").snowfall({
            image: "static/img/snow/flake.png",
            flakeCount: 10,
            minSpeed: 0.5,
            minSize: 15,
            maxSize: 15,
        });
    }
</script>
</html>