$(document).ready(function() {
    $('#summernote').summernote({ height: 450});
});

function getdata() {
    $('#texttoshow').val($('#summernote').summernote('code'));
    return true
}


function Redirect(URL){
    window.location.assign(URL)
}