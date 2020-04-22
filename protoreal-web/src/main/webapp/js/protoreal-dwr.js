var selectedItem;
var selectedPage;
var treeNode = {};

function handler(msg, exc) {
  console.log("Error message is: " + msg + " - Error Details: " + dwr.util.toDescriptiveString(exc, 2));
}

dwr.engine.setErrorHandler(handler);

function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("text", ev.target.id);
}

function dragEnter(e) {
    e.target.style.border = "3px solid #ccccc3";
}

function dragLeave(e) {
    e.target.style.border = "";
}

function drop(ev) {
    ev.preventDefault();
	var source = ev.dataTransfer.getData("text");
	ev.target.classList.remove('over');
	var parent = selectedItem;
	
	Controller.criar(source, parent, '', function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue(data['parent'], data['view']);
		iniciarEditar(data['id']);
	});
}

function dropCompInside(ev) {
    ev.preventDefault();
	var source = ev.dataTransfer.getData("text");
	ev.target.classList.remove('over');
	var parent = ev.target.id;
	
	Controller.criar(source, parent, '', function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue(data['parent'], data['view']);
		iniciarEditar(data['id']);
		
		if (typeof data["charts"] !== 'undefined') {
			var charts = data["charts"].split(";");
			for (i = 0; i < charts.length; i++){
				drawChart(charts[i]);
			}
		}
		
		if (typeof data["textEditors"] !== 'undefined') {
			var textEditors = data["textEditors"].split(";");
			for (i = 0; i < textEditors.length; i++){
				drawTextEditor(textEditors[i]);
			}
		}
		
		if (typeof data["autoCompletes"] !== 'undefined') {
			var autoCompletes = data["autoCompletes"].split(";");
			for (i = 0; i < autoCompletes.length; i++){
				typeHead(autoCompletes[i]);
			}
		}
		
		if (typeof data["chartId"] !== 'undefined') {
			drawChart(data["chartId"]);	
		}
		
		if (typeof data["textEditorId"] !== 'undefined') {
			drawTextEditor(data["textEditorId"]);	
		}
		
		if (typeof data["autoCompleteId"] !== 'undefined') {
			typeHead(data["autoCompleteId"]);	
		}
		
		Controller.obter(selectedPage, function(data){
			dwr.util.setEscapeHtml(false);
			dwr.util.setValue("treeview", data);
		});
	});
	ev.stopPropagation();
}

function dropComponent(ev, parent, pos) {
    ev.preventDefault();
    var source = ev.dataTransfer.getData("text");
    ev.target.classList.remove('over');
    
	FormController.criar(parent, pos, function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue(ev.target.id, data);
	});
    
    return false;
}

function iniciarEditar(value){

	selectedItem = value;
	Controller.editar(value, function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue(data['parent'], data['view']);
		dwr.util.setValue('propComponents', data["properties"]);
		selectedPage = data["selectedPage"];
		
		Controller.obter(selectedPage, function(data){
			dwr.util.setEscapeHtml(false);
			dwr.util.setValue("treeview", data);
		});
	});
}

function componentRemove(id){
	    Controller.remover(id, dwr.util.getValues(id), function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue(data['parent'], data['view']);

		if (typeof data["charts"] !== 'undefined') {
			var charts = data["charts"].split(";");
			for (i = 0; i < charts.length; i++){
				drawChart(charts[i]);
			}
		}
		
		if (typeof data["textEditors"] !== 'undefined') {
			var textEditors = data["textEditors"].split(";");
			for (i = 0; i < textEditors.length; i++){
				drawTextEditor(textEditors[i]);
			}
		}
		
		if (typeof data["autoCompletes"] !== 'undefined') {
			var autoCompletes = data["autoCompletes"].split(";");
			for (i = 0; i < autoCompletes.length; i++){
				typeHead(autoCompletes[i]);
			}
		}
		
		Controller.obter(selectedPage, function(data){
			dwr.util.setEscapeHtml(false);
			dwr.util.setValue("treeview", data);
		});
	});  	  	
}

function send(id, controlleName){
	
	var controlType = id.substr(3);
    Controller.edit(id, controlleName, dwr.util.getValues(id), function(data){
	dwr.util.setEscapeHtml(false);
	dwr.util.setValue(data['parent'], data['view']);
	
		if (controlType == 'Chart'){
			drawChart(data['id']);
		}
		
		if (controlType == 'TextEditor'){
			drawTextEditor(data['id']);
		}
		
		if (controlType == 'AutoComplete'){
			typeHead(data['id']);
		}
	
		Controller.obter(selectedPage, function(data){
			dwr.util.setEscapeHtml(false);
			dwr.util.setValue("treeview", data);
		});
    });  	  	
}