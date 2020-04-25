function allowDrop(ev) {
  ev.preventDefault();
}

function drag(ev) {
  ev.dataTransfer.setData("text", ev.target.id);
}

function drop(ev) {
	var source = ev.dataTransfer.getData("text");
	ev.target.classList.remove('over');
	
	Controller.create(source, ev.target.id, function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('container-designer', data['data']);
		dwr.util.setValue('componentSelected', data['components']);
	});
  
	ev.stopPropagation();
}

function configure(){
	
	var sel = $('select[name ="componentSelected"]');
	
	Controller.edit(sel.val(), function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('properties', data['data']);
	});
	
}