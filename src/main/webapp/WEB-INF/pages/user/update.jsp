<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/pages/taglibs.jsp" %>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" src="${ctx}/static/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"></script>
<link rel="stylesheet" href="${ctx}/static/ueditor1_4_3-utf8-jsp/themes/default/css/ueditor.css"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改用户信息</title>
    <style type="text/css">
        fieldset {
            width: 1665px;
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

    </style>
<body style="overflow:auto;">
<div>
    <div id="lookdialog"></div>
    <form id="editUserForm" class="easyui-form" method="post" data-options="novalidate:true">
        <div title="修改" style="padding: 10px;">

            <fieldset>
                <legend>修改信息 &nbsp;&nbsp;&nbsp;</legend>
                <table>
                    <tr>
                        <input type="hidden" id="id" name="id"/>
                        <td class="one">用户名：</td>
                        <td class="two">
                            <input id="user" name="user" class="easyui-validatebox" style="width: 590px;height: 26px"
                                   maxlength="50">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">用户密码：</td>
                        <td class="two">
                            <input id="password" name="password" class="easyui-validatebox"
                                   style="width: 590px;height: 26px" maxlength="50">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">用户姓名：</td>
                        <td class="two">
                            <input id="name" name="name" class="easyui-validatebox" style="width: 590px;height: 26px"
                                   maxlength="50">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">用户手机号：</td>
                        <td class="two">
                            <input id="mobile" name="mobile" class="easyui-validatebox"
                                   style="width: 590px;height: 26px" maxlength="50">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">用户邮箱：</td>
                        <td class="two">
                            <input id="email" name="email" class="easyui-validatebox" style="width: 590px;height: 26px"
                                   maxlength="50">
                        </td>
                    </tr>
                    <tr style="height: 50px">
                        <td class="one">组织：</td>
                        <td class="two">
                            <input id="org" name="org" class="easyui-validatebox" style="width: 590px;height: 26px"
                                   maxlength="50">
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

    window.top["Refresh_CloudHomePage_Content"] = function () {
        window.location.reload();
    }

    $(document).ready(function () {
        $("#id").val("${gspUser.id}");
        $("#user").val("${gspUser.user}");
        $("#password").val("${gspUser.password}");
        $("#name").val("${gspUser.name}");
        $("#mobile").val("${gspUser.mobile}");
        $("#email").val("${gspUser.email}");
        $("#org").val("${gspUser.org}");

        //更新
        $('#add').click(function () {
            $.messager.confirm('提示信息', '确认保存吗?', function (r) {
                if (r) {
                    $('#editUserForm').form('submit', {
                        url: '${ctx}/user/updateGspUser',
                        success: function (dataObj) {
                            dataObj=eval("(" + dataObj+ ")");
                            if (dataObj.code == '200') {
                                $.messager.alert('提示信息', dataObj.message, 'info', function () {
                                    parent.reloadTabGrid("用户管理");
                                    parent.$("#tabs").tabs("close", "修改用户信息");
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
                    parent.$("#tabs").tabs("close", "修改用户信息");
                }
            });

        });
    });
</script>

</html>

