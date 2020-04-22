function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	ev.preventDefault();
	var source = ev.dataTransfer.getData("text");
	ev.target.classList.remove('over');
	
	Controller.create(source, ev.target.id, function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('container-designer', data['data']);
	});
  
}