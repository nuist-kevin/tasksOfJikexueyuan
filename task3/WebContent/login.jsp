<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html class="bootstrap-admin-vertical-centered">
<head>
    <title>登录</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" media="screen" href="css/bootstrap.min.css">
    <link rel="stylesheet" media="screen" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" media="screen" href="css/bootstrap-admin-theme.css">

    <style type="text/css">
        .alert {
            margin: 0 auto 20px;
        }
    </style>
</head>

<body class="bootstrap-admin-without-padding">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                    <%
                        String errMsg = (String) request.getAttribute("errMsg");
                        if( errMsg != null) {
                    %>
                    <div class="alert alert-info">
                        <a class="close" data-dismiss="alert">&times;</a> ${errMsg}
                    </div>
                    <%
                        }
                    %>
                        <form method="post" action="login" class="bootstrap-admin-login-form">
                            <h1>登录</h1>
                            <div class="form-group">
                                <input class="form-control" required type="text" name="username" placeholder="请输入任意用户名登录">
                            </div>
                            <div class="form-group">
                                <input class="form-control" required type="password" name="password" placeholder="请输入任意密码">
                            </div>
                            <button class="btn btn-lg btn-primary" type="submit">提交</button>
                        </form>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="js/jquery-3.0.0.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(function() {
            $('input[name="username"]').focus();
            var alert = $('.alert');
            var formWidth = $('.bootstrap-admin-login-form').innerWidth();
            var alertPadding = parseInt($('.alert').css('padding'));

            if (isNaN(alertPadding)) {
                alertPadding = parseInt($(alert).css('padding-left'));
            }

            $('.alert').width(formWidth - 2 * alertPadding);
            $('.close').click(function() {
                alert.remove();
            })
        });
    </script>
</body>

</html>