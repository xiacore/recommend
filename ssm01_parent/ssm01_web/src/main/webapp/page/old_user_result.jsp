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
        <title>老用户推荐结果</title>
        <link href="${pageContext.request.contextPath}/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <script src="${pageContext.request.contextPath}/plugins/jQuery/jquery-2.2.3.min.js"></script>
        <script src="${pageContext.request.contextPath}/plugins/bootstrap/js/bootstrap.min.js"></script>
    </head>
    <body>
    <div style="margin-top: 100px"></div>
    <div class="container" style="margin: auto auto ">
       <h3 style="text-align: center;">推荐结果</h3>
    </div>
    <div id="image" style="width: 200px;height: 200px;margin:auto auto">
        <img src="" class="img-responsive img-rounded" id="picture" alt="Responsive image">
        <h4 style="text-align: center;">${result_tj}</h4>
    </div>

<script>

    //$('#image').style.background="url(${pageContext.request.contextPath}/img/lq"+1+".png)   center center no-repeat";
    //下标从0开始
     var arr = ["吉他","架子鼓","口琴","钢琴","二胡","古筝","琵琶","萨克斯","手风琴","漏洞"];
     var result = '${result_tj}';//使用单引号
     var index =0;//下标
     for(var i=0;i<arr.length;i++){
         if(arr[i]==result){
             index = i;
         }
     }
    $('#picture').attr('src','${pageContext.request.contextPath}/img/lq'+index+'.png');
</script>
    </body>
</html>