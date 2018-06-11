$(document).ready(function(){
	var user=$("#containerStorys").attr('ref');
	$.ajax({
		url:"./Usuarios/"+user+"/AlumnoStory.xml",
		method:"get",
		data:{},
		success:function(response){
			displayStoryBoards(response);
		},
		dataType:"xml"
	});

	
})


function displayStoryBoards(response){
	$(response).find('AlumnoStory').each(function(index,value){
                    var story=$(this).find("storyboard").text();
                    var profesor=$(this).find("profesor").text();
                    var calificacion=$(this).find("calificacion").text();
                    var msgt=$(this).find("mensaje").text()==""?"false":"true";
                    window.messages=$(this).find("mensaje").text();
                    card="<div class='col-3' style='margin-top:10px' >"+
                    	'<div class="card">'+
						'<div class="card-header text-left '+(calificacion>=6?"bg-success":(calificacion==''?"bg-info":"bg-danger"))+'">'+
						'<i class="fas fa-images"></i> '+
						'</div>'+
						'<div class="card-body text-center">'+
						'<h5 class="card-title">'+story+'</h5>'+
						'<p class="card-text"><i class="fas fa-image" style=" font-size:3em;"></i></p>'+
						'<p class="card-text">Profesor: <span>'+profesor+'</span></p>'+
						'<p class="card-text">Calificacion: '+(calificacion==''?'No evaluado':calificacion)+'</p>'+
						'</div>'+
						'<div class="card-footer text-muted text-center">'+
						'<button name="openStory" class="btn btn-info" onclick="getStoryBoard(\''+profesor+'\',\''+story+'\',\''+msgt+'\');" > <i class="fas fa-pencil-alt"></i> </button>'+
						'</div>'+
						'</div>'+
						'</div>';
						if ($(this).find("tipo").text()=='StoryBoard')
                  			$('#containerStorys .row').append(card);
                });
}







function getStoryBoard(profesor,story,msgt){
	  $.ajax({
    		url:"./Usuarios/"+profesor+"/base.xml",
    		method:"get",
    		data:{},
    		success:function(response){
    			displayStoryBoard(response,story,msgt);
    		},
    		dataType:"xml"
    });
}

function displayStoryBoard(response,story,msgt){
	
	$(response).find('StoryBoard').each(function(){
		if($(this).find("Nombre").text()==story){
			generateModalStory(this,msgt);
		}
	})
}

function generateModalStory(who,msgt){
	window.slidei=0;
	modal='<div class="modal" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-lg" role="document" style="min-width: 76%;">'+
	    '<div class="modal-content">'+
	      '<div class="modal-header">'+
	        '<h5 class="modal-title" align="center">'+$(who).find('Nombre').text()+'</h5>'+
	        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
	          '<span aria-hidden="true">&times;</span>'+
	        '</button>'+
	      '</div>'+
	      '<div class="modal-body">'+
	        	"<div class='row'>"+
		        "<div class='col-sm-1 my-auto'>"+
		        "<button id='bprev' class='btn btn-info'>"+
		        "<i class='fas fa-arrow-left'></i>"+
		        "</button>"+
		        "</div>"+
		        "<div class='col-sm-10 my-auto'>"+
		        "<canvas id='canvas'style='border:1px solid black;' width='820' height='550'></canvas>"+
		        "</div>"+
		        "<div class='col-sm-1 my-auto'>"+
		        "<button id='bsig' class='btn btn-info'>"+
		        "<i class='fas fa-arrow-right'></i>"+
		        "</button>"+
		        "</div>"+
		        "</div>"+
		        '<form action="./SendNotes" method="post" id="sendNotes">'+
					'<div class="row">'+
						'<div class="col-sm-12">'+
						"<input type='hidden' name='story' value='"+$(who).find('Nombre').text()+"' />"+
							"<br>"+
							"<h4 align='center'>Comentarios</h4>"+
							"<br>"+
							'<textarea name="notes" id="notes" cols="70" rows="5" style="display: block;margin-left: auto;margin-right: auto;" '+(msgt=='false'?"":"disabled")+'>'+ window.messages+'</textarea>'+
						'</div>'+
					'</div>'+
				'</form>'+

	      '</div>'+
	      '<div class="modal-footer">'+
	        '<button type="submit" form="sendNotes" class="btn btn-primary" '+(msgt=='false'?"":"disabled")+'>Enviar Comentarios</button>'+
	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'+
	      '</div>'+
	    '</div>'+
	  '</div>'+
	'</div>';



	
	$('#modalCont').html(modal);

		window.canvas = new fabric.Canvas('canvas');
	    window.canvas.loadFromJSON($(who).find('slides slide:first-child').text(), window.canvas.renderAll.bind(window.canvas), function(o, object) {
		    object.set('selectable', false);
		});
	 

	 $('.modal').modal('show');

	$('#bsig').on('click',function(){
		lengths=$(who).find('slides slide').length;
		if (window.slidei==lengths-1) {
			alert('Ya no hay mas que ver');
			return;
		}
		slidei++;
		
		
		window.canvas.loadFromJSON($(who).find('slides slide:eq('+window.slidei+')').text());

	})

	$('#bprev').on('click',function(){
		if (window.slidei==0) {
			alert('Esta es la primera pagina');
			return;
		}
		slidei--;		
		window.canvas.loadFromJSON($(who).find('slides slide:eq('+window.slidei+')').text());

	})
}
