addUser = () => {
	
	var object = {
			username  : $("#username").val(),
			name: $("#name").val(),
			surname: $("#surname").val(),
			email: $("#email").val(),
			password: $("#pass").val(),
			password2: $("#pass2").val(),
			
	}
	
	var ser_data = JSON.stringify(object);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:"addUser",
		data: ser_data,
		success: function(data) {
			
			if (data == 1) {
				alert("Parolalar eşleşmiyor.");
			}else if(data == "OK"){
				alert("Başarıyla Üye Olundu.");
				$(location).attr('href','login');
			}
			else if(data == "ERROR"){
				alert("Bir Hata Oluştu!");
			}
		},error:function(data){
			alert(data);
		}
	})
}