loginBtn = () => {
	
	var object = {
			username  : $("#username").val(),	
			password: $("#pass").val(),			
			
	}
	
	var ser_data = JSON.stringify(object);
	
	$.ajax({
		type:"POST",
		contentType:'application/json; charset=UTF-8',
		url:"controlUser",
		data: ser_data,
		success: function(data) {
			
			if (data == "OK") {
				$(location).attr('href','index')

			}
			else if(data == "ERROR"){
				alert("Kullanıcı Adı ve Şifrenizi Kontrol Ediniz!");
			}
		},error:function(data){
			alert(data);
		}
	})
}