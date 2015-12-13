/*function delVoucher(vouchId) {
    $.ajax({
        type: 'DELETE',
        url: '<c:url value="http://localhost:8080/delete/voucher/"/>' + vouchId,
    }).done(function() {
        location.reload();
    });
}


$('#btnSave').click(function () {
    if ($('#vouchId').val() == '')
        addVoucher();
    else
        updateVoucher($('#vouchId').val(), $('#country').val(),
            $('#price').val(), $('#discaunt').val());
    return false;
});


function updateVoucher(vouchId, country, price, discaunt) {
    console.log('updateVoucher');
    $('#vouchId').val() == ''
    $.ajax({
        type: 'PUT',
        contentType: 'application/json',
        url: '<c:url value="update/voucher"/>'+ "/" + vouchId  + "/" + country + "/" +price + "/" +discaunt,
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Vourcher updated successfully');

        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('updateVoucher error: ' + textStatus);
        }
    });
}
    */