<%--
  Created by IntelliJ IDEA.
  User: yinhaijin
  Date: 15/8/11
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>角色管理</title>
    <link rel="stylesheet" type="text/css" href="../js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/company/company.rolemanage.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#seachName').change(function(){
                xialachange();
            });
        });

    </script>
</head>
<body style="margin: 0px 0px 0px 0px">

<!--查询条件开始-->
<div id="toolbar">
    角色名：
<input id="seachName" class="easyui-textbox" data-options="prompt:'角色名。。。',searcher:doSearch"></br>

</div>
<div style="width: 100%;height: 10px;">
</div>
<!--查询条件结束-->


<table id="devicetab" title="角色管理" style="margin:0px 0px 0px 0px;">
</table>

<div id="dlg" class="easyui-dialog" style="width: 200px; height: 420px; padding: 10px 20px;" closed="true"
     buttons="#dlg-buttons">
    <ul id="userfunction" class="easyui-tree" checkbox="true">
    </ul>
    <input id="userid" type="hidden" value="">
</div>
<div id="dlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="savefunction()" iconcls="icon-save">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#dlg').dialog('close');"
       iconcls="icon-cancel">取消</a>
</div>

<div id="mdlg" class="easyui-dialog" style="margin-top:10px;width: 350px; height: 150px; padding: 10px 20px;" closed="true"
     buttons="#mdlg-buttons">
    <form id="addfm" method="post">
        <table>
            <tr>
                <td>角色名:</td>
                <td>
                    <input type="text" name="name" id="name" value="" class="form_control">
                    <input type="hidden" name="roleId" id="roleId" value="">
                </td>

            </tr>

            <tr>
                <td>状态：</td>
                <td>
                    <select id="isEnabled" name="isEnabled">
                       <option value='0'>可用</option>
                       <option value='1'>不可用</option>
                    </select>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="mdlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="saverole()" iconcls="icon-save">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#mdlg').dialog('close');"
       iconcls="icon-cancel">取消</a>
</div>

</body>
</html>
