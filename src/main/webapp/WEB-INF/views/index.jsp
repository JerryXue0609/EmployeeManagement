<%--
  Created by IntelliJ IDEA.
  User: yinhaijin
  Date: 15/8/11
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>企业管理系统</title>
    <link href="../css/default.css" rel="stylesheet" type="text/css"/>
    <link href="../js/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
    <link href="../js/themes/icon.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=BBWVtUKsXom83jKbWKygC8yy"></script>
    <script type="text/javascript" src="../js/company/company.index.js"> </script>
    <script type="text/javascript">
        function popupmodel(title) {
            $("#mdlg").dialog("open").dialog('setTitle', title);
            $("#passwordfm").form("clear");
        }
        function changepassword(){
            var oldpassword = $("#oldpassword").val();
            var password = $("#password").val();
            var confirmpassword = $("#confirmpassword").val();
            if(oldpassword==""||oldpassword==null){
                $.messager.alert("信息不完整","原密码不能为空");
                return false;
            }
            if(password==""||password==null){
                $.messager.alert("信息不完整","新密码不能为空");
                return false;
            }

            if(password == confirmpassword) {
                $.ajax({
                    type: 'POST',
                    url: 'changePassword.json',
                    data: {"password":password,"oldpassword":oldpassword},
                    dataType: 'json',
                    loadMsg: '数据提交中请稍后……',
                    success: function (data) {
                        if (data.code == "0") {
                            $.messager.alert('提示', '修改密码成功!');
                            $('#mdlg').dialog('close');
                        } else {
                            $.messager.alert('警告', data.msg);
                        }
                    },
                    error: function () {
                        $.messager.alert('警告', '修改密码失败，网络故障!');
                    }
                });
            }else{
                $.messager.alert("警告","两次输入不一致");
            }
            return;
        }
    </script>
</head>
<body class="easyui-layout" style="overflow-y:hidden" fit="true" scroll="no">
    <noscript>
        <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
            <img src="../images/noscript.gif" alt='抱歉，请开启脚本支持！'/>
        </div>
    </noscript>
    <div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
        <div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;">
            <img src="../images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
    </div>
    <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(../images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎使用企业管理系统
            <a href="javascript:void(0);" onclick="javascript:popupmodel('用户密码修改');" id="editpass">修改密码</a>
            <a href="./logout.html" id="loginOut">安全退出</a>
        </span>
        <span style="padding-left:10px; font-size: 16px; "><img src="../images/blocks.gif" width="20" height="20" align="absmiddle" />企业管理系统</span>
    </div>
    <div region="south" split="true" style="height: 30px; background: #D2E0F2; ">
        <div class="footer">By NJUPT实训</div>
    </div>
    <div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
        <div id="nav">
            <!--  导航内容 -->

        </div>

    </div>
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
            <div title="欢迎使用" style="padding:20px;overflow:hidden; color:red; " >
                <div id="maincontext">

                </div>

            </div>
        </div>
    </div>


    <div id="mdlg" class="easyui-dialog" style="width: 350px; height: 190px; padding: 10px 20px;" closed="true"
         buttons="#mdlg-buttons">
        <form id="passwordfm" method="post">
            <table>
                <tr>
                    <td>当前密码：</td>
                    <td>
                        <input type="password" name="oldpassword" id="oldpassword" value="">
                    </td>
                </tr>
                <tr>
                    <td>新密码：</td>
                    <td>
                        <input type="password" name="password" id="password" value="">
                    </td>
                </tr>
                <tr>
                    <td>确认密码：</td>
                    <td>
                        <input type="password" name="confirmpassword" id="confirmpassword" value="">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="mdlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:changepassword();" iconcls="icon-save">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#mdlg').dialog('close');"
           iconcls="icon-cancel">取消</a>
    </div>
</body>
</html>
