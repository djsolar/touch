var tree;
$(function () {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $.ajaxSetup({
            beforeSend: function (xhr) {
                if (header && token) {
                    xhr.setRequestHeader(header, token);
                }
            }
        }
    );

    tree = $("#wrapper-menu-tree").treeview(
        {
            data: data,
            color: "#4D4D4D",
            levels: 1,
            onNodeSelected: function (event, data) {
                display_selected_node(data);
            },
            onNodeUnselected: function (event, data) {
                clear_content();
            }
        });
    String.prototype.endWith = function (endStr) {
        var d = this.length - endStr.length;
        return (d >= 0 && this.lastIndexOf(endStr) == d)
    }
    material_select();
    // 添加删除节点
    node_delete();
    node_sibling_add();
    node_child_add();
    up_node();
    down_node();
    up_header();
    down_tail();
    node_save();
    material_add();
    save_program();
});

function display_selected_node(data) {
    console.log(data);
    var type = data.type;
    enable_all_button();
    if (type === 0) {
        display_level_one(data)
        add_level_one_click();
    } else if (type === 1) {
        display_level_two(data);
        add_level_two_click();
    } else if (type === 2) {
        display_content(data);
        add_content_click();
    }
    content_edit();
    material_add();
    material_delete();
}

function add_level_one_click() {
    $("#level-one div.display-img").click(function () {
        $(this).siblings().removeClass("item-active");
        $(this).addClass("item-active");
        $("#operation_edit").removeClass("disabled");
    });
}

function add_level_two_click() {
    $("#level-two div.content").click(function () {
        $(this).siblings().removeClass("item-active");
        $(this).addClass("item-active");
        if ($(this).attr("mediaType") === "2") {
            $("#operation_look").removeClass("disabled");
        }
        $("#operation_edit").removeClass("disabled");
        $("#operation_delete").removeClass("disabled");
    });
}

function clear_content() {
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    disable_all_button();
}

function add_content_click() {
    $("#content ul li").click(function () {
        $(this).siblings().removeClass("item-active");
        $(this).addClass("item-active");
        if ($(this).attr("mediaType") === "2") {
            $("#operation_look").removeClass("disabled");
        }
        $("#operation_edit").removeClass("disabled");
        $("#operation_delete").removeClass("disabled");
        $("#operation_up").removeClass("disabled");
        $("#operation_down").removeClass("disabled");
    });
}


function display_level_two(data) {
    var levelTwoHtml = template("level-two-template", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(levelTwoHtml);
    var tree_node = $("#wrapper-menu-tree");
    var nodes = tree_node.treeview("getSelected");
    var node = nodes[0];
    if (node.data.urlMaterial !== null) {
        $("#operation_add").addClass("disabled");
        $("#operation_up").addClass("disabled");
        $("#operation_down").addClass("disabled");
    } else {
        $("#operation_up").addClass("disabled");
        $("#operation_down").addClass("disabled");
        if (typeof(node.nodes) !== "undefined" && node.nodes !== null && node.nodes.length > 0) {
            $("#operation_add").addClass("disabled");
        }
    }
}

function display_content(data) {
    var result = template("content", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(result);
}

/**
 * 初始化button的状态
 */
function enable_all_button() {
    $("#operation_edit").addClass("disabled");
    $("#operation_look").addClass("disabled");
    $("#operation_delete").addClass("disabled");
    $("#operation_up").addClass("disabled");
    $("#operation_down").addClass("disabled");

    $("#operation_add").removeClass("disabled");
    $("#operation_save").removeClass("disabled");
}

function disable_all_button() {
    $("#operation_edit").addClass("disabled");
    $("#operation_look").addClass("disabled");
    $("#operation_delete").addClass("disabled");

    $("#operation_add").addClass("disabled");
    $("#operation_up").addClass("disabled");
    $("#operation_down").addClass("disabled");
    $("#operation_save").addClass("disabled");
}

function display_level_one(data) {
    var levelOneHtml = template("level-one-template", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(levelOneHtml);
    add_level_one_click()
    $("#operation_add").addClass("disabled");
    $("#operation_up").addClass("disabled");
    $("#operation_down").addClass("disabled");
    $("#operation_look").addClass("disabled");
    $("#operation_delete").addClass("disabled");
}

var table;

function material_select() {
    $("#selectMaterial").on("show.bs.modal", function () {
        var tree_node = $("#wrapper-menu-tree");
        var nodes = tree_node.treeview('getSelected');
        if (nodes.length === 0)
            return;
        var node = nodes[0];
        var type = node.data.mediaType;
        if (typeof(table) !== "undefined") {
            table.destroy();
        }
        table = $('#materialDataTables').DataTable({
            "pagingType": "simple_numbers",//设置分页控件的模式
            searching: false,//屏蔽datatales的查询框
            aLengthMenu: [5],//设置一页展示10条记录
            "bLengthChange": false,//屏蔽tables的一页展示多少条记录的下拉列表
            info: false,
            "oLanguage": {  //对表格国际化
                "sLengthMenu": "每页显示 _MENU_条",
                "sZeroRecords": "没有找到符合条件的数据",
                //  "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
                "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
                "sInfoEmpty": "木有记录",
                "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
                "sSearch": "搜索：",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "前一页",
                    "sNext": "后一页",
                    "sLast": "尾页"

                }
            },
            "iDisplayLength": 3,
            "processing": false,
            "bAutoWidth": false,
            "serverSide": true,
            "ajax": {
                "url": "/resource/getMaterialData",
                "dataSrc": "aaData",
                "data": {"type": type}
            }, "dom": 'rt<"#pBottom"p>',
            "columns": [
                {
                    "data": "first",
                    render: function (data, type, row) {
                        var result = template("material-list", {"material": data});
                        return result;
                    }
                },
                {
                    "data": "second",
                    render: function (data, type, row) {
                        var result = template("material-list", {"material": data});
                        return result;
                    }
                }

            ]
        });
        table.on('draw', function () {
            $("#materialDataTables tbody tr td img.material-select").click(function () {
                var mediaType = $(this).attr("mediaType");
                var macName = $(this).attr("macName");
                var originName = $(this).attr("originName");
                var materialId = $(this).attr("materialId");
                var node = get_selected_node();
                if (isEdit) {
                    var activeNode = $(".item-active");
                    activeNode.attr("mediaType", mediaType);
                    activeNode.attr("macName", macName);
                    activeNode.attr("originName", originName);
                    activeNode.attr("materialId", materialId);
                    if (mediaType === "1") {
                        var imgNode = activeNode.find("img");
                        imgNode.attr("src", "/" + macName);
                        imgNode.attr("alt", originName);
                    } else if (mediaType === "2") {
                        $(".item-active>label").text(originName);
                    }

                    $("#selectMaterial").modal("hide");
                } else {
                    var resultHtml;
                    if (node.type === 1) {
                        resultHtml = template("level-two-item", {
                            "urlMaterial": {
                                "mediaType": mediaType,
                                "md5Name": macName,
                                "originName": originName,
                                "id": materialId
                            }
                        });
                        $("#level-two").append(resultHtml);
                        add_level_two_click();
                        $("#operation_add").addClass("disabled");
                        node.data.mediaType = mediaType;
                        $("#selectMaterial").modal("hide");
                    } else if (node.type === 2) {
                        resultHtml = template("content-item", {
                            "material": {
                                "mediaType": mediaType,
                                "md5Name": macName,
                                "originName": originName,
                                "id": materialId
                            }
                        });
                        $("#content ul").append(resultHtml);
                        add_content_click();
                        node.data.mediaType = mediaType;
                        $("#selectMaterial").modal("hide");
                    }

                }
            });
        });
    });
}

function node_delete() {
    $("#delete_select_node").click(function () {
        var nodes = $('#wrapper-menu-tree').treeview('getSelected');
        if (nodes.length === 0)
            return;
        $('#wrapper-menu-tree').treeview('removeNode', [nodes, {silent: true}]);
        $('#wrapper-content').empty();
    });
}

function node_sibling_add() {
    $("#add_sibling_node").click(function () {

        var nodes = $('#wrapper-menu-tree').treeview('getSelected');
        var new_node;
        if (nodes.length === 0 || nodes[0].type === 0) {
            var data = {
                "normalMaterial": null,
                "selectedMaterial": null,
                "mediaType": 1
            };
            new_node = {"text": "Level-1", "type": 0, "data": data};
        }

        if (nodes.length > 0 && nodes[0].type === 1) {
            var data = {
                "contentBeans": null,
                "label": "LevelTwoNew",
                "many": false,
                "mediaType": 0,
                "title": "LevelTwoNew",
                "url": null,
                "urlMaterial": null
            };
            new_node = {"text": "LevelTwoNew", "type": 1, "data": data};
        }

        if (nodes.length > 0 && nodes[0].type === 2) {
            var data = {
                "label": "ContentNew",
                "materials": [],
                "mediaType": 0,
                "title": "ContentNew"
            }
            new_node = {"text": "ContentNew", "type": 2, "data": data};
        }

        var index;
        var parentNode;
        if (nodes.length > 0) {
            index = nodes[0].index + 1;
            parentNode = $('#wrapper-menu-tree').treeview('getParents', nodes[0]);
        }
        $('#wrapper-menu-tree').treeview('addNode', [new_node, parentNode, index, {silent: true}]);
    });
}

function node_child_add() {
    $("#add_child_node").click(function () {
        var nodes = $("#wrapper-menu-tree").treeview('getSelected');
        if (nodes.length === 0) {
            return;
        }
        if (nodes[0].type === 2) {
            return;
        }
        var node = nodes[0];
        if (node.type === 0) {
            var level_two_node = {
                "text": "levelTwoNew", "type": 1, "data":
                    {
                        "label": "levelTwoNew",
                        "title": "levelTwoNew",
                        "urlMaterial": null,
                        "many": false,
                        "mediaType": 0
                    }
            };
            $('#wrapper-menu-tree').treeview('addNode', [level_two_node, nodes[0], undefined, {silent: true}]);
        } else if (node.type === 1) {
            var new_nodes = [];
            if (node.data.urlMaterial !== null) {
                var urlMaterial = node.data.urlMaterial;
                var title = node.data.title;
                var first_node = {
                    "text": title, "type": 2, data:
                        {
                            "label": title, "title": title,
                            "materials": [urlMaterial],
                            "mediaType": node.data.mediaType
                        }
                };
                node.data.urlMaterial = null;
                node.data.mediaType = 0;
                new_nodes.push(first_node);
            }
            var content_node = {
                "text": "contentNew", "type": 2, data:
                    {
                        "label": "contentNew", "title": "contentNew",
                        "materials": [],
                        "mediaType": 0
                    }
            };
            new_nodes.push(content_node);

            $('#wrapper-menu-tree').treeview('addNode', [new_nodes, nodes[0], undefined, {silent: true}]);
        }

    });
}

function get_file_type(filename) {
    if (filename.toLowerCase().endWith(".png") || filename.endWith(".jpg") || filename.endWith(".jpeg"))
        return 1;
    else if (filename.toLowerCase().endWith(".txt")) {
        return 2;
    }
    return 0;
}

function up_node() {
    $("#up_node").click(function () {
        var nodes = $("#wrapper-menu-tree").treeview('getSelected');
        if (nodes.length === 0)
            return;
        var node = nodes[0];
        var parentNode = $("#wrapper-menu-tree").treeview('getParents', node);
        var index = node.index - 1;
        if (index < 0) {
            index = 0;
        }
        $('#wrapper-menu-tree').treeview('addNode', [parentNode, nodes[0], index, {silent: true}]);
        $('#wrapper-menu-tree').treeview('removeNode', [node, {silent: true}]);
    });
}

function down_node() {
    $("#down_node").click(function () {
        var nodes = $("#wrapper-menu-tree").treeview('getSelected');
        if (nodes.length === 0)
            return;
        var node = nodes[0];
        var parentNode = $("#wrapper-menu-tree").treeview('getParents', node);
        var index = node.index + 1;
        $('#wrapper-menu-tree').treeview('addNode', [parentNode, node, index, {silent: true}]);
        $('#wrapper-menu-tree').treeview('removeNode', [node, {silent: true}]);
    });
}

function up_header() {
    $("#up_header").click(function () {
        var tree_node = $("#wrapper-menu-tree");
        var nodes = tree_node.treeview('getSelected');
        if (nodes.length === 0)
            return;
        var node = nodes[0];
        var parentNode = tree_node.treeview('getParents', node);
        var index = 0;
        var copyData = {};
        var copyState = {};
        $.extend(copyData, node.data);
        $.extend(copyState, node.state);
        var new_node = {"text": node.text, "type": node.type, "data": copyData, "state": copyState};
        tree_node.treeview('addNode', [parentNode, new_node, index, {silent: true}]);
        //tree_node.treeview('removeNode', [ node, { silent: true } ]);
        alert(JSON.stringify(new_node));
    });
}

function down_tail() {

}

// 保存修改
function node_save() {
    $("#operation_save").click(function () {
        var tree_node = $("#wrapper-menu-tree");
        var nodes = tree_node.treeview('getSelected');
        if (nodes.length === 0)
            return;
        var node = nodes[0];
        if (node.type === 0) {
            var divNodes = $("#level-one div.display-img");
            var normalDiv = divNodes[0];
            var selectedDiv = divNodes[1];
            var normalMaterial = {};
            var selectedMaterial = {};
            var id = normalDiv.attributes["materialId"].nodeValue;
            if (id === "null") {
                // 添加提示
                return;
            }
            id = selectedDiv.attributes["materialId"].nodeValue;
            if (id === "null") {
                // 添加提示
                return;
            }
            normalMaterial["id"] = normalDiv.attributes["materialId"].nodeValue;
            normalMaterial["md5Name"] = normalDiv.attributes["macName"].nodeValue;
            normalMaterial["originName"] = normalDiv.attributes["originName"].nodeValue;
            selectedMaterial["id"] = selectedDiv.attributes["materialId"].nodeValue;
            selectedMaterial["md5Name"] = selectedDiv.attributes["macName"].nodeValue;
            selectedMaterial["originName"] = selectedDiv.attributes["originName"].nodeValue;
            node.data.normalMaterial = normalMaterial;
            node.data.selectedMaterial = selectedMaterial;
        } else if (node.type === 1) {
            var label;
            var nodeData;
            if (typeof(node.nodes) !== "undefined" && node.nodes !== null && node.nodes.length > 0) {
                label = $("#level-two input#level-two-label").val();
                nodeData = level_two_nodes_copy(node, label);
            } else {
                label = $("#level-two input#level-two-label").val();
                var title = $("#level-two input#level-two-title").val();
                var urlDivs = $("#level-two div.content");
                if (typeof(urlDivs) !== "undefined" && urlDivs.length > 0) {
                    var urlDiv = urlDivs[0];
                    var id = urlDiv.attributes["materialId"].nodeValue;
                    var mediaType = urlDiv.attributes["mediaType"].nodeValue;
                    var macName = urlDiv.attributes["macName"].nodeValue;
                    var originName = urlDiv.attributes["originName"].nodeValue;
                    var urlMaterial = {};
                    urlMaterial["id"] = id;
                    urlMaterial["macName"] = macName;
                    urlMaterial["originName"] = originName;
                    nodeData = {
                        "text": label, "type": 1, "nodes": [], data: {
                            "urlMaterial": urlMaterial, "mediaType": mediaType, "label": label,
                            "title": title, "many": node.data.many, "mediaType": node.data.mediaType
                        }
                    };
                    console.log(nodeData);
                } else {
                    nodeData = {
                        "text": label, "type": 1, "nodes": [], data: {
                            "urlMaterial": null, "label": label,
                            "title": title, "many": node.data.many, "mediaType": 0
                        }
                    };
                }
            }
            $('#wrapper-menu-tree').treeview('updateNode', [node, nodeData, {silent: true}]);
            $("#wrapper-content").empty();
        } else if (node.type === 2) {
            var label = $("#content input#content-label").val();
            var title = $("#content input#content-title").val();
            var materials = [];
            $("#content").find("li").each(function () {
                var macName = $(this).attr("macName");
                var originName = $(this).attr("originName");
                var materialId = $(this).attr("materialId");
                var material = {"id": materialId, "md5Name": macName, "originName": originName};
                materials.push(material);
            })
            var nodeData = {
                "text": label,
                "type": node.type,
                "data": {
                    "label": label,
                    "title": title,
                    "materials": materials,
                    "mediaType": node.data.mediaType
                }
            };
            $('#wrapper-menu-tree').treeview('updateNode', [node, nodeData, {silent: true}]);
            $("#wrapper-content").empty();
        }
    });

}

function level_two_nodes_copy(selectedNode, label) {
    var nodes = selectedNode.nodes;
    var nodeArray = [];
    for (var i = 0; i < nodes.length; i++) {
        var node = nodes[i];
        var materials = node.data.materials;
        var materialsArray = [];
        if (typeof (materials) !== "undefined" && materials != null && materials.length > 0) {
            for (var j = 0; j < materials.length; j++) {
                var tempMaterial = materials[j];
                var material = {
                    "id": tempMaterial.id,
                    "md5Name": tempMaterial.md5Name,
                    "originName": tempMaterial.originName
                };
                materialsArray.push(material);
            }
        }
        var tempNode = {
            "type": node.type,
            "text": node.text,
            "data": {
                "label": node.data.label,
                "mediaType": node.data.mediaType,
                "title": node.data.title,
                "materials": materialsArray
            }
        };
        nodeArray.push(tempNode);
    }


    return {
        "text": label,
        "type": 1,
        "nodes": nodeArray,
        data: {
            label: label,
            title: null,
            urlMaterial: null,
            "many": node.data.many,
            "mediaType": node.data.mediaType
        }
    };
}

// 添加元素
var isEdit;

function material_add() {
    $("#operation_add").click(function () {
        isEdit = false;
        $("#selectMaterial").modal("show");
    });
}

// 编辑元素
function content_edit() {
    $("button#operation_edit").click(function () {
        isEdit = true;
        $("#selectMaterial").modal("show");
    });
}

// 删除元素
function material_delete() {
    $("button#operation_delete").click(function () {
        $(".item-active").remove();
        $("#operation_delete").addClass("disabled");
        var node = get_selected_node();
        if (node.type === 1) {
            $("#operation_add").removeClass("disabled");
            node.data.mediaType = 0;
        } else if (node.type === 2) {
            alert($("#content ul").find("li").length === 0)
        }
    });
}

function get_selected_node() {
    var nodes = $("#wrapper-menu-tree").treeview('getSelected');
    return nodes[0];
}

// 保存节目
function save_program() {
    $("#save_program").click(function () {
        var node = $("#wrapper-menu-tree").treeview("findNodes", [0, "type"]);
        $("#saveProgram").modal("show");
        $("#save_program_button").click(function () {
            var programName = $("input#programName").val();
            var tip = $("#save_program_tip");
            if (programName.trim() === "") {
                tip.text("节目名称不能为空");
                return;
            }
            $.ajax({
                "url": "/program/programExist",
                "data": {"programName": programName},
                "success": function (data) {
                    if (!data) {
                        $.ajax({
                            "url": "/program/saveProgram",
                            "type": "post",
                            "data": {"programName": programName, "program": JSON.stringify(node)},
                            "success": function (aaaa) {
                                console.log(aaaa);
                            }
                        })
                    } else {
                        tip.text("节目名称不能重复");
                    }
                }
            })
        });
    });
}

