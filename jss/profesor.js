
function confirmvideo(video){
    if (confirm('¿Realmente deseas eliminar el video '+video+'?')) {
        Redirect("./EliminarVideo?video="+video);
    }

}
function confirmtext(texto){
    if (confirm('¿Realmente deseas eliminar el texto '+texto+'?')) {
        Redirect("./EliminarTexto?texto="+texto);
    }

}

function confirmstory(texto){
    if (confirm('¿Realmente deseas eliminar el StoryBoard '+texto+'?')) {
        Redirect("./EliminarStory?story="+texto);
    }

}
function confirmimagen(imagen){
    if (confirm('¿Realmente deseas eliminar el StoryBoard '+imagen+'?')) {
        Redirect("./EliminarImagen?imagen="+imagen);
    }

}

function confirmaudio(audio){
    if (confirm('¿Realmente deseas eliminar el StoryBoard '+audio+'?')) {
        Redirect("./EliminarAudio?audio="+audio);
    }

}

function modifystory(name) {
    Redirect("./Modifystoryboard?nombre="+name);
}

function modifytexto(name) {
    Redirect("./ModifyTexto?nombre="+name);
}


function Redirect(URL){
    window.location.assign(URL)
}

