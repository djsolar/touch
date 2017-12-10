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

    $('#fileupload').fileupload({
        dataType: 'json',
        formData: {"achieveId": $(".folder-filter #achieve_manage li.folder-active").attr("id")},
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
