//页面加载
$(document).ready(function () {
    loadGrid()
});
var deviceid = undefined;

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
            {field: 'baseSalary', title: '基础工资', align: 'center', width: getwidth(0.1)},
            {field: 'startTime', title: '创建时间', align: 'center', width: getwidth(0.1)},
            {field: 'id', title: '工资管理', align: 'center', width: getwidth(0.1),formatter: function(value,row,index){
                return "<a href='javascript:void(0)' onclick=\"popupmodel('工资管理','"+ value +"','"+ row["name"] +"','"+ row["baseSalary"] +"');\">工资管理</a>";
            }}
        ]]
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

function popupmodel(title,id,name,baseSalary) {
    $("#userid").val(id);
    $("#userName").val(name)
    $("#baseSalary").val(baseSalary)
    $("#dlg").dialog("open").dialog('setTitle', title);
    $('#salarytab').datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        url: './salarylist.json',
        queryParams:{userId:id},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'userName', title: '姓名', align: 'center', width: getwidth(0.1)},
            {field: 'salary', title: '工资', align: 'center', width: getwidth(0.1)},
            {field: 'updateTime', title: '时间', align: 'center', width: getwidth(0.1)}
        ]],
        toolbar: [{
            id:'add',
            text: '增加',
            iconCls: 'icon-add',
            handler: function () {
                popupaddmodel('添加工资');
            }
        }]
    });
    // getDate(id)
}


function popupaddmodel(title) {
    $("#mdlg").dialog("open").dialog('setTitle', title);
}




// function getDate(id) {
//     $("#username").attr("readonly",true);
//     $('#addfm').form('load','getuserinfo.json?userid='+id);
// }


/**
 * 新增工资
 */
function savesalary(){
    var userid = $('#userid').val();
    var salary = $('#salary').val();
    var baseSalary = $('#baseSalary').val();
    var userName = $('#userName').val();
    var userjson = {"userId": userid,"salary": salary,"baseSalary": baseSalary,"userName": userName};
    var regu = /^[1-9]\d*$/;

    if(salary==""||salary==null){
        $.messager.alert("信息不完整","工资不能为空");
        return false;
    }
    if(!regu.test(salary)){
        $.messager.alert("信息不完整","工资只能是正整数");
        return false;
    }
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        url: 'savesalary.json',
        data: JSON.stringify(userjson),
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                // view("修改成功！");
                $.messager.alert('提示', '添加工资成功!');
                $('#mdlg').dialog('close');
                $("#salarytab").datagrid('reload');
            } else {
                $.messager.alert('警告', data.msg);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('警告', '添加工资失败，网络故障!');
        }
    });
}
