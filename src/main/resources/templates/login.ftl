<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="/webjars/jquery/3.2.1/jquery.min.js" type="text/javascript"></script>
    <script src="/webjars/bootstrap/3.3.7/js/bootstrap.min.js" type="text/javascript"></script>
    <link href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/css/login.css" rel="stylesheet">
    <title>登录</title>
</head>
<body>

<div class="login-page">

    <div class="login-header">

    </div>

    <div class="container col-lg-4 col-lg-offset-4">
        <h3 class="text-center header">法院管理系统2</h3>
        <form method="post" action="/login">
            <div class="form-group">
                <label for="username">用户名</label>
                <input id="username" type="text" class="form-control" name="username">
            </div>

            <div class="form-group">
                <label for="password">密码</label>
                <input id="password" type="password" class="form-control" name="password">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-primary login-btn">登录</button>
        </form>
    </div>

    <div class="footer-bottom container">
        <p> Copyright©1999-2017 Twinflag All Rights Reserved.</p>
        <p>京ICP备09078677号-7京公网安备110108001839号</p>
    </div>
</div>
</body>
</html>