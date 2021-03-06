<!DOCTYPE html>
<html lang="en">

<head>
    <#assign basePath=springMacroRequestContext.contextPath>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>节目管理</title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/normalize.css">
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap-treeview.css">
    <link rel="stylesheet" href="${basePath}/css/common.css">
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
            <h4>节目管理</h4>
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
                    <a class="active-menu" href="#"><i class="fa fa-dashboard fa-3x"></i>节目</a>
                </li>
                <li>
                    <a href="/template/getTemplates"><i class="fa fa-desktop fa-3x"></i>模版管理</a>
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
                    <span class="fa fa-plus" aria-hidden="true"></span> 添加节目
                </button>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <div>
                                <table class="table table-striped table-hover" id="dataTables-example"
                                       style="width: 100%">
                                    <thead>
                                    <tr>
                                        <th>编号</th>
                                        <th>节目名称</th>
                                        <th>发布状态</th>
                                        <th>更新时间</th>
                                        <th>创建者名称</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
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
                        选择模板
                    </h4>
                </div>
                <div class="modal-body">
                    <form>
                        <label for="template">选择模板</label>
                        <select id="template" class="form-control">
                            <#list templateMap? keys as key>
                                <option value="${key}">${templateMap[key]}</option>
                            </#list>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消
                    </button>
                    <button id="createProgram" type="button" class="btn btn-primary" onclick="createNewProgram()">
                        确定
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
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $.ajaxSetup({
                    beforeSend: function (xhr) {
                        if (header && token) {
                            xhr.setRequestHeader(header, token);
                        }
                    }
                }
        );
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
                "url": "/program/getProgramData",
                "dataSrc": "aaData"
            },
            "columns": [
                {"data": "id"},
                {"data": "programName"},
                 {
                    "data": "type",
                    render: function (data, type, row) {
                        return data === 1 ? "已发布" : "未发布";
                    }
                },
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
                        console.log(row);
                        return "<div class=\"btn-operation\" programId=\"" + row.id + "\" programZip=\"" + row.zipPath + "\">\n" +
                                "<button type=\"button\" class=\"btn btn-primary btn-sm publish \">发布</button>\n" +
                                "<button type=\"button\" class=\"btn btn-info btn-sm edit \">编辑</button>\n" +
                                "<button type=\"button\" class=\"btn btn-info btn-sm export \">导出</button>\n" +
                                "<button type=\"button\" class=\"btn btn-danger btn-sm delete\">删除</button>\n" +
                                "</div>";
                    }
                }
            ]
        });
        table.on('draw', function () {
            add_operation();
        });
    });

    function createNewProgram() {
        var templateId = $("#template").val();
        console.log(templateId);
        window.location.href = "/program/createProgram/" + templateId;
    }

    function add_operation() {
        $("button.edit").click(function () {
            var id = $(this).parent().attr("programId");
            console.log("id = " + id);
            window.location.href = "/program/editProgram/" + id;
        });

        $("button.publish").click(function () {
            var id = $(this).parent().attr("programId");
            console.log("id = " + id);

        });

        $("button.delete").click(function () {
            var id = $(this).parent().attr("programId");
            console.log("id = " + id);
            $.ajax({
                "url": "/program/deleteProgram/" + id,
                "type": "get",
                "success": function (data) {
                    if (data) {
                        window.location.reload();
                    }
                }
            });
        });

        $("button.export").click(function () {
            window.location.href = $(this).parent().attr("programZip");
        });
    }
</script>
</body>
</html>