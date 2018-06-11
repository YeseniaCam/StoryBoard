$(document).ready(function(){
    var canvas = new fabric.Canvas('canvas');
    var slides = new Array();
    var index = 0;
    var maxindex = 0;

    $('.iconos img[draggable="true"]').on('dragstart',function(event){
       
    window.draggimagesrc=this.src;
    })

    $('.canvas-container').on('dragover', function(event){
      event.preventDefault();  
      event.stopPropagation();
      var rect = window.canvas.getBoundingClientRect();
      window.draggX=(event.clientX-rect.left);
      window.draggY=(event.clientY-rect.top);
    });

    $('.canvas-container').on('drop', function(event){
      event.preventDefault();  
    event.stopPropagation();
      var ruta = window.draggimagesrc;
      fabric.Image.fromURL(ruta, function(myImg) {
      var img1 = myImg.set({ left: window.draggX, top: window.draggY ,width:150,height:150});
      canvas.add(img1); 
      
        });
    });
    $('body').keydown(function(event){
      var letter = (String.fromCharCode(event.which)); 
   
      if(event.keyCode == 8){
        canvas.remove(canvas.getActiveObject());
        canvas.renderAll();
    };
   })

    $(".containertohide").each(function(){
      $(this).hide();
    });

    $('#bprev').click(function() {
        if (index == 0) {
          alert('Esta es la primer hoja');
        }
        else {  
          slides[index] = JSON.stringify(canvas);
          canvas.clear();
          canvas.renderAll();
          canvas.loadFromJSON(slides[index-1]);
          index = index - 1
        }

        $('#numpa').text("Página: " + (index+1));

    });
    $('#bsig').click(function() {
        if (maxindex==index) {
          maxindex = maxindex + 1;
          slides.push(JSON.stringify(canvas));
          canvas.clear();
          canvas.renderAll();
        }else{
          slides[index] = JSON.stringify(canvas);
          canvas.clear();
          canvas.renderAll();
          canvas.loadFromJSON(slides[index+1]);
        }
        console.log(slides)
        index = index + 1 
        $('#numpa').text("Página: " + (index+1));

    });

    $('#shapecolor').change(function() {
      var activeObject = canvas.getActiveObject();
      activeObject.fill = '#' + $(this).val();
      canvas.renderAll();
    });

    $('#delete').click(function() {
      canvas.remove(canvas.getActiveObject());
      canvas.renderAll();
    });

    $('#clearboard').click(function() {
      canvas.clear();
      canvas.renderAll();
    });
    $('#button1').click(function() {
              var circle = new fabric.Circle({
                radius: 20,
                fill: 'black',
                left: 100,
                top: 100
              });
          canvas.getObjects();
          canvas.add(circle);
          canvas.selection = true;
          canvas.renderAll();
          canvas.calcOffset();

    });
    $('#button2').click(function() {
      var rect = new fabric.Rect({
          left: 100,
          top: 100,
          fill: 'black',
          width: 20,
          height: 20
      });
          canvas.getObjects();
          canvas.add(rect);
          canvas.selection = true;
          canvas.renderAll();
          canvas.calcOffset();

    });
    $('#button3').click(function() {
      var line = new fabric.Line([ 250, 125, 250, 175 ], {
        fill: 'red',
        stroke: 'red',
        strokeWidth: 5,
        selectable: true
      });
          canvas.getObjects();
          canvas.add(line);
          canvas.selection = true;
          canvas.renderAll();
          canvas.calcOffset();
    });

    $('#button4').click(function() {
      texto = $("#textocanvas").val();
      var text = new fabric.Text(texto, { left: 100, top: 100 });
      canvas.add(text);
    });

    $('.iconos').click(function() {
      var ruta = this.getAttribute('name');
      fabric.Image.fromURL(ruta, function(myImg) {
      var img1 = myImg.set({ left: 150, top: 150 ,width:150,height:150});
      canvas.add(img1); 
      });
    });

    $('.showicons').click(function() {
      var expresion = 'div[name='+this.name+']';
      $(expresion).toggle(2000);
    });
    $('#save').click(function (){
        if (slides.length == 0) {
          slides.push(JSON.stringify(canvas));
        }
        if (maxindex==index) {
          slides[index] = JSON.stringify(canvas);
          
        }
        for (let i = 0; i < slides.length; i++) {
          slides[i] = slides [i] + "?";
          
        }
        $('#slides').val(slides);
        return true;
    });
  });

  function Redirect(URL){
    window.location.assign(URL)
  }



