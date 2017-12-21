var tree;
$(function () {
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
    String.prototype.endWith=function(endStr){
        var d=this.length-endStr.length;
        return (d>=0&&this.lastIndexOf(endStr)==d)
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
        $("#operation_edit").removeClass("disabled");
        $("#operation_look").removeClass("disabled");
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
        $("#operation_edit").removeClass("disabled");
        $("#operation_look").removeClass("disabled");
        $("#operation_delete").removeClass("disabled");
    });
}

function content_edit() {
    $("button#operation_edit").click(function () {
        $("#selectMaterial").modal("show");
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
    if (node.data.url != null) {
        $("#operation_add").addClass("disabled");
        $("#operation_up").addClass("disabled");
        $("#operation_down").addClass("disabled");
    } else {
        $("#operation_up").addClass("disabled");
        $("#operation_down").addClass("disabled");
        if (typeof(node.nodes) !== "undefined" && node.nodes != null && node.nodes.length > 0) {
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

function enable_all_button() {
    $("#operation_edit").addClass("disabled");
    $("#operation_look").addClass("disabled");
    $("#operation_delete").addClass("disabled");

    $("#operation_add").removeClass("disabled");
    $("#operation_up").removeClass("disabled");
    $("#operation_down").removeClass("disabled");
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
        var type = $(".item-active").attr("mediaType");
        console.log("mediaType", type);

        if (typeof(table) !== "undefined") {
            console.log("destroy");
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
                        console.log("data = " + data);
                        console.log("type = " + type);
                        console.log(row);
                        var result = template("material-list", {"material": data});
                        console.log(result);
                        return result;
                    }
                },
                {
                    "data": "second",
                    render: function (data, type, row) {
                        console.log("data = " + data);
                        console.log("type = " + type);
                        console.log(row);
                        var result = template("material-list", {"material": data});
                        console.log(result);
                        return result;
                    }
                }

            ]
        });
        table.on('draw', function () {
            console.log('Redraw occurred at: ' + new Date().getTime());
            $("#materialDataTables tbody tr td img.material-select").click(function () {
                var imgSrc = $(this).attr("src");
                $(".item-active img").attr("src", imgSrc);
                $("#selectMaterial").modal("hide");
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

        data = {
            "normalMaterial": null,
            "selectedMaterial": null
        };
        var new_node = {"text": "Level-1", "type": 0, "data": data};
        var nodes = $('#wrapper-menu-tree').treeview('getSelected');
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
                        "label": "levelTwoNew", "title": "levelTwoNew",
                        "url": null, "contentBeans":
                            null, "many": false
                    }
            };
            $('#wrapper-menu-tree').treeview('addNode', [level_two_node, nodes[0], undefined, {silent: true}]);
        } else if (node.type === 1) {
            var new_nodes = [];
            if (node.data.url !== null) {
                var url = node.data.url;
                var title = node.data.title;
                var type = get_file_type(url);
                var first_node = {
                    "text": title, "type": 2, data:
                        {
                            "label": title, "title": null,
                            "paths":[url],
                            "type": type
                        }
                };
                node.data.url = null;
                new_nodes.push(first_node);
            }
            var content_node = {
                "text": "contentNew", "type": 2, data:
                    {
                        "label": "contentNew", "title": null,
                        "paths":[],
                        "type": 0
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
        $('#wrapper-menu-tree').treeview('removeNode', [ node, { silent: true } ]);
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
        $('#wrapper-menu-tree').treeview('removeNode', [ node, { silent: true } ]);
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
        console.log(index);
        alert(JSON.stringify(new_node));
    });
}

function down_tail() {
    
}

