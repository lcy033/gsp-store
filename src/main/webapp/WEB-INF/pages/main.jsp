<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <title>gsp</title>
</head>
<body class="easyui-layout">

<div region="north" border="true" style="height: 55px;">
    <%@ include file="header.jsp" %>
</div>
<div data-options="region:'west',split:true,title:'功能导航'" style="height: 200px; padding: 1px; width: 200px;">
    <div style="margin: 10px 0;">

        <ul id="munes" class="easyui-tree" data-options="data:menuData"></ul>

    </div>
</div>
<div id="mainPanle" region="center" border="true" border="false">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
        <div title="主页">
            <div class="cs-home-remark">
                <h1>这里是GSP系统</h1> <br>
            </div>
        </div>
    </div>
</div>
<div data-options="region:'south',border:'none'" style="height:20px;">
    <%@ include file="footer.jsp" %>
</div>
<!-- 菜单树、选项卡 -->
<script type="text/javascript" charset="UTF-8">
    var _treeView = $('#munes');//菜单栏
    $(document).ready(function () {
        $("#munes").tree({onClick:function (node) {
                var click_obj = $('#munes').tree('getSelected');
                if(click_obj.state == 'open' && click_obj.attributes == null){
                    _treeView.tree('collapse',node.target);
                }else if(click_obj.state == 'closed' && click_obj.attributes == null){
                    _treeView.tree('expand',node.target);
                }else{
                    var url = node.attributes.url;
                    var name = node.text
                    parent.$("#tabs").tabs("add",{
                        closable:true,
                        title:node.text,
                        content : '<iframe scrolling="no" frameborder="0" src="${ctx}' + url + '" width="100%" height="99%"></iframe>'
                    });
                    }
                }

        });
    });

    function makeEasyTree(data){
        if(!data)
            return [];
        var _newData = []; //最终返回结果
        var _treeArray = {}; //记录一级节点
        var _root = 1; //最顶层fid
        var _idKey = "id"; //主键的键名
        var _fidKey = "fid"; //父ID的键名
        _getChildren(_root);
        function _getChildren($root){
            var $children = [];
            for (var i in data){
                if($root == data[i][_fidKey]){
                    data[i]["children"] = _getChildren(data[i][_idKey]);
                    $children.push(data[i]);
                }
                //只要一级节点
                if(_root == data[i][_fidKey] && !_treeArray[data[i][_idKey]]){
                    _treeArray[data[i][_idKey]] = data[i];
                    _newData.push(data[i]);
                }
            }
            return $children;
        }
        return _newData;
    }

    var menuData;
    jQuery.ajax({
        url: "<%=request.getContextPath()%>/user/treeView?id=" + ${user.id},
        type: "get",
        dataType: "json",
        async: false,
        success: function (dataObj) {
            if (dataObj.code == '200'){
                menuData = dataObj.result;
            }else {
                $.messager.alert('提示信息', dataObj.message);
            }
        }
    });

</script>
</body>
</html>