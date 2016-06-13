<%@ page import="com.jikexueyuan.caiwen.domain.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/header.jsp"%>

<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-lg-12">
                    <div class="page-header">
                        <h1>学生列表</h1>
                    </div>
                </div>
            </div>
            <%
                ArrayList<Student> studentList = (ArrayList<Student>) request.getAttribute("students");
                if (studentList.size() == 0) {
            %>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">select * from t_students;</div>
                        </div>
                        <div class="bootstrap-admin-panel-content text-muted"
                             style="padding: 60px 0; text-align: center">
                            尚无数据, <button id="addBtn" class="btn btn-xs btn-primary">添加10条</button>

                        </div>
                    </div>
                </div>
            </div>
            <%
                } else {
            %>
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">select * from t_students;
                                <button id="delMalesBtn" class="btn btn-xs btn-primary right">删除所有男生</button>
                            </div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>学号</th>
                                    <th>姓名</th>
                                    <th>年龄</th>
                                    <th>性别</th>
                                    <th>地址</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%
                                    }
                                    for(Student student: studentList) {
                                %>
                                <tr>
                                    <td><%=student.getId()%></td>
                                    <td><%=student.getName()%></td>
                                    <td><%=student.getAge()%></td>
                                    <td><%=student.getGender()%></td>
                                    <td><%=student.getAddress()%></td>
                                </tr>
                                <%
                                    }
                                %>
                                </tbody>
                            </table>
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
<script>
    $(function() {
        $("#delMalesBtn").click(function() {
            $.ajax({type:'GET', url: '<%=request.getContextPath()%>/studentlist?action=delete&gender=M', success: function() {
                window.location.reload();
            }});
        });
        $("#addBtn").click(function() {
            $.ajax(
                    {
                        type: 'GET',
                        url: '<%=request.getContextPath()%>/studentlist?action=add',
                        success: function () {
                            window.location.reload();
                        }
                    });
        });
    });
</script>
</body>
</html>
