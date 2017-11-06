//页面加载
$(document).ready(function () {
    loadGrid();
    checkfunction();
});
var deviceid = undefined;
var publishbtfunctionid = '001'; //增加
var updatebtfunctionid = '003'; //修改
var deletebtfunctionid = '002'; //删除

var seachName=null;

//加载表格datagrid
function loadGrid() {
    //加载数据
    $('#devicetab').datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        url: './rolelist.json',
        queryParams:{seachName:seachName},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'name', title: '角色名', align: 'center', width: getwidth(0.1)},
            {field: 'isEnabled', title: '状态', align: 'center', width: getwidth(0.1),formatter: function(value,row,index){
                if(value==1){
                    return "不可用";
                }else{
                    return "可用";
                }
            }},
            {field: 'id', title: '权限管理', align: 'center', width: getwidth(0.1),formatter: function(value,row,index){
                if(value=="0"){
                    return "";
                }else{
                    return "<a href='javascript:void(0)' onclick=\"popupmodel('角色权限修改','"+ value +"');\">修改权限</a>";

                }
            }}
        ]],
        toolbar: [{
            id:'add',
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                popupaddmodel('添加角色');
            }
        }, '-',
            {
                id:'update',text: '修改', iconCls: 'icon-edit', handler: function () {
                var row = $("#devicetab").datagrid('getSelected');
                if (row !=null) {
                    popupaddmodel('修改角色');
                    $("#devicetab").datagrid('unselectAll');
                    getDate(row["id"]);
                } else {
                    $.messager.alert('操作提示', '请选择一条数据!');
                }
            }
            }, '-',
            {id:'delete',text: '删除', iconCls: 'icon-remove', handler: function () {
                var row = $("#devicetab").datagrid('getSelected');
                if (row !=null) {
                    delDate(row["id"]);
                }else{
                    $.messager.alert('操作提示', '请选择一条数据!');
                }
            }}]
    });
}


function checkfunction(){

    //控制按钮是否可用
    //$("#add").linkbutton("disable");

    $.ajax({
        type: 'POST',
        url: 'getbtfunction.json',
        dataType: 'json',
        async: false,
        success: function (data) {
            if (data.code == "0") {
                var result = data.data;

                var addresult = false; //增加按钮
                var updateresult= false;    //修改按钮
                var deleteresult = false;   //删除按钮
                for(var i=0; i<result.length; i++){
                    if(result[i]==publishbtfunctionid){
                        addresult=true;   //如果用户权限中有这个按钮则表明有权限
                    }
                    if(result[i]==updatebtfunctionid){
                        updateresult=true;
                    }
                    if(result[i]==deletebtfunctionid){
                        deleteresult=true;
                    }
                };
                if(!addresult){
                    $("#add").linkbutton("disable");
                }

                if(!updateresult){
                    $("#update").linkbutton("disable");
                }


                if(!deleteresult){
                    $("#delete").linkbutton("disable");
                }
                //$.messager.alert('提示', "加载成功！");
                // view("修改成功！");
            } else {
                $.messager.alert('错误提示', data.message);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('错误提示', '删除路由器失败，网络故障!');
        }
    });
}



/**
 * 搜索条件查询
 * @param title
 */

function xialachange(){
    seachName = $('#seachName').val();
    loadGrid();
    seachName=null;
}

/**
 * 修改用户权限谈出框
 * @param title
 * @param userid
 */
function popupmodel(title,roleId) {
    $("#dlg").dialog("open").dialog('setTitle', title);
    $("#userfunction").form("clear");
    loadUserfunction(roleId);
}
/**
 * 添加修改用户信息弹出框
 * @param title
 */
function popupaddmodel(title) {
    $('#userid').val('');
    $("#mdlg").dialog("open").dialog('setTitle', title);
    $("#addfm").form("clear");
    $("#username").attr("readonly",false);
}

/**
 * 保存用户权限
 */
function savefunction() {
    $.messager.confirm('提示框', '你确定要提交吗?',function(f){
        var nodes = $('#userfunction').tree('getChecked');
        //alert(JSON.stringify(nodes));
        var postjson = {roleid:$("#roleId").val(),data:nodes};
        $.ajax({
            type: 'POST',
            contentType: "application/json;charset=utf-8",
            url: 'saverolefunction.json',
            data: JSON.stringify(postjson),
            async:false,
            dataType: 'json',
            loadMsg: '数据提交中请稍后……',
            success: function (data) {
                if (data.code == "0") {
                    // view("修改成功！");
                    $.messager.alert('提示', '角色权限修改成功!');
                    $('#dlg').dialog('close');
                    $("#devicetab").datagrid('reload');
                } else {
                    $.messager.alert('警告', data.msg);
                }
            },
            error: function () {
                // view("异常！");
                $.messager.alert('警告', '角色权限修改失败，网络故障!');
            }
        });
    });
}
/**
 * 获取用户信息并绑定到form
 * @param id
 */
function getDate(id) {
    $("#username").attr("readonly",true);
    $('#addfm').form('load','getroleinfo.json?roleid='+id);
}

/**
 * 删除信息
 * @param id
 */
function delDate(id) {
    if(id=="0"){
        $.messager.alert()
    }
    //删除
    $.messager.confirm('提示框', '你确定要删除吗?',function(f) {
        if (f) {
            $.ajax({
                type: 'POST',
                url: 'delrole.json',
                data: {"id": id},
                dataType: 'json',
                loadMsg: '数据提交中请稍后……',
                success: function (data) {
                    if (data.code == "0") {
                        // view("修改成功！");
                        $.messager.alert('提示', '删除角色成功!');
                        $("#devicetab").datagrid("reload");
                    } else {
                        $.messager.alert('警告', data.msg);
                        $("#devicetab").datagrid('unselectAll');
                    }
                },
                error: function () {
                    // view("异常！");
                    $.messager.alert('警告', '删除角色失败，网络故障!');
                    $("#devicetab").datagrid('unselectAll');
                }
            });
        }
    });
}

/**
 * 获取用户权限
 * @param userid
 */
function loadUserfunction(roleId){
    $('#roleId').val(roleId);
    $('#userfunction').tree({
        url: 'getroletbfunction.json?roleid='+roleId,
        loadFilter: function(data)
        {
            return data.data;
        }
    });
}

/**
 * 保存管理员信息
 */
function saverole(){
    var id = $('#id').val();
    var name = $('#name').val();
    var isEnabled = $('#isEnabled option:selected').val();
    var userjson = {"id": id,"name": name,"isEnabled":isEnabled};
    if(name==""||name==null){
        $.messager.alert("信息不完整","用户名不能为空");
        return false;
    }
    if(isEnabled==""||isEnabled==null){
        $.messager.alert("信息不完整","状态不能为空");
        return false;
    }
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        url: './saverole.json',
        data: JSON.stringify(userjson),
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                // view("修改成功！");
                $.messager.alert('提示', '添加/修改角色成功!');
                $('#mdlg').dialog('close');
                $("#devicetab").datagrid('reload');
            } else {
                $.messager.alert('警告', data.msg);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('警告', '添加角色失败，网络故障!');
        }
    });
}
