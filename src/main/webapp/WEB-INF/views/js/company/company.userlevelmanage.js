//页面加载
$(document).ready(function () {
    loadGrid()
    checkfunction()
});
var deviceid = undefined;
var publishbtfunctionid = '131'; //增加
var updatebtfunctionid = '133'; //修改
var deletebtfunctionid = '132'; //删除

var seachName=null;

//加载表格datagrid
function loadGrid() {
    //加载数据
    $('#devicetab').datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        url: './userlevellist.json',
        queryParams:{seachName:seachName},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'name', title: '等级名称', align: 'center', width: getwidth(0.1)},
            {field: 'salary', title: '基础工资', align: 'center', width: getwidth(0.1)}
        ]],
        toolbar: [{
            id:'add',
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                popupaddmodel('添加员工等级');
            }
        }, '-',
            {
                id:'update',text: '修改', iconCls: 'icon-edit', handler: function () {
                var row = $("#devicetab").datagrid('getSelected');
                if (row !=null) {
                    popupaddmodel('修改员工等级');
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
    $('#addfm').form('load','getuserlevelinfo.json?levelid='+id);
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
                url: 'deluserlevel.json',
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
    var name = $('#name').val();
    var salary = $('#salary').val();
    var userjson = {"id": id,"name": name,"salary": salary};
    if(name==""||name==null){
        $.messager.alert("信息不完整","员工号不能为空");
        return false;
    }
    if(salary==""||salary==null){
        $.messager.alert("信息不完整","等级工资不能为空");
        return false;
    }
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        url: 'saveuserlevel.json',
        data: JSON.stringify(userjson),
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                // view("修改成功！");
                $.messager.alert('提示', '添加/修改员工等级成功!');
                $('#mdlg').dialog('close');
                $("#devicetab").datagrid('reload');
            } else {
                $.messager.alert('警告', data.msg);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('警告', '添加员工等级失败，网络故障!');
        }
    });
}
