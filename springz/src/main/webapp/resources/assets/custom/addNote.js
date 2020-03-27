addNote = () => {
	
	var object = {
			title  : $("#note_title").val(),
			content: $("#note_detail").val(),
	}
	
	var ser_data = JSON.stringify(object);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:"addNote",
		data: ser_data,
		success: function(data) {
			alert(data);
		},error:function(data){
			alert(data);
		}
	})
}