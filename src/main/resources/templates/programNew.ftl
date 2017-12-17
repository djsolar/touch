<!DOCTYPE html>
<html lang="en">
<head>
<#assign basePath=springMacroRequestContext.contextPath>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/font-awesome.css">
    <link rel="stylesheet" type="text/css" href="${basePath}/css/bootstrap-treeview.css">
    <link rel="stylesheet" href="${basePath}/css/common.css">
</head>
<body>
<div id="wrapper-outer">
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
<#--<div id="wrapper-head">
    <button class="btn btn-primary ">返回</button>
    <button class="btn btn-primary">保存</button>
</div>-->
    <div id="wrapper-inner" class="row">
        <div id="wrapper-menu-tree" class="col-md-3 col-md-offset-1">

        </div>

        <div id="wrapper-content" class="col-md-7">

        </div>
    </div>
</div>

<div class="modal fade" id="selectMaterial" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    编辑素材
                </h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
                    <div class="material_menu">

                    </div>
                    <div class="material-container">
                        <table class="table" id="materialDataTables">

                        </table>
                    </div>
                </div>
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

<script id="level-one-template" type="text/html">
    <div id="level-one" class="row">
        <div class="display-img col-md-3 col-md-offset-2">
            <img src="{{data.normalPic}}">
            <h3>未选中图片</h3>
        </div>

        <div class="display-img col-md-3 col-md-offset-2">
            <img src="{{data.selectedPic}}" onclick="add_level_one_click()">
            <h3>选中图片</h3>
        </div>
    </div>
</script>

<script id="content" type="text/html">
    <div id="content">
        {{if data.label }}
        <div class="form-group">
            <label for="content-label">标签</label>
            <input id="content-label" class="form-control" type="text" value="{{data.label}}">
        </div>
        {{/if}}

        {{if data.title }}
        <div class="form-group">
            <label for="content-title">标题</label>
            <input id="content-title" class="form-control" type="text" value="{{data.title}}">
        </div>
        {{/if}}

        <ul class="list-group" style="list-style: none">
            {{each data.paths value}}
            {{if data.type == 1}}
            <li class="content-img">
                <div>
                    <img src="{{value}}" alt="{{value}}">
                </div>
            </li>
            {{else if data.type == 2}}
            <li class="content-txt">
                <label>{{value}}</label>
                <button class="btn btn-success">查看</button>
                <button class="btn btn-primary">编辑</button>
            </li>
            {{/if}}
            {{/each}}
        </ul>
    </div>
</script>

<script id="level-two-template" type="text/html">
    <div id="level-two">
        <div class="form-group">
            <label for="level-two-label">标签</label>
            <input id="level-two-label" class="form-control" type="text" value="{{data.label}}">
        </div>

        {{if data.title }}
        <div class="form-group">
            <label for="level-two-title">标题</label>
            <input id="level-two-title" class="form-control" type="text" value="{{data.title}}">
        </div>
        {{/if}}
        {{if data.url }}
        <label style="margin-top: 20px;">文件路径 </label>
        <p>{{data.url}}
            <button class="btn btn-success">查看</button>
            <button class="btn btn-primary">编辑</button>
        </p>
        {{/if}}
    </div>
</script>

<script id="material-list" type="text/html">
    <#--<div class="material-item">
        <img class="material-select" src="/{{material.macName}}">
        <h4>{{material.originName}}</h4>
    </div>-->
    <div class="row material-item">
        <div class="col-sm-6 col-md-4">
            <div>
                <img class="material-select" src="/{{macName}}">
                <div class="caption">
                    <h4>{{macName}}</h4>
                </div>
            </div>
        </div>
</script>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="${basePath}/js/jquery.dataTables.js"></script>
<script src="${basePath}/js/dataTables.bootstrap.js"></script>
<script src="${basePath}/js/bootstrap-treeview.js"></script>
<script src="${basePath}/js/template-web.js"></script>
<script src="${basePath}/js/program.js"></script>
<script>
    var data = ${programData};
    $(function () {
        var tree = $("#wrapper-menu-tree").treeview({
            data: data,
            color: "#4D4D4D",
            levels: 1,
            onNodeSelected: function (event, data) {
                display_selected_node(data);
            }
        });
        material_select();
    });
</script>
</body>
</html>