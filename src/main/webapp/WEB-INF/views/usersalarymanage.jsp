<%--
  Created by IntelliJ IDEA.
  User: lokey
  Date: 17/6/29
  Time: 20:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>我的工资</title>
    <link rel="stylesheet" type="text/css" href="../js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/company/company.usersalary.js"></script>
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
<%--<div id="toolbar">--%>
    <%--用户名：--%>
<%--<input id="seachName" class="easyui-textbox" data-options="prompt:'名称。。。',searcher:doSearch"></br>--%>

<%--</div>--%>
<div style="width: 100%;height: 10px;">
</div>
<!--查询条件结束-->


<table id="devicetab" title="我的工资" style="margin:0px 0px 0px 0px;">
</table>

<div id="mdlg" class="easyui-dialog" style="margin-top:10px;width: 400px; height: 320px; padding: 10px 20px;" closed="true"
     buttons="#mdlg-buttons">
    <form id="addfm" method="post">
        <table>
            <tr>
                <td>员工姓名:</td>
                <td>
                    <input type="text" name="userName" id="userName" value="" class="form_control">
                    <input type="hidden" name="id" id="id" value="">
                </td>
            </tr>

            <tr>
                <td>实发工资:</td>
                <td>
                    <textarea type="text" name="salary" id="salary" value="" class="form_control"></textarea>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="mdlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="savemanager()" iconcls="icon-save">确认</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#mdlg').dialog('close');"
       iconcls="icon-cancel">取消</a>
</div>
</body>
</html>
