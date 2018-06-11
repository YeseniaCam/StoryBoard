
function confirmf(user){
    if (confirm('¿Realmente deseas eliminar al Usuario '+user+'?')) {
        Redirect("./Eliminar?username="+user);
    }

}

function Redirect(URL){
    window.location.assign(URL)
}

