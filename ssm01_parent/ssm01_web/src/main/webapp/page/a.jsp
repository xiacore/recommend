
<html>

<head><title>测试数据</title></head>
<style type="text/css">
    table, td{
        font:100% '微软雅黑';
    }
    table{width:80%;border-collapse:collapse; margin:0 0 0 100px}
    th, td{text-align:center;border:1px solid #fff;}
    th{background:#328aa4}
    td{background:#e5f1f4;}
</style>
<script type="text/javascript" src="jquery-1.8.0.js"></script>
<script type="text/javascript" src="jq.js"></script>
<body>
<a href="#" style ="margin:100px" id="add_btn">新增</a>
<input id="Ktext" type="text"> <a href="#" id="search_btn">  查询</a>
<a href="#" style ="margin:100px" id="back_btn">返回</a>
<br/>
            <div class="btn-group" style="width: 370px">
                    <div class="form-group" style="float: left">
                            <label>乐器种类</label>
                            <select class="form-control select2" style="width: 120px;">
                                    <option selected="selected">钢琴</option>
                                    <option>吉他</option>
                                    <option>小提琴</option>
                                    <option>长笛</option>
                                    <option>架子鼓</option>
                                </select>
                        </div>
                    <div style="width: 20px;height:20px;float: left"></div>
                    <div class="form-group" style="float: left">
                            <label>喜好度</label>
                            <select class="form-control select2" style="width: 120px;">
                                    <option selected="selected">非常喜欢</option>
                                    <option>喜欢</option>
                                    <option>一般</option>
                                    <option>不喜欢</option>
                                    <option>非常不喜欢</option>
                                </select>
                        </div>
                    <div class="btn-group" style="height: 30px;width: 50px;float: right;text-align:center;margin-top: 26px" >
                            <button type="button" style="height: 30px;width: 50px" class="btn btn-primary" title="新建" ng-click="()" >新增</button>
                        </div>
                </div>
</body>
</html>