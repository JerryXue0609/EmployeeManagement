//页面加载
$(document).ready(function () {
    loadGrid()
    checkfunction()
});
var deviceid = undefined;
var publishbtfunctionid = '121'; //增加
var updatebtfunctionid = '123'; //修改
var deletebtfunctionid = '122'; //删除

var seachName=null;

//加载表格datagrid
function loadGrid() {
    //加载数据
    $('#devicetab').datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        url: './userlist.json',
        queryParams:{seachName:seachName},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'num', title: '工号', align: 'center', width: getwidth(0.1)},
            {field: 'name', title: '员工名称', align: 'center', width: getwidth(0.1)},
            {field: 'levelName', title: '员工等级', align: 'center', width: getwidth(0.1)},
            {field: 'startTime', title: '创建时间', align: 'center', width: getwidth(0.1)},
        ]],
        toolbar: [{
            id:'add',
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                popupaddmodel('添加员工');
            }
        }, '-',
            {
                id:'update',text: '修改', iconCls: 'icon-edit', handler: function () {
                var row = $("#devicetab").datagrid('getSelected');
                if (row !=null) {
                    popupaddmodel('修改员工');
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



function popupaddmodel(title) {
    $('#userid').val('');
    $("#mdlg").dialog("open").dialog('setTitle', title);
    $("#addfm").form("clear");
    $("#username").attr("readonly",false);
}




function getDate(id) {
    $("#username").attr("readonly",true);
    $('#addfm').form('load','getuserinfo.json?userid='+id);
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
                url: 'deluser.json',
                data: {"id": id},
                dataType: 'json',
                loadMsg: '数据提交中请稍后……',
                success: function (data) {
                    if (data.code == "0") {
                        $.messager.alert('提示', '删除用户成功!');
                        $("#devicetab").datagrid("reload");
                    } else {
                        $.messager.alert('警告', data.msg);
                        $("#devicetab").datagrid('unselectAll');
                    }
                },
                error: function () {
                    // view("异常！");
                    $.messager.alert('警告', '删除理员失败，网络故障!');
                    $("#devicetab").datagrid('unselectAll');
                }
            });
        }
    });
}

/**
 * 保存管理员信息
 */

function savemanager(){
    var id = $('#id').val();
    var num = $('#num').val();
    var name = $('#name').val();
    var sex = $('#sex option:selected').val();
    var edu = $('#edu').val();
    var level = $('#level option:selected').val();
    var userjson = {"id": id,"num": num,"name": name,"sex":sex,"edu": edu,"level":level};
    if(num==""||num==null){
        $.messager.alert("信息不完整","员工号不能为空");
        return false;
    }
    if(name==""||name==null){
        $.messager.alert("信息不完整","员工姓名不能为空");
        return false;
    }
    if(sex==""||sex==null){
        $.messager.alert("信息不完整","性别不能为空");
        return false;
    }
    if(edu==""||edu==null){
        $.messager.alert("信息不完整","学历不能为空");
        return false;
    }
    if(level==""||level==null){
        $.messager.alert("信息不完整","等级不能为空");
        return false;
    }
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        url: 'saveuser.json',
        data: JSON.stringify(userjson),
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                // view("修改成功！");
                $.messager.alert('提示', '添加/修改用户成功!');
                $('#mdlg').dialog('close');
                $("#devicetab").datagrid('reload');
            } else {
                $.messager.alert('警告', data.msg);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('警告', '添加用户失败，网络故障!');
        }
    });
}
