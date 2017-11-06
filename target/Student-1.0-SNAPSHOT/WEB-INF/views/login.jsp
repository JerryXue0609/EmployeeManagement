<%--
  Created by IntelliJ IDEA.
  User: yinhaijin
  Date: 15/9/26
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>企业管理系统</title>
    <link href="../css/main.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/fun.base.js"></script>
    <script type="text/javascript" src="../js/login.js"></script>
    <!--[if IE 6]>
    <script src="../js/DD_belatedPNG.js" type="text/javascript"></script>
    <script>DD_belatedPNG.fix('.png')</script>
    <![endif]-->
</head>
<body>

<div class="login">
    <div class="box png">
        <div>
           <%--<span>企业管理系统</span>--%>
        </div>
        <div class="input">
            <div class="log">
                <%--<div class="logo">--%>
                    <%--</div>--%>
                <div class="name">
                    <label>帐号</label>
                    <input type="text" class="text" id="username" placeholder="用户名" name="username" tabindex="1" required="required">
                </div>
                <div class="pwd">
                    <label>密码</label><input type="password" class="text" id="password" placeholder="密码" name="password" tabindex="2" style="margin-left:4px;" required="required">
                    <label style="margin-left:25%;margin-top:8px;"><input name="ident" type="radio" value="1" checked="checked" />员工</label>
                    <label style="margin-top:8px;"><input name="ident" type="radio" value="0" />管理员</label>
                    <input type="button" class="submit" onclick="javascript:login();" tabindex="3" value="登录">
                    <div id="ermsg" style="color: red;text-align: left; display: none; font-size: 8px;">用户名或密码错误！</div>
                </div>
                <div class="tip">

                </div>
            </div>
        </div>
    </div>
    <div class="footer"></div>
</div>
<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
    <p>适用浏览器：FireFox、Chrome、Safari. 不支持IE8及以下浏览器。</p>
</div>
</body>
</html>
