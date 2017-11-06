//页面加载
$(document).ready(function () {
    loadGrid();
});

var seachName=null;

//加载表格datagrid
function loadGrid() {
    //加载数据
    $('#devicetab').datagrid({
        width: 'auto',
        height: 'auto',
        striped: true,
        singleSelect: true,
        url: './userapplist.json',
        queryParams:{seachName:seachName},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'userName', title: '员工名称', align: 'center', width: getwidth(0.1)},
            {field: 'cate', title: '申请类型', align: 'center', width: getwidth(0.1)},
            {field: 'status', title: '状态', align: 'center', width: getwidth(0.1),formatter: function(value,row,index){
                if(value==0){
                    return "<span style='color:orange;'>未审核</span>";
                }else if (value ==1){
                    return "<span style='color:green;'>通过</span>";
                }else if (value ==-1){
                    return "<span style='color:red;'>不通过</span>";
                }
            }},
            {field: 'updateTime', title: '申请时间', align: 'center', width: getwidth(0.1)},
            {field: 'id', title: '审核', align: 'center', width: getwidth(0.1),formatter: function(value,row,index){
                  return "<a href='javascript:void(0)' onclick=\"popupmodel('审核','"+ value +"');\">审核</a>";
            }}
        ]]
        // ,
        // toolbar: [
        //     {
        //     id:'add',
        //     text: '增加',
        //     iconCls: 'icon-add',
        //     handler: function () {
        //         popupaddmodel('添加员工等级');
        //     }
        // }, '-',
        //     {
        //         id:'update',text: '修改', iconCls: 'icon-edit', handler: function () {
        //         var row = $("#devicetab").datagrid('getSelected');
        //         if (row !=null) {
        //             popupaddmodel('修改员工等级');
        //             $("#devicetab").datagrid('unselectAll');
        //             getDate(row["id"]);
        //         } else {
        //             $.messager.alert('操作提示', '请选择一条数据!');
        //         }
        //     }
        //     }, '-',
        //     {id:'delete',text: '删除', iconCls: 'icon-remove', handler: function () {
        //         var row = $("#devicetab").datagrid('getSelected');
        //         if (row !=null) {
        //             delDate(row["id"]);
        //         }else{
        //             $.messager.alert('操作提示', '请选择一条数据!');
        //         }
        //     }}]
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




// function popupaddmodel(title) {
//     $('#userid').val('');
//     $("#mdlg").dialog("open").dialog('setTitle', title);
//     $("#addfm").form("clear");
//     $("#username").attr("readonly",false);
// }


function popupmodel(title,id) {
    $("#mdlg").dialog("open").dialog('setTitle', title);
    getDate(id)
}

function getDate(id) {
    $('#addfm').form('load','getuserappinfo.json?appid='+id);
}

// /**
//  * 删除信息
//  * @param id
//  */
// function delDate(id) {
//     if(id=="0"){
//         $.messager.alert()
//     }
//     //删除
//     $.messager.confirm('提示框', '你确定要删除吗?',function(f) {
//         if (f) {
//             $.ajax({
//                 type: 'POST',
//                 url: 'deluserlevel.json',
//                 data: {"id": id},
//                 dataType: 'json',
//                 loadMsg: '数据提交中请稍后……',
//                 success: function (data) {
//                     if (data.code == "0") {
//                         $.messager.alert('提示', '删除用户成功!');
//                         $("#devicetab").datagrid("reload");
//                     } else {
//                         $.messager.alert('警告', data.msg);
//                         $("#devicetab").datagrid('unselectAll');
//                     }
//                 },
//                 error: function () {
//                     // view("异常！");
//                     $.messager.alert('警告', '删除理员失败，网络故障!');
//                     $("#devicetab").datagrid('unselectAll');
//                 }
//             });
//         }
//     });
// }

/**
 * 保存信息
 */
function savemanager(status){
    var id = $('#id').val();
    var userjson = {"id": id,"status": status};
    $.ajax({
        type: 'POST',
        contentType: "application/json;charset=utf-8",
        url: 'saveuserapp.json',
        data: JSON.stringify(userjson),
        dataType: 'json',
        success: function (data) {
            if (data.code == "0") {
                // view("修改成功！");
                $.messager.alert('提示', '审核成功!');
                $('#mdlg').dialog('close');
                $("#devicetab").datagrid('reload');
            } else {
                $.messager.alert('警告', data.msg);
            }
        },
        error: function () {
            // view("异常！");
            $.messager.alert('警告', '审核失败，网络故障!');
        }
    });
}
