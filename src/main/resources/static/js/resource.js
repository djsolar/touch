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

    $("ol#achieve_manage li").click(function () {
        $(this).parent().children().removeClass("folder-active");
        $(this).addClass("folder-active");
    });

    $("ol#achieve_manage li:first-child").addClass("folder-active");
});

function add_achieve() {
    $.ajax({
        type: 'post',
        url: '/resource/addAchieve',
        data: $("#achieve_form_data").serialize(),
        success: function (data) {
            if (data) {
                window.location.reload();
            }
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

function upload_material() {

    $('#fileupload').fileupload({
        dataType: 'json',
        done: function (e, data) {
            $.each(data.result.files, function (index, file) {
                $('<p/>').text(file.name).appendTo(document.body);
            });
        }
    });
}