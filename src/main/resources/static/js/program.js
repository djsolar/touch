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
    material_select();
    // 添加删除节点
    node_delete();
    node_sibling_add();
});

function display_selected_node(data) {
    console.log(data);
    var type = data.type;
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
    });
}

function add_level_two_click() {
    $("#level-two p").click(function () {
        $(this).siblings().removeClass("item-active");
        $(this).addClass("item-active");
    });
}

function clear_content() {
    var contentNode = $("#wrapper-content");
    contentNode.empty();
}

function add_content_click() {
    $("#content ul li").click(function () {
        $(this).siblings().removeClass("item-active");
        $(this).addClass("item-active");
    });
}

function content_edit() {
    $("button#edit_content").click(function () {
        $("#selectMaterial").modal("show");
    });
}


function display_level_two(data) {
    var levelTwoHtml = template("level-two-template", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(levelTwoHtml);
}

function display_content(data) {
    var result = template("content", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(result);
}

function display_level_one(data) {
    var levelOneHtml = template("level-one-template", data);
    var contentNode = $("#wrapper-content");
    contentNode.empty();
    contentNode.append(levelOneHtml);
    add_level_one_click()
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
            "normalPic":
                "2ccdf4797de63263d42e115ccd70c9b2.png",
            "selectedPic":
                "ac7313f4cebc32d718d2b24fd1162e55.png",
            "twoContent":
                null,
            "url":
                null
        };

        var new_node = {"text": "Level-1", "type": 0, "data": data};
        var nodes = $('#wrapper-menu-tree').treeview('getSelected');
        var index;
        var parentNode;
        if (nodes.length > 0) {
            index = nodes[0].index + 1;
            parentNode =$('#wrapper-menu-tree').treeview('getParents', nodes[0]);
        }
        $('#wrapper-menu-tree').treeview('addNode', [new_node, parentNode, index, {silent: true}]);
    });
}
