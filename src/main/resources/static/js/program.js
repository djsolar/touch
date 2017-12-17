function display_selected_node(data) {
    console.log(data);
    var type = data.type;
    if (type === 0) {
        display_level_one(data)
    } else if (type === 1) {
        display_level_two(data);
    } else if (type === 2) {
        display_content(data);
    }
}

function add_level_one_click() {
    $("#level-one .display-img>img").click(function () {
        $("#selectMaterial").modal("show");
    })
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

function material_select() {
    $("#selectMaterial").on("show.bs.modal", function () {
        $('#materialDataTables').DataTable({
            "pagingType": "simple_numbers",//设置分页控件的模式
            searching: false,//屏蔽datatales的查询框
            aLengthMenu: [10],//设置一页展示10条记录
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
            "iDisplayLength": 5,
            "processing": false,
            "bAutoWidth": false,
            "ajax": "/resource/getMaterialData",
            "columns": [
                {"data": "macName",
                    render: function (data, type, row) {
                        console.log("data = " + data);
                        console.log("type = " + type);
                        console.log(row);
                        var result = template("material-list", {"macName": data});
                        console.log(result);
                        return result;
                    }}/*,
                {"data": "second",
                    render: function (data, type, row) {
                        console.log("data = " + data);
                        console.log("type = " + type);
                        console.log(row);
                        var result = template("material-list", {"material": data});
                        console.log(result);
                        return result;
                    }}*/

            ]
        });
    })
}