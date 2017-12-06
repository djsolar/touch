<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>终端管理</title>
    <link rel="stylesheet" type="text/css" href="css/normalize.css">
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
                <h4>终端管理</h4>
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
                        <a href="/template/getTemplates"><i class="fa fa-desktop fa-3x"></i>模版管理</a>
                    </li>
                    <li>
                        <a href="/resource/resourceManage"><i class="fa fa-qrcode fa-3x"></i>素材管理</a>
                    </li>
                    <li>
                        <a href="/terminal/terminalStatus"><i class="fa fa-bar-chart-o fa-3x"></i>终端监控</a>
                    </li>
                    <li>
                        <a class="active-menu" href="#"><i class="fa fa-table fa-3x"></i>终端管理</a>
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
                <div id="terminal-status" class="row">
                    <div class="col-md-2 terminal-display" style="border-right: 1px solid #fff;">
                        <div class="terminal-head">
                            <h4>终端树</h4>
                        </div>
                        <div class="terminal-operation">
                            <div class="btn-group">
                                <button class="btn btn-info"><i class="fa fa-plus"></i></button>
                                <button class="btn btn-info"><i class="fa fa-edit"></i></button>
                                <button class="btn btn-danger"><i class="fa fa-trash"></i></button>
                            </div>
                        </div>
                        <div id="terminal-group"></div>
                    </div>
                    <div class="col-md-10 terminal-display">
                        <div class="terminal-head">
                            <h4>终端列表</h4>
                        </div>
                        <div class="terminal-operation">
                            <div>
                                <button class="btn btn-info">
                                    <i class="fa fa-plus"></i> 添加
                                </button>
                                <button class="btn btn-info">
                                    <i class="fa fa-edit"></i> 编辑
                                </button>
                                <button class="btn btn-info">
                                    <i class="fa fa-trash"></i> 删除
                                </button>
                                <button class="btn btn-info">
                                    <i class="fa fa-search-plus"></i> 查看待添加
                                </button>
                            </div>
                        </div>
                        <div id="terminal-list" style="margin-top: 5px; height: 500px">
                            <div>
                                <table class="table table-striped table-hover" id="dataTables-example" style="width: 100%">
                                    <thead>
                                        <tr>
                                            <th>编号</th>
                                            <th>终端名称</th>
                                            <th>所属终端组</th>
                                            <th>IP地址</th>
                                            <th>终端标示号</th>
                                            <th>创建日期</th>
                                            <th>更新日期</th>
                                            <th>创建者名称</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr class="odd gradeX">
                                            <td class="col-md-1">Trident</td>
                                            <td class="col-md-2">Internet Explorer 4.0</td>
                                            <td>Win 95+</td>
                                            <td class="center">4</td>
                                            <td class="center">X</td>
                                            <td class="center">X</td>
                                            <td class="center">X</td>
                                            <td class="center">X</td>
                                        </tr>
                                    </tbody>
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
                    选择模板
                </h4>
                    </div>
                    <div class="modal-body">
                        <form>
                            <label for="name">选择模板</label>
                            <select class="form-control">
                                <option>模板1</option>
                                <option>模板2</option>
                                <option>模板3</option>
                                <option>模板4</option>
                                <option>模板5</option>
                            </select>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消
                        </button>
                        <button type="button" class="btn btn-primary">
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
    <script src="../js/bootstrap-treeview.js"></script>
    <script>
    $(document).ready(function() {
        $('#dataTables-example').dataTable({
            "autoWidth": false,
            "lengthChange": false,
            "info": false,
            "ordering": false,
            "scollX": false,
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索 ",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            }
        });
        var defaultData = [{
                text: 'Parent 1',
                href: '#parent1',
                tags: ['4'],
                nodes: [{
                        text: 'Child 1',
                        href: '#child1',
                        tags: ['2'],
                        nodes: [{
                                text: 'Grandchild 1',
                                href: '#grandchild1',
                                tags: ['0']
                            },
                            {
                                text: 'Grandchild 2',
                                href: '#grandchild2',
                                tags: ['0']
                            }
                        ]
                    },
                    {
                        text: 'Child 2',
                        href: '#child2',
                        tags: ['0']
                    }
                ]
            },
            {
                text: 'Parent 2',
                href: '#parent2',
                tags: ['0']
            },
            {
                text: 'Parent 3',
                href: '#parent3',
                tags: ['01111']
            },
            {
                text: 'Parent 4',
                href: '#parent4',
                tags: ['0']
            },
            {
                text: 'Parent 5',
                href: '#parent5',
                tags: ['0']
            }
        ];

        $("#terminal-group").treeview({
            data: defaultData,
            color: "#4D4D4D"
        });
    });
    </script>

</html>