
$("#nuevogrupo").on('click',function(){
	modal='<div class="modal" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-md" role="document" >'+
	    '<div class="modal-content">'+
	      '<div class="modal-header">'+
	        '<h5 class="modal-title" align="center">Nuevo Grupo</h5>'+
	        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
	          '<span aria-hidden="true">&times;</span>'+
	        '</button>'+
	      '</div>'+
	      '<div class="modal-body">'+
			'<div class="row">'+
				'<div class="col-sm-12">'+
				"<label for='grouphidAdd'>Nombre: </label><br /> "+
				"<input type='text' name='grouphidAdd' id='grouphidAdd' />"+
				"<br /><br />"+
				"<label for='profesorSelAdd'>Profesor: </label><br> "+
				'<select name="profesorSelAdd" id="profesorSelAdd">'+
				'<option disabled></option>'+
				'</select>'+
				'</div>'+
			'</div>'+	
	      '</div>'+
	      '<div class="modal-footer">'+
	        '<button id="newGroupbtn" class="btn btn-primary" >Assignar Profesor</button>'+
	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'+
	      '</div>'+
	    '</div>'+
	  '</div>'+
	'</div>';



	
	$('#modalCont').html(modal);
	$('.modal').modal('show');

	selectProfesorFill("","profesorSelAdd");


	$('#newGroupbtn').on('click',function(){
		prof=$('#profesorSelAdd').val();
		name=$("#grouphidAdd").val();
		$.ajax({
			url:"./GroupActions",
			method:"post",
			data:{'grupo':name,'profesor':prof,'tipo':'0'},
			success:function(response){
				location.reload(true);
			},
			dataType:"text"
		});
		
	})


})


function addAlumns(where){
modal='<div class="modal" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-md" role="document" >'+
	    '<div class="modal-content">'+
	      '<div class="modal-header">'+
	        '<h5 class="modal-title" align="center">'+where+'</h5>'+
	        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
	          '<span aria-hidden="true">&times;</span>'+
	        '</button>'+
	      '</div>'+
	      '<div class="modal-body">'+
			'<div id="alumnsOptions">'+
			"<label>Asignar alumnos: </label><br><br /> "+
				'<table class="table table-dark">'+
					'<thead>'+
						'<tr class="bg-primary">'+
							'<th>Alumno</th>'+
							'<th>Asignado</th>'+
						'</tr>'+
					'</thead>'+
					'<tbody id="alumnsSelect">'+
					'</tbody>'+
				'</table>'+

			'</div>'	
	      '</div>'+
	      '<div class="modal-footer">'+
	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'+
	      '</div>'+
	    '</div>'+
	  '</div>'+
	'</div>';



	
	$('#modalCont').html(modal);
	$('.modal').modal('show');

	alumnsOptions(where);
}

function alumnsOptions(where){
	 $.ajax({
  		url:"./xml/Usuarios.xml",
  		method:"get",
  		data:{},
  		success:function(response){
  			$(response).find('Usuario').each(function(){
				if($(this).find("Tipo").text()=='Estudiante'){
					$("#alumnsSelect").append('<tr><td>'+$(this).find("Nombre").text()+
						'</td><td><input type="checkbox" value="1"  onchange="assignAlumn(this,\''+$(this).find("Nombre").text()+'\',\''+where+'\')" '+
						($(this).find("Grupo").text()==where?"checked":"")+' /></td></tr>');
				}
			})
  		},
  		dataType:"xml"
  });
}


function assignAlumn(val,alumn,group){
	val=($(val).is(':checked') ? 1 : 0);
	$.ajax({
			url:"./GroupActions",
			method:"post",
			data:{'grupo':group,'alumno':alumn,'asignado':val,'tipo':3},
			success:function(response){
			},
			dataType:"text"
		});
}








function updateGroup(name){
	$.ajax({
		url:"./xml/grupos.xml",
		method:"get",
		data:{},
		success:function(response){
			$(response).find('Grupo').each(function(){
				if($(this).find("Nombre").text()==name){
					displayGroupUpdate(this);
				}
			})
			
		},
		dataType:"xml"
	});
}


function displayGroupUpdate(who){

modal='<div class="modal" tabindex="-1" role="dialog">'+
	  '<div class="modal-dialog modal-md" role="document" style="min-width: 76%;">'+
	    '<div class="modal-content">'+
	      '<div class="modal-header">'+
	        '<h5 class="modal-title" align="center">'+$(who).find('Nombre').text()+'</h5>'+
	        '<button type="button" class="close" data-dismiss="modal" aria-label="Close">'+
	          '<span aria-hidden="true">&times;</span>'+
	        '</button>'+
	      '</div>'+
	      '<div class="modal-body">'+
			'<div class="row">'+
				'<div class="col-sm-12">'+
				"<input type='hidden' name='grouphid' id='grouphid' value='"+$(who).find('Nombre').text()+"' />"+
				"<label for='profesorSel'>Profesor: </label> "+
				'<select name="profesorSel" id="profesorSel">'+
				'<option disabled></option>'+
				'</select>'+
				'</div>'+
			'</div>'+

	      '</div>'+
	      '<div class="modal-footer">'+
	        '<button id="asignProfesor"  class="btn btn-primary" >Assignar Profesor</button>'+
	        '<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>'+
	      '</div>'+
	    '</div>'+
	  '</div>'+
	'</div>';



	
	$('#modalCont').html(modal);
	$('.modal').modal('show');

	selectProfesorFill($(who).find('Profesor').text(),"profesorSel");

	$('#asignProfesor').on('click',function(){
		prof=$('#profesorSel').val();
		name=$("#grouphid").val();
		$.ajax({
			url:"./GroupActions",
			method:"post",
			data:{'grupo':name,'profesor':prof,'tipo':1},
			success:function(response){
				location.reload(true);
			},
			dataType:"text"
		});
		
	})
}



function selectProfesorFill(profesor,where){
 	  $.ajax({
  		url:"./xml/Usuarios.xml",
  		method:"get",
  		data:{},
  		success:function(response){
  			$(response).find('Usuario').each(function(){
				if($(this).find("Tipo").text()=='Profesor'){
					$("#"+where).append('<option value="'+$(this).find("Nombre").text()+'" '+($(this).find("Nombre").text()==profesor?"selected":"")+'>'+$(this).find("Nombre").text()+'</option>');
				}
			})
  		},
  		dataType:"xml"
  });
}



function deleteGroup(name){
	if (confirm('Seguro que desea eliminar este grupo?'))
		$.ajax({
			url:"./GroupActions",
			method:"post",
			data:{'grupo':name,'tipo':2},
			success:function(response){
				location.reload(true);
			},
			dataType:"text"
		});
}


