<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>添加角色信息</title>
    <style type="text/css">
        fieldset {
            width: 1300px;
        }

        .one {
            text-align: right;
            height: 30px;
            width: 160px;
        }

        .two {
            text-align: left;
            height: 30px;
            width: 150px;
        }

        select {
            height: 22px;
            border: 1px solid #7F9DB9;
        }

        table td input {
            width: 151px;
            border: 1px solid #7F9DB9;
        }

        #staffFormAdd .easyui-validatebox, .easyui-datebox, .easyui-numberbox {
            width: 151px;
            editable: false;
        }

        #staffFormAdd .easyui-combobox, .easyui-combobox {
            width: 151px;
            editable: false;
        }
    </style>
<body style="overflow:auto;">
<div>
    <div id="lookdialog"></div>
    <form id="addRoleForm" class="easyui-form" method="post" data-options="novalidate:true">
        <div title="新增" style="padding: 10px;">

            <fieldset>
                <legend>新增信息 &nbsp;&nbsp;&nbsp;</legend>
                <table>
                    <tr>
                        <td class="one">角色名称：</td>
                        <td class="two">
                            <input id="roleName" name="roleName" class="easyui-validatebox" placeholder="角色名称"
                                   style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">角色别名：</td>
                        <td class="two">
                            <input id="roleType" name="roleType" class="easyui-validatebox" placeholder="角色英文别名"
                                   style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">角色描述：</td>
                        <td class="two">
                            <input id="roleDesc" name="roleDesc" class="easyui-validatebox" placeholder="角色描述"
                                   style="width: 590px;height: 26px" maxlength="50" data-options="required:true">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">使用状态：</td>
                        <td class="two">
                            <select id="enable" class="easyui-combobox" name="enable">
                                <option value="true">启用</option>
                                <option value="false">不启用</option>
                            </select>
                        </td>
                    </tr>
                </table>
            </fieldset>
            <div style="float:right;margin:10px">
                <a href="javascript:;" class="easyui-linkbutton" id="add">保存</a>
                &nbsp;&nbsp;
                <a href="javascript:;" class="easyui-linkbutton" id="close">关闭</a>

            </div>
        </div>
    </form>
</div>
</body>

<script language="javascript">

    $(document).ready(function () {
        //添加
        $('#add').click(function () {
            $.messager.confirm('提示信息', '确认保存吗?', function (r) {
                if (r) {
                    $('#addRoleForm').form('submit', {
                        url: '${ctx}/role/addGspRole',
                        success: function (dataObj) {
                            dataObj = eval("(" + dataObj + ")");
                            if (dataObj.code == '200') {
                                $.messager.alert('提示信息', dataObj.message, 'info', function () {
                                    parent.reloadTabGrid("角色管理");
                                    parent.$("#tabs").tabs("close", "添加角色信息");
                                });
                            } else {
                                $.messager.alert('提示信息', dataObj.message);
                            }
                        }
                    });
                }
            });
        });

        //关闭
        $('#close').click(function () {
            $.messager.confirm('提示信息', "确认关闭吗？", function (r) {
                if (r) {
                    parent.$("#tabs").tabs("close", "添加角色信息");
                }
            });

        });
    });
</script>

</html>

