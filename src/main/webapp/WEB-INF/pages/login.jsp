<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/icon.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyui/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${ctx}/static/style/main.css" />
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/extEasyUI.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/jquery.color.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyui/easyui-validator-ext.js"></script>

    <!-- EasyUIEx -->
    <link rel="stylesheet" type="text/css" href="${ctx}/static/easyuiex/css/easyuiex.css">
    <script type="text/javascript" src="${ctx}/static/easyuiex/easy.easyuiex.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/easyuiex/easy.easyuiex-validate.js"></script>
    <%-- jquery Cookie plugin --%>
    <script type="text/javascript" src="${ctx}/static/easyee/jquery.cookie.js"></script>

    <title>Pharmacy Login</title>

    <!-- EasyUI CSS -->
    <style type="text/css">
        * {
            font-size: 15px;
            font-family: 'Open Sans', Arial, Helvetica, sans-serif;
        }

        h1, h1 span {
            font-size: 48px;
            font-family: 'Roboto', '微软雅黑';
            font-weight: 700;
        }

        .footer {
            margin: 10px auto;
        }
    </style>

    <script type="text/javascript">

        $(document).ready(function () {
            //登录
            $('#loginBtn').click(function () {
                $('#loginForm').form('submit', {
                    url: '${ctx}/user/login',
                    success: function (data) {
                        data = eval("(" + data + ")");
                        if (data.code == 200) {
                            var id = data.result.id;
                            if (id != 0) {
                            location.href = '${ctx}/home/index';
                            } else {
                                $.messager.alert('提示信息', '登录失败');
                            }
                        } else {
                            $.messager.alert('提示信息', data.message);
                        }
                    }
                });
            });
        });

    </script>
</head>

<body>
<div style="text-align: center;overflow:auto;width:100%;height:100%;margin: 100px auto;">
    <h1><span style="color:#8FC31F">Pharmacy</span>-<span style="">Platform</span></h1>
    <div style="margin: 10px auto;">
    </div>
    <div style="margin: 0 auto;width: 500px;">
        <div class="easyui-panel" title="User Login" style="width:500px;">
            <div style="padding:10px 60px 20px 60px;">
                <form id="loginForm" class="easyui-form" method="post"
                      data-options="novalidate:true" action="toLogin">

                    <table cellpadding="5" style="">

                        <tr>
                            <td width="90">用户:</td>
                            <td><input class="easyui-textbox" type="text"
                                       name="user" id="user" style="height:30px;width: 190px;"
                                       data-options="validType:[],required:true,prompt:'user name...'"/></td>
                        </tr>
                        <tr>
                            <td>密码:</td>
                            <td><input class="easyui-textbox" type="password"
                                       name="password" style="height:30px;width: 190px;"
                                       data-options="required:true"/></td>
                        </tr>
                    </table>
                </form>
                <div style="text-align:center;padding:5px">
                    <a href="javascript:void(0)" class="easyui-linkbutton"
                       id="loginBtn">登 录</a>
                </div>

            </div>
        </div>

    </div>
    <div class="footer">
        <p>
            © 2018 - 2018 Ray
        </p>
        <p>
            联系、反馈、定制、培训/Contact, Feedback, Custom, Train Email：<a
                href="#">baidu@163.com</a>
        </p>
    </div>

</div>

</body>
</html>
