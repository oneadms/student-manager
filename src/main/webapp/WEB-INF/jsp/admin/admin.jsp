
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>学生成绩管理系统 管理员后台</title>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="bookmark" href="favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/css/default.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/easyui/themes/icon.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src='${pageContext.request.contextPath}/easyui/js/outlook2.js'> </script>
    <script type="text/javascript">

      var _menus = {"menus":[
          {"menuid":"1","icon":"","menuname":"成绩统计分析",
            "menus":[
              {"menuid":"11","menuname":"考试列表","icon":"icon-exam","url":"/exam/examList.jsp"},
            ]
          },
          {"menuid":"2","icon":"","menuname":"学生信息管理",
            "menus":[
              {"menuid":"21","menuname":"学生列表","icon":"icon-user-student","url":"../student/studentList.html"},
            ]
          },
          {"menuid":"3","icon":"","menuname":"教师信息管理",
            "menus":[
              {"menuid":"31","menuname":"教师列表","icon":"icon-user-teacher","url":"../teacher/teacherList.html"},
            ]
          },
          {"menuid":"4","icon":"","menuname":"基础信息管理",
            "menus":[
              {"menuid":"41","menuname":"年级列表","icon":"icon-world","url":"/grade?action=page"},
              {"menuid":"42","menuname":"班级列表","icon":"icon-house","url":"../class/clazzList.html"},
              {"menuid":"43","menuname":"课程列表","icon":"icon-book-open","url":"/course?action=page"}
            ]
          },
          {"menuid":"5","icon":"","menuname":"系统管理",
            "menus":[
              {"menuid":"51","menuname":"系统设置","icon":"icon-set","url":"../admin/adminPersonal.html"},
            ]
          }
        ]};

    </script>

</head>
<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
        <img src="images/noscript.gif" alt='抱歉，请开启脚本支持！' />
    </div>
</noscript>
<div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        /*background: url(../images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;*/
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
    <span style="float:right; padding-right:20px;" class="head"><span style="color:red; font-weight:bold;">${username}&nbsp;</span>您好&nbsp;&nbsp;&nbsp;<a href="${pageContext.request.contextPath}/login" id="loginOut">安全退出</a></span>
    <span style="padding-left:10px; font-size: 16px; ">学生信息管理系统</span>
</div>
<div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
    <div class="footer">Copyright &copy; Power By Mryang</div>
</div>
<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
    <div id="nav" class="easyui-accordion" fit="true" border="false">
        <!--  导航内容 -->
    </div>

</div>
<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
    <div id="tabs" class="easyui-tabs"  fit="true" border="false" >

        <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; ">
            <p style="font-size: 50px; line-height: 60px; height: 60px;">${systemInfo.schoolName}</p>
            <p style="font-size: 25px; line-height: 30px; height: 30px;">欢迎使用学生成绩管理系统</p>
            <p>开发人员：${systemInfo.devlopper}</p>
            <p>开发周期：${systemInfo.devTime}</p>

            <hr />
            <h2>系统环境</h2>
            <p>系统环境：${systemInfo.osDev}</p>
            <p>开发工具：${systemInfo.devTools}</p>
            <p>Java版本：${systemInfo.javaVersion}</p>
            <p>服务器：${systemInfo.tomcatVersion}</p>
            <p>数据库：${systemInfo.mysqlVersion}</p>
            <p>系统采用技术： Servlet+Jsp+Jdbc+dbutils+EasyUI+jQuery+Ajax+面向接口编程</p>
        </div>
    </div>
</div>

<iframe width=0 height=0 src="refresh.jsp"></iframe>

</body>
</html>