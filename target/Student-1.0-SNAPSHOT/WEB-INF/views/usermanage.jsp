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
    <title>用户管理</title>
    <link rel="stylesheet" type="text/css" href="../js/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="../js/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="../css/demo.css">
    <script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="../js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="../js/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="../js/company/company.usermanage.js"></script>
    <script type="text/javascript" src="../js/common.js"></script>
    <script type="text/javascript" src="../js/jquery.form.js"></script>
    <link href="../js/myUploadJs/uploadify/uploadify.css" rel="stylesheet" type="text/css"/>
    <script src="../js/myUploadJs/uploadify/jquery.uploadify.min.js" type="text/javascript"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#seachName').change(function(){
                xialachange();
            });
        });
    </script>
</head>
<body style="margin: 0px 0px 0px 0px">
<input id="file_upload" name="file_upload" type="file" multiple="true">
<script type="text/javascript">
    $('#file_upload').uploadify({
        'buttonText' : 'excel导入',
        'swf'      : '../js/myUploadJs/uploadify/uploadify.swf',
        'uploader' : '../file/excelUpload',
        'fileObjName' : 'file',
        'onInit'   : function(instance) {
        },
        'onUploadSuccess' : function(file, data, response) {
            var obj = jQuery.parseJSON(data);
            var url = obj.data.filePath;
            $.post("exexlIn.json",
                    {
                        url: url
                    },
                    function (data, status) {
                        if (status == "success") {
                            if (data.code == 0) {
                                $.messager.alert('导入成功', data.msg);
                                location.reload();
                            } else {
                                $.messager.alert('操作提示', data.msg);
                            }
                        }
                    });
        }

    });
</script>


<table id="devicetab" title="用户管理" style="margin:0px 0px 0px 0px;">
</table>

<div id="mdlg" class="easyui-dialog" style="margin-top:10px;width: 350px; height: 250px; padding: 10px 20px;" closed="true"
     buttons="#mdlg-buttons">
    <form id="addfm" method="post">
        <table>
            <tr>
                <td>员工编号:</td>
                <td>
                    <input type="text" name="num" id="num" value="" class="form_control">
                    <input type="hidden" name="id" id="id" value="">
                </td>
            </tr>

            <tr>
                <td>姓名:</td>
                <td>
                    <input type="text" name="name" id="name" value="" class="form_control">
                </td>
            </tr>

            <tr>
                <td>性别:</td>
                <td>
                    <select id="sex" name="sex">
                       <option value='0'>男</option>
                       <option value='1'>女</option>
                    </select>
                </td>
            </tr>

            <tr>
                <td>学历:</td>
                <td>
                    <input type="text" name="edu" id="edu" value="" class="form_control">
                </td>
            </tr>

            <tr>
                <td>员工等级:</td>
                <td>
                    <select id="level" name="level">
                        <c:forEach items="${levelList}" var="level">
                            <option value='${level.id}'>${level.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

        </table>
    </form>
</div>
<div id="mdlg-buttons">
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="savemanager()" iconcls="icon-save">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" onclick="javascript:$('#mdlg').dialog('close');"
       iconcls="icon-cancel">取消</a>
</div>
</body>
</html>
