<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
    <head>
        <!-- 指定字符集 -->
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>注册用户</title>
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>

        <script>
            //$("#bq").html("内容")；
            var register = '${register}';
            if(register == ''){
            }else {
                alert(register);
            }
        </script>
    </head>
    <body>
    <div class="container" style="width: 400px;margin-top: 100px">
       <h3 style="text-align: center;">注册账号</h3>
        <form action="${pageContext.request.contextPath}/register" method="post">
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入你的用户名"/><span
                    id="l_username"></span>
            </div>

            <div class="form-group">
                <label for="password">密码：</label>
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码"/>
            </div>
            <!-- /.box -->
            <div class="form-group" style="text-align: center">
                <input id="btn1" class="btn btn-primary" type="submit" value="点击注册"/>
                <input class="btn btn-default" type="reset" value="重置"/>
            </div>
        </form>
    </div>

    </body>
</html>