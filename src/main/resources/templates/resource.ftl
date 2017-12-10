<!DOCTYPE html>
<html lang="en">

<head>
    <#assign basePath=springMacroRequestContext.contextPath>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>素材管理</title>
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
            <h4>素材管理</h4>
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
                    <a class="active-menu" href="#"><i class="fa fa-qrcode fa-3x"></i>素材管理</a>
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
            <div class="resource-manage">
                <!-- 素材操作 -->
                <div class="resource-operation">
                    <div class="add-resource">
                        <button class="btn btn-primary" aria-label="image" data-toggle="modal" data-target="#uploadMaterial">
                            <span class="fa fa-plus" aria-hidden="true"></span> 添加素材
                        </button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" aria-label="image">
                            <i class="fa fa-file-image-o" aria-hidden="true"></i> 图片
                        </button>
                        <button type="button" class="btn btn-primary" aria-label="image">
                            <i class="fa fa-file-text" aria-hidden="true"></i> 文档
                        </button>
                    </div>
                    <div class="btn-group">
                        <button type="button" class="btn btn-primary" aria-label="image" data-toggle="modal"
                                data-target="#addAchieve">
                            <i class="fa fa-plus" aria-hidden="true"></i> 新建分类
                        </button>
                        <button type="button" class="btn btn-danger" aria-label="image" data-toggle="modal" data-target="#deleteAchieve">
                            <i class="fa fa-trash-o" aria-hidden="true"></i> 删除
                        </button>
                    </div>
                </div>
                <!-- 素材过滤-->
                <div class="resource-filter">
                    <div class="resource-head">
                        <div class="panel-title">
                            <h4>素材分类</h4>
                        </div>
                    </div>
                    <div class="folder-filter">
                        <ol id="achieve_manage" class="list-inline">
                            <#list achieves as achieve>
                                <li id="${achieve.id}">
                                    <span class="fa fa-folder fa-3x"></span>
                                    <br>
                                    <h6>${achieve.name}</h6>
                                </li>
                            </#list>
                        </ol>
                    </div>
                </div>
                <div class="resource-display">
                    <div class="resource-info">
                        <ol class="list-inline">
                            <li class="display-active">
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                            <li>
                                <img class="image-info" src="img/1.png">
                                <h6>文件夹1文件夹1文件夹1文件夹1</h6>
                            </li>
                        </ol>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade" id="deleteAchieve" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
               aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    删除文件夹
                </h4>
            </div>
            <div class="modal-body">
                <p>确定删除文件夹和文件夹下的内容吗?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">
                    取消
                </button>
                <button type="button" class="btn btn-primary" onclick="delete_achieve()">
                    确定
                </button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->
</div>

    <div class="modal fade" id="uploadMaterial" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                        上传文件
                    </h4>
                </div>
                <div class="modal-body">
                    <input id="fileupload" type="file" name="files[]" data-url="/resource/upload/" multiple>
                    <div id="progress" class="progress">
                        <div class="progress-bar" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 0%;">
                            0%
                        </div>
                    </div>

                    <table id="uploaded-files" class="table">
                        <tr>
                            <th>文件名</th>
                            <th>文件大小</th>
                            <th>文件类型</th>
                        </tr>
                    </table>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        取消
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal -->
    </div>


    <div class="modal fade" id="addAchieve" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title">
                        添加文件夹
                    </h4>
                </div>
                <div class="modal-body">
                    <form id="achieve_form_data">
                        <div class="form-group">
                            <label for="achieve_name">文件夹名</label>
                            <input id="achieve_name" type="text" class="form-control" name="achieve_name">
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <label for="achieve">文件夹属性</label>
                        <select id="achieve" class="form-control" name="achieve_authority">
                            <option value="0">私有</option>
                            <option value="1">共享</option>
                        </select>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        取消
                    </button>
                    <button type="button" class="btn btn-primary" onclick="add_achieve()">
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
<script src="${basePath}/js/jquery.dataTables.js"></script>
<script src="${basePath}/js/dataTables.bootstrap.js"></script>
<script src="${basePath}/js/bootstrap-treeview.js" type="text/javascript"></script>
<script src="${basePath}/js/jquery.iframe-transport.js"></script>
<script src="${basePath}/js/vendor/jquery.ui.widget.js"></script>
<!-- The basic File Upload plugin -->
<script src="${basePath}/js/jquery.fileupload.js"></script>
<!-- The File Upload processing plugin -->
<!-- The File Upload user interface plugin -->
<script src="${basePath}/js/resource.js"></script>

</body>

</html>