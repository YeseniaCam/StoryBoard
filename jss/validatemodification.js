$(document).ready(function(){
    var correo_alt = $( "input[name='correo']").val();
    var usuario_alt = $( "input[name='usuario']").val();
    $( "input[name='usuario']").hide();
    $( "#form1" ).submit(function( event ) {
        var x = ejemplo(usuario_alt,correo_alt);
        if (!x) {
            event.preventDefault();
        }

    });

    function ejemplo(usuario_alt,correo_alt) {
    
        var nombre = $( "input[name='nombre']").val();
        var correo = $( "input[name='correo']").val();
        var usuario = $( "input[name='seconduser']").val();
        var password1 = $( "input[name='contrasena1']").val();
        var password2 = $( "input[name='contrasena2']").val();
        var mensaje = ""
        if (nombre=="") {
            mensaje = "<i class='fas fa-times-circle' style='color:red;'></i>   Ingresa tu Nombre <br/>";
        }
        if (correo=="") {
            mensaje = mensaje + "<i class='fas fa-times-circle' style='color:red;'></i>   Ingresa tu Correo <br/>";
        }
        if (usuario=="") {
            mensaje = mensaje + "<i class='fas fa-times-circle' style='color:red;'></i>   Ingresa un Nombre de Usuario <br/>";
        }
        if (password1=="") {
            mensaje = mensaje + "<i class='fas fa-times-circle' style='color:red;'></i>   Ingresa una Contraseña <br/>";
        }
        if (password2=="") {
            mensaje = mensaje + "<i class='fas fa-times-circle' style='color:red;'></i>   Reingresa la Contraseña <br/>";
        }
        
        if (mensaje=="") {
            var y = checkexistingUser(usuario,correo,usuario_alt,correo_alt);
            if(y[0]!=""){
                mensaje = mensaje + y[0];
            }
            if(y[1]!=""){
                mensaje = mensaje + y[1];
            }
            if (y[0]=="" && y[1]==""){
                if(password1 == password2){
                    return true
                }
                else{
                    mensaje = mensaje + "Las contraseñas no coinciden";
                }
            }
        }
        save(mensaje)
        return false;
    }
    
    
    function checkexistingUser(Us,Cor,jumpus,jumpco){
        var xhttp = new XMLHttpRequest();
        xhttp.open("GET", "./xml/Usuarios.xml", false);
        xhttp.setRequestHeader("Content-Type", "text/xml");
        xhttp.send(null);
        var correo = "";
        var user = "";
        var TheDocument = xhttp.responseXML;
        var Customers = TheDocument.childNodes[0];
        for (var i = 0; i < Customers.children.length; i++){
            var Customer = Customers.children[i];
            var Name = Customer.getElementsByTagName("Username")[0].textContent;
            var email = Customer.getElementsByTagName("Correo")[0].textContent;
            if (Us == Name){
                if(jumpus==Us){
                    continue;
                }
                user = "Ya existe ese Nombre de Usuario";
                return [user,correo];
            }
            if (Cor == email){
                if(jumpco==Cor){
                    continue;
                }
                correo = "Ya esta registrado ese correo";
                return [user,correo];
            }
    
        }
        return ["",""]
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
  });
