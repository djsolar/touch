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


    $("ol#achieve_manage li:first-child").addClass("folder-active");
    var achieveId = $("li.folder-active").attr("id");
    get_achieve(achieveId);
    add_achieve_click();
    file_upload();
    upload_modal_close();
    file_type_filter();

});

function file_type_filter() {
    $("#material_type_photo").click(function () {
        var achieveId = $(".folder-active").attr("id");
        $.ajax({
            url: "/resource/getMaterialByAchieveAndType",
            data: {
                "achieveId": achieveId,
                "type": 1
            },
            success: function (data) {
                var result = template("resource-display-template", {"data": data});
                var resourceInfo = $(".resource-display .resource-info");
                resourceInfo.empty();
                resourceInfo.append(result);
                add_material_click();
            }
        })
    });

    $("#material_type_txt").click(function () {
        var achieveId = $(".folder-active").attr("id");
        $.ajax({
            url: "/resource/getMaterialByAchieveAndType",
            data: {
                "achieveId": achieveId,
                "type": 2
            },
            success: function (data) {
                var result = template("resource-display-template", {"data": data});
                var resourceInfo = $(".resource-display .resource-info");
                resourceInfo.empty();
                resourceInfo.append(result);
                add_material_click();
            }
        })

    });
}


function upload_modal_close() {
    $("#uploadMaterial").on("hidden.bs.modal", function () {
        var achieveId = $(".folder-active").attr("id");
        alert(achieveId);
        $.ajax({
            url: "/resource/getAchieveMaterial",
            data: {"id": achieveId},
            success: function (data) {
                var result = template("resource-display-template", {"data": data});
                var resourceInfo = $(".resource-display .resource-info");
                resourceInfo.empty();
                resourceInfo.append(result);
                add_material_click();
            }
        })
    });
}

function file_upload() {
    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $("tr:has(td)").remove();
            $.each(data.result, function (index, file) {

                $("#uploaded-files").append(
                    $('<tr/>')
                        .append($('<td/>').text(file.fileName))
                        .append($('<td/>').text(file.fileSize))
                        .append($('<td/>').text(file.fileType))
                )
            });
        },

        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('#progress .progress-bar').css(
                'width',
                progress + '%'
            ).text(progress + "%");
            console.log("progress: " + progress)
        }
    });
    $("#fileupload").bind("fileuploadsubmit", function (e, data) {
        var id = $(".folder-active").attr("id");
        data.formData = {"achieveId": id};
    });
}

function add_achieve() {
    $.ajax({
        type: 'post',
        url: '/resource/addAchieve',
        data: $("#achieve_form_data").serialize(),
        success: function (data) {
            if (data) {
                $("#addAchieve").modal("hide");
                get_achieve();
            }
        }
    });
}

function add_achieve_click() {
    $("ol#achieve_manage li").click(function () {
        $(this).parent().children().removeClass("folder-active");
        $(this).addClass("folder-active");
        var achieveId = $(this).attr("id");
        $.ajax({
            url: "/resource/getAchieveMaterial",
            data: {"id": achieveId},
            success: function (data) {
                var result = template("resource-display-template", {"data": data});
                var resourceInfo = $(".resource-display .resource-info");
                resourceInfo.empty();
                resourceInfo.append(result);
                add_material_click();
            }
        })
    });
}

function add_material_click() {
    $(".resource-display li").click(function () {
        if ($(this).hasClass("display-active")) {
            $(this).removeClass("display-active");
        } else {
            $(this).addClass("display-active");
        }
    });
}


function delete_achieve() {
    var id = $(".folder-active").attr("id");
    $.ajax({
        type: 'post',
        url: '/resource/deleteAchieve',
        data: {"id": id},
        success: function (data) {
            if (data) {
                window.location.reload();
            }
        }
    });
}

function get_achieve() {
    var achieveId = $("li.folder-active").attr("id");
    $.ajax({
        url: "/resource/getAchieveMaterial",
        data: {"id": achieveId},
        success: function (data) {
            var result = template("resource-display-template", {"data": data});
            var resourceInfo = $(".resource-display .resource-info");
            resourceInfo.empty();
            resourceInfo.append(result);
            add_material_click();
        }
    });
}

function delete_material() {
    var achieveId = $("li.folder-active").attr("id");
    var ids = [];
    $(".resource-display li.display-active").each(function () {
        var id = $(this).attr("material");
        ids.push(id);
    });
    console.log(ids);
    if (ids.length === 0) {
        window.location.reload();
        return;
    }
    $.ajax({
        type: 'post',
        url: '/resource/deleteMaterial',
        data: {"ids": ids},
        success: function (data) {
            if (data) {
                $("#deleteMaterial").modal("hide");
                get_achieve(achieveId);
            }
        }
    })
}
