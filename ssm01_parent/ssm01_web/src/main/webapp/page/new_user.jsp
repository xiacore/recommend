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
        <title>用户信息</title>
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div class="container" style="width: 400px;margin-top: 100px">
       <h3 style="text-align: center;">个人信息</h3>
        <form action="${pageContext.request.contextPath}/new_recommend" method="post">
            <div class="form-group">
                <label for="username">职业</label>
                <input type="text" class="form-control" id="username" name="occupation" placeholder="请输入你的职业(英文书写)"/><span
                    id="l_username"></span>
            </div>
            <div class="form-group">
                <label>性别：</label>
                <input type="radio" name="sex" value="1"/>男
                <input type="radio" name="sex" value="0"/>女
            </div>
            <div class="form-group">
                <label for="age">年龄：</label>
                <input type="text" class="form-control" id="age" name="age" placeholder="请输入你的年龄"/>
            </div>

            <div class="btn-group" style="width: 370px">
                <div class="form-group" style="float: left">
                    <label>住址</label>
                    <select name="address" class="form-control" style="width: 180px;">
                        <option  value="0" selected="selected">北京市</option>
                        <option  value="1">天津市</option>
                        <option  value="2">陕西省</option>
                        <option  value="3">山西省</option>
                        <option  value="4">河南省</option>
                        <option  value="5">湖北省</option>
                        <option  value="6">湖南省</option>
                        <option  value="7">山东省</option>
                        <option  value="8">上海市</option>
                        <option  value="9">浙江省</option>
                        <option  value="10">安徽省</option>
                        <option  value="11">福建省</option>
                        <option  value="12">江西省</option>
                    </select>
                </div>
                <div style="width: 10px;height:20px;float: left"></div>
                <div class="form-group" style="float: right">
                    <label>喜欢的音乐类型</label>
                    <select name="music_type" class="form-control select2" style="width: 180px;">
                        <option  value="0" selected="selected">乡村</option>
                        <option  value="1">古典</option>
                        <option  value="2">摇滚</option>
                        <option  value="3">浪漫</option>
                        <option  value="4">蓝调</option>
                        <option  value="5">民谣</option>
                        <option  value="6">爵士</option>
                        <option  value="7">流行</option>
                    </select>
                </div>
            </div>
            <!-- /.box -->
            <div class="form-group" style="text-align: center">
                <input id="btn1" class="btn btn-primary" type="submit" value="点击推荐"/>
                <input class="btn btn-default" type="reset" value="重置"/>
            </div>
        </form>
    </div>

    </body>
</html>