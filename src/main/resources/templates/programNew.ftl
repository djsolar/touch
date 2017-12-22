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
    <link rel="stylesheet" href="${basePath}/css/normalize.css">
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
    <div id="wrapper-menu" class="row">
        <div class="col-md-3 col-md-offset-1">
            <div class="btn-group" role="group" aria-label="...">
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        节&nbsp;目
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#">保存</a></li>
                        <li><a href="#">发布</a></li>
                        <li><a href="#">返回</a></li>
                    </ul>
                </div>

                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                        添&nbsp;加
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" id="add_sibling_node">菜单</a></li>
                        <li><a href="#" id="add_child_node">子菜单</a></li>
                    </ul>
                </div>
            <#-- <div class="btn-group" role="group">
                 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown"
                         aria-haspopup="true" aria-expanded="false">
                     移&nbsp;动
                     <span class="caret"></span>
                 </button>
                 <ul class="dropdown-menu">
                     <li><a href="#" id="up_node">上移</a></li>
                     <li><a href="#" id="down_node">下移</a></li>
                     <li><a href="#" id="up_header">头部</a></li>
                     <li><a href="#" id="down_tail">尾部</a></li>
                 </ul>
             </div>-->
                <button id="delete_select_node" type="button" class="btn btn-danger"><span class="fa fa-trash"></span>
                    删除
                </button>
            </div>
        </div>

        <div class="col-md-7">
            <div class="btn-group" role="group" aria-label="...">
                <div class="btn-group" role="group" aria-label="..." style="margin-left: 20px; margin-right: 20px;">
                    <button id="operation_save" type="button" class="btn btn-primary"><span class="fa fa-save"></span>
                        保存
                    </button>
                    <button id="operation_add" type="button" class="btn btn-primary"><span class="fa fa-plus"></span> 添加
                    </button>
                    <button id="operation_up" type="button" class="btn btn-primary"><span
                            class="fa fa-long-arrow-up"></span> 上移
                    </button>
                    <button id="operation_down" type="button" class="btn btn-primary"><span
                            class="fa fa-long-arrow-down"></span> 下移
                    </button>
                    <button id="operation_look" type="button" class="btn btn-primary"><span class="fa fa-eye"></span> 查看
                    </button>
                    <button id="operation_edit" type="button" class="btn btn-primary"><span class="fa fa-edit"></span>
                        编辑
                    </button>
                    <button id="operation_delete" type="button" class="btn btn-danger"><span class="fa fa-trash"></span>
                        删除
                    </button>
                </div>
            </div>

        </div>
    </div>
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
        {{if data.normalMaterial}}
        <div class="display-img col-md-3 col-md-offset-2" mediaType="1" materialId="{{data.normalMaterial.id}}"
             macName="{{data.normalMaterial.md5Name}}" originName="{{data.normalMaterial.originName}}">
            <img src="/{{data.normalMaterial.md5Name}}" alt="{{data.normalMaterial.originName}}">
            <h3>未选中图片</h3>
        </div>
        {{else}}
        <div class="display-img col-md-3 col-md-offset-2" mediaType="1" materialId="null" macName="null" originName="null">
            <img src="/img/1.png" alt="1.png">
            <h3>未选中图片</h3>
        </div>
        {{/if}}
        {{if data.selectedMaterial}}
        <div class="display-img col-md-3 col-md-offset-2" mediaType="1" materialId="{{data.selectedMaterial.id}}"
             macName="{{data.selectedMaterial.md5Name}}" originName="{{data.selectedMaterial.originName}}">
            <img src="/{{data.selectedMaterial.md5Name}}" alt="{{data.selectedMaterial.originName}}">
            <h3>选中图片</h3>
        </div>
        {{else}}
        <div class="display-img col-md-3 col-md-offset-2" mediaType="1" materialId="null"
             macName="null" originName="null">
            <img src="/img/1.png" alt="1.png">
            <h3>选中图片</h3>
        </div>
        {{/if}}

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
            {{if data.materials}}
            {{each data.materials material}}
            {{if data.mediaType == 1}}
            <li class="content-img" mediaType="{{data.mediaType}}" materialId="{{material.id}}"
                macName="{{material.md5Name}}" originName="{{material.originName}}">
                <div>
                    <img src="/{{material.md5Name}}" alt="{{material.originName}}">
                </div>
            </li>
            {{else if data.mediaType == 2}}
            <li class="content-txt" mediaType="{{data.mediaType}}" materialId="{{material.id}}"
                macName="{{material.md5Name}}" originName="{{material.originName}}">
                <label>{{material.originName}}</label>
            </li>
            {{/if}}
            {{/each}}
            {{/if}}
        </ul>
    </div>
</script>

<script id="content-item" type="text/html">
    <li class="content-img" mediaType="{{data.mediaType}}" materialId="{{material.id}}"
    macName="{{material.md5Name}}" originName="{{material.originName}}">
            <div>
            <img src="/{{material.md5Name}}" alt="{{material.originName}}">
            </div>
            </li>
    {{else if data.mediaType == 2}}
    <li class="content-txt" mediaType="{{data.mediaType}}" materialId="{{material.id}}"
    macName="{{material.md5Name}}" originName="{{material.originName}}">
            <label>{{material.originName}}</label>
    </li>
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
        {{if data.urlMaterial}}
        <label style="margin-top: 20px;">文件路径 </label>
        {{if data.mediaType == 1}}
        <div class="content-level-two content" mediaType="{{data.mediaType}}" materialId="{{data.urlMaterial.id}}"
             macName="{{data.urlMaterial.md5Name}}" originName="{{data.urlMaterial.originName}}">
            <img src="/{{data.urlMaterial.md5Name}}" alt="{{data.urlMaterial.originName}}">
        </div>
        {{else if data.mediaType == 2}}
        <div class="content" mediaType="{{data.mediaType}}" materialId="{{data.urlMaterial.id}}"
             macName="{{data.urlMaterial.md5Name}}" originName="{{data.urlMaterial.originName}}">
            <label>{{data.urlMaterial.originName}}</label>
        </div>
        {{/if}}
        {{/if}}
    </div>
</script>

<script id="material-list" type="text/html">
    {{if material }}
    <div class="row material-item">
        <div class="col-sm-6 col-md-4">
            <div>
                {{if material.type == 1}}
                <img class="material-select" src="/{{material.macName}}" mediaType="1" materialId="{{material.id}}"
                     macName="{{material.macName}}" originName="{{material.originName}}">
                {{else}}
                <img class="material-select" src="/img/txt.png" mediaType="2" materialId="{{material.id}}"
                     macName="{{material.macName}}" originName="{{material.originName}}">
                {{/if}}
                <div class="caption">
                    <h4>{{material.originName}}</h4>
                </div>
            </div>
        </div>
    </div>
    {{/if}}
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
</script>
</body>
</html>