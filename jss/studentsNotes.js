function getComments(name){
	$.ajax({
		url:"./GetNotes",
		method:"get",
		data:{'name':name},
		success:function(response){
			displayComments(response,name);
		},
		dataType:"xml"
	});
}

function displayComments(response,story){
	modal='<div class="modal" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-lg" role="document" style="min-width: 76%;">'+
	    '<div class="modal-content">'+
	      '<div class="modal-header">'+
	        '<h5 class="modal-title" align="center">Notas de alumnos</h5>'+
	        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
	          '<span aria-hidden="true">&times;</span>'+
	        '</button>'+
	      '</div>'+
	      '<div class="modal-body" id="notesContainer">'+

	      '</div>'+
	      '<div class="modal-footer">'+
	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'+
	      '</div>'+
	    '</div>'+
	  '</div>'+
	'</div>';
	$('#modalCont').html(modal);

	$(response).find('Nota').each(function(){

		var alumno=$(this).find("Alumno").text();
		var calif=$(this).find("Calificacion").text();
		var mensaje=$(this).find("Mensaje").text();

		nota='<hr /><div class="row">'+
				'<div class="col-md-8">'+
					'<h4>'+alumno+'</h4>'+
					'<textarea name="note" id="note" cols="70" rows="5" style="display: block;margin-left: auto;margin-right: auto;" disabled>'+mensaje+'</textarea>'+
				'</div>'+
				'<div class="col-md-4">'+
					'<br /><div class="row">'+
						'<div class="col-md-12">'+
							'Calificacion:  <select name="calificacion-'+alumno+'"  id="calificacion-'+alumno+'">'+
								'<option disabled '+(calif!=""?"":"selected")+'></option>';
								for (var i = 0; i < 11; i++) {
									nota+='<option value="'+i+'" '+(calif!=""?(calif==i?"selected":""):"")+'>'+i+'</option>'
								}
							nota+='</select>'+
						'</div>'+
					'</div>'+
					'<br /><br /><div class="row">'+
						'<div class="col-md-12">'+
							'<button name ="" class="btn btn-success mx-auto"  style="display: block;margin-left: auto;margin-right: auto;" onclick="setScore(\''+alumno+'\',\''+story+'\');"><i class="fas fa-check"></i> Calificar</button>'+
						'</div>'+
					'</div>'+
					
				'</div>'+
			'</div>';

		$("#notesContainer").append(nota);


	})

	$('.modal').modal('show');

}


function setScore(to,story){
	grade=$('#calificacion-'+to).val();
	$.ajax({
		url:"./SendGrades",
		method:"get",
		data:{'alumno':to,'story':story,'calificacion':grade},
		success:function(response){
			alert('Calificacion guardada');
		},
		dataType:"text"
	});
}