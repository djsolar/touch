<#-- 取得 应用的绝对根路径 -->
<#assign basePath=request.contextPath>
<#macro head>
    <link rel="shortcut icon" href="${basePath}/favicon.ico">
    <link rel="icon" type="image/gif" href="${basePath}/animated_favicon.gif">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
</#macro>