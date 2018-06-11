$(document).ready(function() {
    $('#summernote').summernote({ height: 450});
});

function getdata() {
    $('#textosummer').val($('#summernote').summernote('code'));
    alert('Hola')
    return true
}


function Redirect(URL){
    window.location.assign(URL)
}