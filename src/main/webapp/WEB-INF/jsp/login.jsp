<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="shortcut icon" href="favicon.ico" />
    <link rel="bookmark" href="favicon.ico" />

    <%--动态获取项目的上下文路径--%>
    <%--    <link href="<%=request.getContextPath()+"/h-ui/css/H-ui.min.css"%>" rel="stylesheet" type="text/css" />--%>
    <%--    <link href="/s/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />--%>
    <%--jstl 中动态获取上下文路径的方式--%>
    <link href='${pageContext.request.contextPath}/h-ui/css/H-ui.min.css' rel="stylesheet" type="text/css" />
    <link href='${pageContext.request.contextPath}/h-ui/css/H-ui.login.css'rel="stylesheet" type="text/css" />
    <link href='${pageContext.request.contextPath}/h-ui/lib/icheck/icheck.css' rel="stylesheet" type="text/css" />
    <link href='${pageContext.request.contextPath}/h-ui/lib/Hui-iconfont/1.0.1/iconfont.css' rel="stylesheet" type="text/css" />
    <link href='${pageContext.request.contextPath}/easyui/themes/default/easyui.css' rel="stylesheet" type="text/css" />
    <link href='${pageContext.request.contextPath}/easyui/themes/icon.css' rel="stylesheet" type="text/css" />

    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/js/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/h-ui/js/H-ui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/h-ui/lib/icheck/jquery.icheck.min.js" ></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>

    <script type="text/javascript">
      $(function() {
        //点击图片切换验证码
        $("#vcodeImg").click(function() {
          this.src = "LoginServlet?action=captcha&t=" + new Date().getTime();
        });

        //登录
        $("#submitBtn").click(function() {
          if($("#radio-2").attr("checked") && "${systemInfo.forbidStudent}" == 1) {
            $.messager.alert("消息提醒", "学生暂不能登录系统！", "warning");
            return;
          }
          if($("#radio-3").attr("checked") && "${systemInfo.forbidTeacher}" == 1) {
            $.messager.alert("消息提醒", "教师暂不能登录系统！", "warning");
            return;
          }

          var data = $("#form").serialize();
          $.ajax({
            type: "post",
            url: "LoginServlet?method=Login",
            data: data,
            dataType: "json", //返回数据类型
            success: function(res) {
              const {code,msg} =res
              console.log(code,msg,typeof msg)

              if (code === 200) {
                $.messager.alert("消息提醒", res.msg);
                window.location = '/admin';
              }else{
                $.messager.alert("消息提醒", res.msg, "warning");
              }
            }

          });
        });

        //设置复选框
        $(".skin-minimal input").iCheck({
          radioClass: 'iradio-blue',
          increaseArea: '25%'
        });
      })
    </script>
    <title>登录|学生成绩管理系统</title>
    <meta name="keywords" content="学生成绩管理系统">
</head>

<body>

<div class="header" style="padding: 0;">
    <h2 style="color: white; width: 400px; height: 60px; line-height: 60px; margin: 0 0 0 30px; padding: 0;">学生成绩管理系统</h2>
</div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form id="form" class="form form-horizontal" method="post">
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-8">
                    <input  name="account" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-8">
                    <input id="" name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <div class="formControls col-8 col-offset-3">
                    <input class="input-text size-L" name="vcode" type="text" placeholder="请输入验证码" style="width: 200px;">
                    <img title="点击图片切换验证码" id="vcodeImg" src="LoginServlet?action=captcha"></div>
            </div>

            <div class="mt-20 skin-minimal" style="text-align: center;">
                <div class="radio-box">
                    <input type="radio" id="radio-2" name="type" checked value="2" />
                    <label for="radio-1">学生</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-3" name="type" value="3" />
                    <label for="radio-2">老师</label>
                </div>
                <div class="radio-box">
                    <input type="radio" id="radio-1" name="type" value="1" />
                    <label for="radio-3">管理员</label>
                </div>
            </div>

            <div class="row">
                <div class="formControls col-8 col-offset-3">
                    <input id="submitBtn" type="button" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright &nbsp; @ Mryang </div>

</body>

</html>