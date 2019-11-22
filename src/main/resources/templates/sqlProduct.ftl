<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>脚本生成工具</title>
    <link type="text/css" rel="stylesheet" href="../static/css/global.css" />
    <link type="text/css" rel="stylesheet" href="../static/css/index.css" />
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="../static/js/index.js"></script>
</head>
<body>
<a href="/logout" id="logout">注销</a>
<input type="hidden" id="isError" value=${isError} />
<div class="form_wrap">
    <#--<div class="wrap">
        <div class="cube">
            <div class="out_front"><img src="../static/wangxiaochen.jpg" class="pic"></div>
            <div class="out_back"><img src="../static/wangxiaochen.jpg" class="pic"></div>
            <div class="out_left"><img src="../static/wangxiaochen.jpg" class="pic"></div>
            <div class="out_right"><img src="../static/wangxiaochen.jpg" class="pic"></div>
            <div class="out_top"><img src="../static/wangxiaochen.jpg" class="pic"></div>
            <div class="out_bottom"><img src="../static/wangxiaochen.jpg" class="pic">
            </div>
        </div>
    </div>-->
    <h2>脚本生成工具</h2>
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

            <div class="form_ele_wrap" id="isRollback">
                <label for="isRollback">是否回滚脚本</label>
                <select name="isRollback">
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
            </div>

            <div class="form_ele_wrap" id="isBackup">
                <label for="isBackup">是否备份脚本</label>
                <select name="isBackup">
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
            </div>

            <div class="form_ele_wrap">
                <label for="provNm">省份名称</label>
                <button type="button" id="add" style="margin-left: 10px;">add</button>
                <select name="provNm" id="provNm"></select>
            </div>

            <div class="form_ele_wrap">
                <input id="provNmMulti" type="text" name="provNmMulti" style="width: 300px;"  />
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
                <label for="opCount">操作数量</label>
                <input class="form_input" type="text" name="opCount" placeholder="请输入操作数量"/>
            </div>
            <div class="form_ele_wrap">
                <label for="connPhone">联系电话</label>
                <input class="form_input" type="text" name="connPhone" placeholder="请输入联系电话"/>
            </div>
            <#--<div style="text-align: left; font-size: 10px; margin-top: 10px;">
                <input type="checkbox" name="isZip"/>是否生成压缩脚本？
            </div>-->
            <div class="button_wrap">
                <input class="form_input" id="submit" type="button" value="提交" />
            </div>
        </div>
    </form>
</div>
<div class="sql_wrap">
    <h4 style="text-align: center">请输入sql语句</h4>
    <textarea id="sql" name="sql" style="opacity: 0.5"></textarea>
</div>


<#--<div class="sql_wrap sql_wrap_rollback">
    <h4 style="text-align: center">请输入回滚sql语句</h4>
    <textarea id="rollbackSql" name="rollbackSql"></textarea>
</div>-->

</body>
</html>