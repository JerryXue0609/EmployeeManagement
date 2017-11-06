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
        url: './usersalarylist.json',
        queryParams:{},
        loadMsg: '数据加载中请稍后……',
        pagination: true,
        rownumbers: true,
        fitColumns:true,
        columns: [[
            {field: 'userName', title: '员工名称', align: 'center', width: getwidth(0.1)},
            {field: 'salary', title: '实发工资', align: 'center', width: getwidth(0.1)},
            {field: 'updateTime', title: '发放时间', align: 'center', width: getwidth(0.1)}
        ]]
    });
}
