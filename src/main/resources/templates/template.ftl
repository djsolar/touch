<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>模板管理</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap-treeview.css">
    <link rel="stylesheet" href="../css/common.css">
</head>

<body>
<div id="wrapper">
    <nav class="navbar navbar-default navbar-cls-top " role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.ftl">法院管理系统</a>
        </div>

        <div class="page-info">
            <h4>模板管理</h4>
        </div>

        <div style="color: white;padding: 15px 50px 5px 50px;float: right;font-size: 16px;">
            上次登录 : 2014年5月24日 &nbsp;
            <a href="/logout" class="btn btn-danger square-btn-adjust">退出</a>
        </div>
    </nav>
    <!-- /. NAV TOP  -->
    <nav class="navbar-default navbar-side" role="navigation">
        <div class="sidebar-collapse">
            <ul class="nav" id="main-menu">
                <li style="height: 60px;"></li>
                <li>
                    <a href="/program/getPrograms"><i class="fa fa-dashboard fa-3x"></i>节目</a>
                </li>
                <li>
                    <a class="active-menu" href="#"><i class="fa fa-desktop fa-3x"></i>模版管理</a>
                </li>
                <li>
                    <a href="/resource/resourceManage"><i class="fa fa-qrcode fa-3x"></i>素材管理</a>
                </li>
                <li>
                    <a href="/terminal/terminalStatus"><i class="fa fa-bar-chart-o fa-3x"></i>终端监控</a>
                </li>
                <li>
                    <a href="/terminal/terminalManage"><i class="fa fa-table fa-3x"></i>终端管理</a>
                </li>
                <li>
                    <a href="/setting"><i class="fa fa-edit fa-3x"></i>设置</a>
                </li>
            </ul>
        </div>
    </nav>
    <!-- /. NAV SIDE  -->
    <div id="page-wrapper">
        <div id="page-inner">
            <div class="form-inline">
                <button class="btn btn-md btn-primary" data-toggle="modal" data-target="#myModal">
                    <span class="fa fa-plus" aria-hidden="true"></span> 添加模板
                </button>
            </div>
            <div class="hr"></div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel-body">
                        <div>
                            <table class="table table-striped table-hover" id="dataTables-example">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>模板名称</th>
                                    <th>更新时间</th>
                                    <th>创建者名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <!--<tbody>

                                </tbody>-->
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        上传模板
                    </h4>
                </div>
                <div class="modal-body">
                    <form enctype="multipart/form-data" id="uploadForm" action="/template/upload" method="post">
                        <div class="row">
                            <div class="col-md-6" style="text-align: center;">
                                <input type="file" name="file" id="templateFile">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </div>

                            <div class="col-md-1">
                                <button type="submit" class="btn btn-primary btn-xs">上传</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="../js/jquery.dataTables.js"></script>
<script src="../js/dataTables.bootstrap.js"></script>
<script src="../js/bootstrap-treeview.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
        var table;
        Date.prototype.Format = function (fmt) { //author: meizz
            var o = {
                "M+": this.getMonth() + 1,
                //月份
                "d+": this.getDate(),
                //日
                "h+": this.getHours(),
                //小时
                "m+": this.getMinutes(),
                //分
                "s+": this.getSeconds(),
                //秒
                "q+": Math.floor((this.getMonth() + 3) / 3),
                //季度
                "S": this.getMilliseconds() //毫秒
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }

        table = $('#dataTables-example').DataTable({
            "pagingType": "simple_numbers",//设置分页控件的模式
            searching: true,//屏蔽datatales的查询框
            aLengthMenu: [10],//设置一页展示10条记录
            "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
            info: false,
            "oLanguage": {  //对表格国际化
                "sLengthMenu": "每页显示 _MENU_条",
                "sZeroRecords": "没有找到符合条件的数据",
                //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
                "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                "sInfoEmpty": "木有记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索：",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"

                }
            },
            order: [[0, "desc"]],
            "processing": true,
            "serverSide": true,
            "ajax": {
                "url": "/template/getTemplateData",
                "dataSrc": "aaData"
            },
            "columns": [
                {"data": "id"},
                {"data": "programName"},
                {
                    "data": "updateTime",
                    render: function (data, type, row) {
                        return (new Date(data)).Format("yyyy-MM-dd hh:mm:ss");
                    }
                },
                {
                    "data": "createUser",
                    render: function (data, type, row) {
                        return data.username;
                    }
                }, {
                    "data": null,
                    render: function (data, type, row) {
                        var html = "<div class=\"btn-operation\">\n" +
                            "                            <button id=\"delete\" type=\"button\" class=\"btn btn-danger btn-sm\">删除</button>\n" +
                            "                            </div>";
                        return html;
                    }
                }
            ]
        });

        $("#dataTables-example tbody").on("click", 'button#delete', function () {
            var data = $('#dataTables-example').DataTable().row($(this).parents('tr')).data();
            $.ajax({
                url: "/template/delete",
                data: {"id": data.id},
                success: function (data) {
                    window.location.reload();
                }
            })
        });
    });

</script>
</body>
</html>