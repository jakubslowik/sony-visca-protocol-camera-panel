$(function () {
    // ----------------------------------- Position

    var viscaPositionDataPacker = function (direction) {
        var data = {
            'direction': direction,
            'tiltSpeed': $('#tiltSpeed').eq(0).val(),
            'panSpeed': $('#panSpeed').eq(0).val(),
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        return data;
    };

    $('#viscaPositionAbsolute').on('click', function () {
        var data = viscaPositionDataPacker("ABSOLUTE");
        postAPI('/position', data);
    });

    $('#viscaPositionPos').on('click', function () {
        var data = viscaPositionDataPacker("POS");
        postAPI('/position', data);
    });

    $('#viscaPositionStop').on('click', function () {
        var data = viscaPositionDataPacker("STOP");
        postAPI('/position', data);
    });

    $('#viscaPositionUpLeft').on('click', function () {
        var data = viscaPositionDataPacker("UPLEFT");
        postAPI('/position', data);
    });

    $('#viscaPositionUp').on('click', function () {
        var data = viscaPositionDataPacker("UP");
        postAPI('/position', data);
    });

    $('#viscaPositionUpRight').on('click', function () {
        var data = viscaPositionDataPacker("UPRIGHT");
        postAPI('/position', data);
    });

    $('#viscaPositionLeft').on('click', function () {
        var data = viscaPositionDataPacker("LEFT");
        postAPI('/position', data);
    });
    $('#viscaPositionRight').on('click', function () {
        var data = viscaPositionDataPacker("RIGHT");
        postAPI('/position', data);
    });

    $('#viscaPositionDownLeft').on('click', function () {
        var data = viscaPositionDataPacker("DOWNLEFT");
        postAPI('/position', data);
    });

    $('#viscaPositionDown').on('click', function () {
        var data = viscaPositionDataPacker("DOWN");
        postAPI('/position', data);
    });

    $('#viscaPositionDownRight').on('click', function () {
        var data = viscaPositionDataPacker("DOWNRIGHT");
        postAPI('/position', data);
    });

    // ----------------------------------- Zoom

    $('#viscaZoomPos').on('click', function () {
        var data = {
            'zoom': 'POS',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });

    $('#viscaZoomStop').on('click', function () {
        var data = {
            'zoom': 'STOP',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });

    $('#viscaZoomTele').on('click', function () {
        var data = {
            'zoom': 'TELE',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });

    $('#viscaZoomWide').on('click', function () {
        var data = {
            'zoom': 'WIDE',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/zoom', data);
    });

    $('#viscaHome').on('click', function () {
        var data = {
            'command': 'HOME',
            'destinationAdr': $('#destinationAddress').eq(0).val()
        };
        postAPI('/other', data);
    });

    $('#viscaSendMacro').on('click', function () {
        var data = {
            'macro': $('#viscaMacro').eq(0).val()
        };
        postAPI('/macro', data);
    });
});

function postAPI(endpoint, data) {
    $.ajax({
        type: 'POST',
        url: '/api' + endpoint,
        data: data,
        success: function (msg) {
            $('#response').val(msg);
        }
    });
}