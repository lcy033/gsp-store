<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>用户信息</title>

    <style type="text/css">
        .query {
            padding: 5px 10px;
        }

        .query tr {
            height: 25px;
        }

        form .label {
            width: 75px;
            text-align: right;
        }

        input, select {
            width: 160px;
        }

        #btn {
            margin: 5px 0 0 5px;
        }
    </style>

</head>
<body class="easyui-layout">
<div data-options="region:'north',title:'搜索条件',split:true" style="overflow: hidden;height:70px;">
    <form id="queryNoticeForm" class="easyui-form">
        <table class="query">
            <tr>
                <td class="label">用户名：</td>
                <td>
                    <input id="user" name="user" class="easyui-textbox"/>
                </td>

                <td class="label">用户姓名：</td>
                <td>
                    <input id="name" name="name" class="easyui-textbox"/>
                </td>

                <td class="label">启用状态：</td>
                <td>
                    <select id="enable" name="enable" class="easyui-combobox" style="width:160px;"
                            data-options="editable:false">
                        <option value="">所有状态</option>
                        <option value="0">未启用</option>
                        <option value="1">已启用</option>
                    </select>
                </td>
                <td colspan="16" align="left">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="searchByConditions" href="#" class="easyui-linkbutton" onclick="searchByConditions();">搜索</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a id="resetConditions" href="#" class="easyui-linkbutton" onclick="resetConditions();">重置</a>
                </td>
            </tr>
        </table>
    </form>
</div>
<div data-options="region:'center',title:'搜索结果',split:true">
    <table id="getUserDatagrid"></table>
</div>
<div id="tbLendNotice">
    <span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="refresh">刷新</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true" id='add'
           onclick="add()">添加</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" plain="true" id='update'
           onclick="update()">修改</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='enableBtn'
           onclick="enableBtn()">启用</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='disableBtn'
           onclick="disableBtn()">禁用</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id='userRoleBtn'
           onclick="userRoleBtn()">权限管理</a>
    </span>
</div>

<script language="javascript">

    window.top["reload_Abnormal_Monitor"]=function(){
        resetConditions();
        grid.datagrid('reload');
        grid.datagrid('clearSelections');
    };

    var grid;
    $(function () {

        grid = $('#getUserDatagrid').datagrid({
                nowrap: false,
                striped: true,
                fit: true,
                url: '${ctx}/user/queryAll',
                singleSelect: true,
                columns: [
                    [
                        {field: 'id', hidden: true},
                        {field: 'user', title: '用户名', width: 130, align: 'left'},
                        {field: 'password', title: '用户密码', width: 130, align: 'left'},
                        {field: 'name', title: '用户姓名', width: 130, align: 'left'},
                        {field: 'mobile', title: '用户手机号', width: 130, align: 'left'},
                        {field: 'email', title: '用户邮箱', width: 130, align: 'left'},
                        {field: 'org', title: '组织', width: 130, align: 'left'},
                        {
                            field: 'enable', title: '使用状态', width: 130, align: 'left',
                            formatter: function (fieldVal, rowData, rowIndex) {
                                if (fieldVal == '1') {
                                    return '已启用';
                                } else {
                                    return '未启用';
                                }
                            }
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            width: 130,
                            align: 'left',
                            formatter: function (value, data) {
                                return new Date(data.createTime).formate("yyyy-MM-dd HH:mm");
                            }
                        },
                        {
                            field: 'updateTime',
                            title: '更新时间',
                            width: 130,
                            align: 'left',
                            formatter: function (value, data) {
                                return new Date(data.updateTime).formate("yyyy-MM-dd HH:mm");
                            }
                        }
                    ]],
                pagination: true,
                rownumbers: true,
                pageList: [10, 20, 30],//选择一页显示多少数据
                loadFilter: pagerFilter,
                toolbar: "#tbLendNotice",
                onLoadError: function (data) {
                    $.messager.alert('提示信息', "查询失败！");
                }
            }
        );

        //分页功能
        function pagerFilter(data) {
            if (typeof data.length == 'number' && typeof data.splice == 'function') {
                data = {
                    total: data.length,
                    rows: data
                }
            }
            var dg = $(this);
            var opts = dg.datagrid('options');
            var pager = dg.datagrid('getPager');
            pager.pagination({
                onSelectPage: function (pageNum, pageSize) {
                    opts.pageNumber = pageNum;
                    opts.pageSize = pageSize;
                    pager.pagination('refresh', {
                        pageNumber: pageNum,
                        pageSize: pageSize
                    });
                    dg.datagrid('loadData', data);
                }
            });
            if (!data.originalRows) {
                if (data.rows)
                    data.originalRows = (data.rows);
                else if (data.data && data.data.rows)
                    data.originalRows = (data.data.rows);
                else
                    data.originalRows = [];
            }
            var start = (opts.pageNumber - 1) * parseInt(opts.pageSize);
            var end = start + parseInt(opts.pageSize);
            data.rows = (data.originalRows.slice(start, end));
            return data;
        }

        //刷新
        $('#refresh').click(function () {
            resetConditions();
            grid.datagrid('reload');
            grid.datagrid('clearSelections');
        });

    });

    //Enter搜索
    $('#queryNoticeForm').keypress(function (e) {
        var keynum; //字符的ASCII码。
        if (window.event) { // IE
            keynum = e.keyCode;
        } else if (e.which) { //其他浏览器
            keynum = e.which;
        }

        if (keynum == 13) { //按下“Enter”键
            $('#searchByConditions').focus();
            $('#searchByConditions').click();
        }
    });
    //搜索
    function searchByConditions() {
        var dataObj = {};
        dataObj.user = $('#user').val();
        dataObj.name = $('#name').val();
        dataObj.enable = $('#enable').combobox('getValue');
        $("#getUserDatagrid").datagrid('load', dataObj);
        $('#getUserDatagrid').datagrid('clearSelections');
    }


    //重置
    function resetConditions() {
        $('#queryNoticeForm').form('clear');
    }

    function update() {
        var row = $('#getUserDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        }
        if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行修改！');
            return false;
        }
        parent.$("#tabs").tabs("add", {
            closable: true,
            title: '修改用户信息',
            content: '<iframe scrolling="no" frameborder="0"  src="${ctx}/user/update/' + row[0].id + '" width="100%" height="99%"></iframe>'
        });
    }

    function add() {
        parent.$("#tabs").tabs("add", {
            closable: true,
            title: '添加用户信息',
            content: '<iframe scrolling="no" frameborder="0"  src="${ctx}/user/add" width="100%" height="99%"></iframe>'
        });
    }

    //启用用户
    function enableBtn() {
        var row = $('#getUserDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        } else if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行修改！');
            return false;
        } else if (row[0].enable == '1') {
            $.messager.alert('提示信息', '该用户已启用！');
            return false;
        } else {
            $.messager.confirm('警告', '确定启用该用户吗?', function (r) {
                if (r) {
                    $.ajax({
                        url: '${ctx}/user/enable',
                        data: {"id": row[0].id},
                        type: 'POST',
                        cache: false,
                        dataType: "json",
                        success: function (dataObj) {
                            if (dataObj.code == '200') {
                                grid.datagrid('clearSelections');
                                grid.datagrid('load');
                            }
                            $.messager.alert('提示信息', dataObj.message);
                        }
                    });
                }
            });
        }
    }

    //禁用角色
    function disableBtn() {
        var row = $('#getUserDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        } else if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行修改！');
            return false;
        } else if (row[0].enable == '0') {
            $.messager.alert('提示信息', '该用户已禁用！');
            return false;
        } else {
            $.messager.confirm('警告', '确定禁用该用户吗?', function (r) {
                if (r) {
                    $.ajax({
                        url: '${ctx}/user/stop',
                        data: {"id": row[0].id},
                        type: 'POST',
                        cache: false,
                        dataType: "json",
                        success: function (dataObj) {
                            if (dataObj.code == '200') {
                                grid.datagrid('clearSelections');
                                grid.datagrid('load');
                            }
                            $.messager.alert('提示信息', dataObj.message);
                        }
                    });
                }
            });
        }
    }

    //用户权限管理
    function userRoleBtn() {
        var row = $('#getUserDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        } else if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行查看！');
            return false;
        } else {
            parent.$("#tabs").tabs("add", {
                closable: true,
                title: '权限管理',
                content: '<iframe scrolling="no" frameborder="0"  src="${ctx}/user/userRole/' + row[0].id + '" width="100%" height="99%"></iframe>'
            });
        }
    }
</script>
</body>
</html>