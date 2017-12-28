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
               	<!-- /. 用户管理  --> 	
               	 <li>
                        <a href="#"><i class="fa fa-sitemap fa-3x"></i> 权限管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">用户管理</a>
                            </li>
                            <li>
                                <a href="#">角色管理</a>
                            </li>
                            
                        </ul>
                      </li>  
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
<script src="${basePath}/js/template-web.js"></script>

<!-- The File Upload processing plugin -->
<!-- The File Upload user interface plugin -->
<script src="${basePath}/js/resource.js"></script>

</body>

</html>