
function CheckUser(SendToWhere){
    var user = document.getElementsByName("username")[0].value;
    var password = document.getElementsByName("password")[0].value;
    var xhttp = new XMLHttpRequest();
    xhttp.open("GET", "./xml/Usuarios.xml", false);
    xhttp.setRequestHeader("Content-Type", "text/xml");
    xhttp.send(null);
    var TheDocument = xhttp.responseXML;
    var Customers = TheDocument.childNodes[0];
    for (var i = 0; i < Customers.children.length; i++){

        var Customer = Customers.children[i];
        var Name = Customer.getElementsByTagName("Username")[0].textContent;
        
        if (Name == user) {
            if (password == Customer.getElementsByTagName("Password")[0].textContent) {
                var Type = Customer.getElementsByTagName("Tipo")[0].textContent;
                Redirect(SendToWhere+"?user="+user+"&type="+Type);
                return 0;
            }
            state = "<i class='fas fa-times-circle' style='color:red;'></i>     Contraseña Incorrecta";
            break;
        }
    }
    if(i == Customers.children.length){
        state = "<i class='fas fa-times-circle' style='color:red;'></i>     Error de Inicio de Sesión.<br/> Checar Usuario y/o Contraseña";
    }
    save(state)    
}

function Redirect(URL){
    window.location.assign(URL)
}


function save(mensaje) {
    $("#dialogmessage").html(mensaje);
    $( "#dialog-message" ).dialog({
        modal: true,
        buttons: {
            Ok: function() {
            $( this ).dialog( "close" );
            }
        }
    });
}