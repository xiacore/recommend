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
        <script>
           // $(function () {
                //用户名检验
                //   $("#username").blur(function () {
                //        var u_data = {"username":$("#username").val()};
                //        var data = $("#username").val();
                //        var str = /^\w{1,12}$/;
                //        var b = str.test(data);
                //        if(b){
                //            $.get("/check_username_Servlet",u_data,function (str) {
                //                $("#l_username").html(str.msg);
                //            },"json")
                //        }else {
                //            $("#l_username").html("职业输入不符合规则,请重新输入");
                //        }
                //   });
             //
           $(function () {
               $('#btn1').on('click', function () {
                   var tr = $("#tb tr"); // 获取table中每一行内容
                   var result = []; // 数组
                   for (var i = 0; i < tr.length; i++) {// 遍历表格中每一行的内容
                       var tds = $(tr[i]).find("td");
                       if (tds.length > 0) {
                           // var a={"instrument":$(tds[0]).find("option:selected").text(), "rate" : $(tds[1]).find("option:selected").val()};
                           result.push(
                               {
                                   "instrument": $(tds[0]).find("option:selected").val(),
                                   "rate": $(tds[1]).find("option:selected").val()
                               }
                           );
                       }
                   }
                   var jsonData = { // json数据
                       "item_charaList": result
                   };
                   $.ajax({
                       type: "post",
                       url: "${pageContext.request.contextPath}/item_Chara",
                       contentType: "application/json;charset=UTF-8",
                       data: JSON.stringify(jsonData),// 将json数据转化为字符串
                       success: function (data) {
                           alert("数据上传成功，点击开始推荐");
                       }
                   })
               });
           });


        </script>
    </head>
    <body>
    <div class="container" style="width: 400px;">
       <h3 style="text-align: center;">个人信息</h3>
        <form action="${pageContext.request.contextPath}/recommend" method="post">
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
            <div class="box">
                <div class="box-header">
                    <div class="btn-group" style="height: 30px;width: 100px;text-align:center;margin-top: 10px">
                        <button type="button" id="add_btn" style="height: 30px;width: 100px" class="btn btn-success"
                                title="新建">新增乐器选项
                        </button>
                    </div>
                </div>
                <!-- /.box-header -->
                <div class="box-body no-padding">
                    <table class="table table-condensed" id="tb">
                        <tr>
                            <th style="width: 120px;">乐器种类</th>
                            <th style="width: 120px;">喜好度</th>
                            <th style="width: 50px">删除</th>
                        </tr>
                        <tbody id="show_tbody">
                        <tr>
                            <td style="width: 120px;">
                                <div class="form-group" style="float: left">
                                    <select class="form-control select2" id="ID" style="width: 120px;">
                                        <option name="instrument" value="0" selected="selected">吉他</option>
                                        <option name="instrument" value="1">架子鼓</option>
                                        <option name="instrument" value="2">口琴</option>
                                        <option name="instrument" value="3">钢琴</option>
                                        <option name="instrument" value="4">二胡</option>
                                        <option name="instrument" value="5">古筝</option>
                                        <option name="instrument" value="6">琵琶</option>
                                        <option name="instrument" value="7">萨克斯</option>
                                        <option name="instrument" value="8">手风琴</option>
                                    </select>
                                </div>
                            </td>
                            <td style="width: 120px;">
                                <div class="form-group" style="float: left">
                                    <select class="form-control select2" id="ID2" style="width: 120px;">
                                        <option name="rate" value="5" selected="selected">非常喜欢</option>
                                        <option name="rate" value="4">喜欢</option>
                                        <option name="rate" value="3">一般</option>
                                        <option name="rate" value="2">不喜欢</option>
                                        <option name="rate" value="1">非常不喜欢</option>
                                    </select>
                                </div>
                            </td>
                            <td style="width: 50px;">
                                <button type="button" id="del_btn" style="height: 30px;width: 50px"
                                        class="btn btn-default" title="新建">删除
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <!-- /.box-body -->
            </div>
            <!-- /.box -->

            <div class="form-group" style="text-align: center">
                <input id="btn1" class="btn btn-info" type="button" value="上传数据"/>
                <input  class="btn btn-primary" type="submit" value="点击推荐"/>
                <input class="btn btn-default" type="reset" value="重置"/>
            </div>
        </form>
    </div>

    <script>
        // var ID = $("#ID option:selected").text();//获取value值
        // alert(ID);
        //
        // var ID2 = $("#ID2 option:selected").val();//获取text值
        // alert(ID2);

        var tb = $("#tb");
        //添加
        $("#add_btn").click(function () {
            var hideTr = $("#show_tbody", tb).children().first();
            var newTr = hideTr.clone().show();
            $("#show_tbody", tb).append(newTr);
        });
        //删除
        $(document).on("click", "#del_btn", function () {
            $(this).parents("tr").remove()
        });

        //获取

    </script>
    </body>
</html>