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
    <div id="wrapper-head">

    </div>
    <div id="wrapper-inner" class="row">
        <div id="wrapper-menu-tree" class="col-md-3 col-md-offset-1">

        </div>

        <div id="wrapper-content" class="col-md-7">
        </div>
    </div>
</div>

<script id="level-one-template" type="text/html">
    <div id="level-one" class="row">
        <div class="display-img col-md-3 col-md-offset-2">
            <img src="{{iconurl_a}}">
            <h3>未选中图片</h3>
        </div>

        <div class="display-img col-md-3 col-md-offset-2">
            <img src="{{iconurl_b}}">
            <h3>选中图片</h3>
        </div>
    </div>
</script>

<script src="/webjars/jquery/3.2.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/3.3.7/js/bootstrap.js"></script>
<script src="${basePath}/js/jquery.dataTables.js"></script>
<script src="${basePath}/js/dataTables.bootstrap.js"></script>
<script src="${basePath}/js/bootstrap-treeview.js"></script>
<script>

    function replace_html_tags(str, reallyDo, replaceWith) {
        var e = new RegExp(reallyDo, "g");
        return str.replace(e, replaceWith);
    }
    
    function display_selete_node(data) {
        console.log(data);
        display_level_one(data)
    }
    
    
    function display_level_one(data) {
        var levelOneHtml = $("#level-one-template").html();
        levelOneHtml = replace_html_tags(levelOneHtml, "{{iconurl_a}}", data.iconurl_a);
        levelOneHtml = replace_html_tags(levelOneHtml, "{{iconurl_b}}", data.iconurl_b);
        $("#wrapper-content").empty();
        $("#wrapper-content").append(levelOneHtml);
    }

    var data = ${programData}
            $(function () {
                var tree = $("#wrapper-menu-tree").treeview({
                    data: data,
                    color: "#4D4D4D",
                    levels: 1,
                    onNodeSelected: function (event, data) {
                        display_selete_node(data)

                    }
                });
            });
</script>
</body>
</html>