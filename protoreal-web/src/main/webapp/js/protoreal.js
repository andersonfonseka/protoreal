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
	
	Controller.startEdit(sel.val(), function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('properties', data['data']);
	});
	
}

function edit(id){
	
	Controller.edit(dwr.util.getValues(id), function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('container-designer', data['data']);
	});
	
}

function remove(){
	
	var sel = $('select[name ="componentSelected"]');
	
	Controller.remove(sel.val(), function(data){
		dwr.util.setEscapeHtml(false);
		dwr.util.setValue('container-designer', data['data']);
		dwr.util.setValue('componentSelected', data['components']);
	});
	
}

function goto(siteUuid, pageUuid){
	window.location.href = "Projects.do?method=preview&siteId=" + siteUuid + '&pageId=' +  pageUuid;
}


function cancelarOp(value){
	$('input[name ="op"]').val(value);
}

function changeCheckbox(value){
	
	var obj = $('input[name ="' + value + '"]').val();
	
	if (obj == 'true'){
		$('input[name ="' + value + '"]').val('false');
	} else {
		$('input[name ="' + value + '"]').val('true');
	}

}

function showHide(id){
	
	var obj = $('#' + id);
	
	if (obj.css('visibility') == 'visible'){
		obj.css('visibility', 'hidden');
	} else {
		obj.css('visibility', 'visible');
	}
	
}

function uploadFiles(id) {
	  var image = dwr.util.getValue(id + '_uploadImage');

	  UploadDownload.uploadFiles(image, id, function(data) {
	    dwr.util.setValue(data['id']+'_image', data['imagem']);
	  });

}
