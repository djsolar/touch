<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>节目</title>
    <!-- Bootstrap css -->
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.css">
    <link rel="stylesheet" href="/css/common.css">
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
            <a class="navbar-brand" href="index.html">法院管理系统</a>
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
                    <a href="/templateManage"><i class="fa fa-desktop fa-3x"></i>模版管理</a>
                </li>
                <li>
                    <a href="/resourceManage"><i class="fa fa-qrcode fa-3x"></i>素材管理</a>
                </li>
                <li>
                    <a href="/terminalStatus"><i class="fa fa-bar-chart-o fa-3x"></i>终端状态</a>
                </li>
                <li>
                    <a href="/terminalManage"><i class="fa fa-table fa-3x"></i>终端管理</a>
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
            <!-- /. ROW  -->
        </div>
        <!-- /. PAGE INNER  -->
    </div>
    <!-- /. PAGE WRAPPER  -->
</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY SCRIPTS -->
<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<!-- BOOTSTRAP SCRIPTS -->
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<#--<script src="assets/js/jquery.metisMenu.js"></script>-->
<!-- MORRIS CHART SCRIPTS -->
<!-- CUSTOM SCRIPTS -->
<#--<script src="assets/js/custom.js"></script>-->
</body>
</html>