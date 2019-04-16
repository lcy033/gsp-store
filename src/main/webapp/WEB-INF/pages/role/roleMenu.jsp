<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>权限管理</title>

    <style type="text/css">

        .query tr {
            height: 25px;
        }

        input, select {
            width: 160px;
        }

    </style>

</head>
<body class="easyui-layout">
<div data-options="region:'center',title:'搜索结果',split:true">
    <table id="getRoleDatagrid"></table>
</div>

<div id="addRoleMenu" class="easyui-dialog" title="绑定" style="width:400px;height:200px;"
     data-options="iconCls:'icon-save',resizable:true,closed:true">
    <div style="margin: 20px 0px 0px 80px;">
        <label style="margin: 0px 0px 0px 50px;">请选择需要绑定的菜单</label>
        <br><br><br>
        <label>菜单：</label>
        <input class="easyui-combobox" data-options="editable:false" id='getMenuList'/>
        <br><br>
        <div style="margin: 0px 0px 0px 50px;">
            <a href="#" onclick="YES();" class="easyui-linkbutton">确认</a>
            &nbsp;&nbsp;&nbsp;&nbsp;
            <a href="#" onclick="NO();" class="easyui-linkbutton">取消</a>
        </div>
    </div>
</div>

<div id="tbLendNotice">
    <span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" plain="true" id="refresh">刷新</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" plain="true"
           onclick="addMenu()">新增菜单</a>
        <span class="datagrid-btn-separator" style="float:none;"></span>
        <a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="delRole()">删除角色</a>
    </span>
</div>

<script language="javascript">

    var roleId = ${gspRole.id};

    window.top["reload_Abnormal_Monitor"] = function () {
        resetConditions();
        grid.datagrid('reload');
        grid.datagrid('clearSelections');
    };

    var grid;
    $(function () {

        //下拉框
        $("#getMenuList").combobox({
            url:'${ctx}/role/getMenuList',
            valueField:'id',
            textField:'text'
        });

        grid = $('#getRoleDatagrid').datagrid({
                nowrap: false,
                striped: true,
                fit: true,
                url: '${ctx}/role/queryRoleMenu',
                queryParams: {"roleId": roleId},
                singleSelect: true,
                columns: [
                    [
                        {field: 'id', hidden: true},
                        {field: 'menuName', title: '菜单名称', width: 130, align: 'left'},
                        {field: 'menuDesc', title: '菜单详情', width: 130, align: 'left'},
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
            grid.datagrid('reload');
            grid.datagrid('clearSelections');
        });

    });

    //删除角色
    function delRole() {
        var row = $('#getRoleDatagrid').datagrid('getSelections');
        if (row.length < 1) {
            $.messager.alert('提示信息', '请选择一条记录！');
            return false;
        } else if (row.length > 1) {
            $.messager.alert('提示信息', '只能选择单条记录进行删除！');
            return false;
        } else {
            $.messager.confirm('警告', '确定删除该用户的角色吗?', function (r) {
                if (r) {
                    $.ajax({
                        url: '${ctx}/role/delMenu',
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

    function addMenu() {
        $('#addRoleMenu').dialog({
            title: "绑定",
            width: 400,
            height: 200,
            modal: true,
            closed: false
        });
    }

    function YES() {
        if (typeof $('#getMenuList').combobox('getValue') != '') {
            $.messager.confirm('确定', '您确定要绑定吗？', function (r) {
                if (r) {
                    $.ajax({
                        url: '${ctx}/role/addRoleMenu',
                        data: {
                            "menuId": $('#getMenuList').combobox('getValue'), "roleId": roleId
                        },
                        type: 'POST',
                        cache: false,
                        dataType: "json",
                        success: function (dataObj) {
                            if (dataObj.code == '200') {
                                grid.datagrid('reload');
                                grid.datagrid('clearSelections');
                                $('#addRoleMenu').dialog('close');
                            }
                            $.messager.alert('提示信息', dataObj.message);
                        }
                    });
                }
            });
        }
    }

    function NO() {
        $('#addRoleMenu').dialog({
            closed: true
        });
    }
</script>
</body>
</html>