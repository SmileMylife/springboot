<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>常用文档查询</title>
    <script crossorigin src="https://unpkg.com/react@16/umd/react.development.js"></script>
    <!-- DOM相关 -->
    <script crossorigin src="https://unpkg.com/react-dom@16/umd/react-dom.development.js"></script>
    <!-- 支持JSX -->
    <script src="https://unpkg.com/babel-standalone@6/babel.min.js"></script>
    <link rel="stylesheet" href="../static/css/antd.css">
    <script src="../static/js/moment.min.js"></script>
    <script type="text/javascript" src="../static/js/antd.js"></script>
    <style type="text/css">
        * {
            padding: 0px;
            margin: 0px;
        }

        h2 {
            margin-top: 30px;
            text-align: center;
        }

        table {
            text-align: center;
        }

        .thead-tr {
            height: 50px;
            background-color: rgb(250, 250, 250);
        }

        .tbody-tr {
            height: 40px;
        }

        .tbody-tr:hover {
            cursor: pointer;
            background-color: #e6f7ff;
        }

        td {
            padding: 10px;
        }
    </style>
    <script type="text/babel" src="../static/js/queryDoc.jsx"></script>
</head>
<body>
<h2>常用文档查询</h2>
<div id="root"></div>
</body>
</html>