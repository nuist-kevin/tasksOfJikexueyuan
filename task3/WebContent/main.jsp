<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <title>任务三</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Bootstrap -->
        <link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">

        <!-- Bootstrap Admin Theme -->
        <link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/css/bootstrap-admin-theme.css">
        <link rel="stylesheet" media="screen" href="<%=request.getContextPath()%>/css/bootstrap-admin-theme-change-size.css">
        
    </head>
    <body class="bootstrap-admin-with-small-navbar">
    <!-- small navbar -->
    <nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar-sm" role="navigation">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="collapse navbar-collapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li>
                                <a href="logout">退出登录 <i class="glyphicon glyphicon-user"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
        <!-- main / large navbar -->
        <nav class="navbar navbar-default navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="navbar-header">
                            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".main-navbar-collapse">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                            <a class="navbar-brand" href="main.jsp">主面板</a>
                        </div>
                    </div>
                </div>
            </div><!-- /.container -->
        </nav>

        <div class="container">
            <!-- left, vertical navbar & content -->
            <div class="row">
                <!-- content -->
                <div class="col-md-12">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="page-header">
                                <h1>欢迎，${loginUser.getUsername()}</h1>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="text-muted bootstrap-admin-box-title">密码</div>
                                </div>
                                <div class="bootstrap-admin-panel-content text-muted" style="padding: 60px 0; text-align: center">
                                    您的密码是 ${loginUser.getPassword()}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- footer -->
        <div class="navbar navbar-footer">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <footer role="contentinfo">
                            <p class="right">&copy; 2016 by 蔡文</p>
                        </footer>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-3.0.0.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/twitter-bootstrap-hover-dropdown.min.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/js/bootstrap-admin-theme-change-size.js"></script>
    </body>
</html>
