$(document).ready(function() {
    $('#summernote').summernote({ height: 450});

    $('#savetext').click(function (){
        x = getdata();
        $('#textosummer').val(x);
        return true;
    });
});

function getdata() {
    return $('#summernote').summernote('code'); 
}

function Redirect(URL){
    window.location.assign(URL)
}