/**
 * 
 */

$(function(){
	
	$("#email").on("keyup", function(){
		var emRegexp = /[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/
		var email = $(this).val()
		if(emRegexp.test(email) || email.length == 0){
			$("#emError").hide()
		} else{
			$("#emError").show()
		}
	})
	$("#email").on("focusout",function(){
		var thisval = $(this).val()
		if(thisval.length != 0){
			$.ajax({
				type :"POST",
				url : "signup/idcheck",
				dataType : "text",
				data : "email=" + thisval,
				success : function(data){
					if(data=="true"){
						
					} else{
						alert("중복된 아이디가 존재함")
						$(this).val("")
					}
				}
			})
		}
		
	})
	
	$("#pw").on("focusout keyup",function(){
		var pwRegexp = /^(?=.*\d)(?=.*[a-z])(?=.*[a-zA-Z]).{6,}$/gm
		var pw = $(this).val()
		if(pwRegexp.test(pw) || pw.length == 0){
			$("#pwError").hide()
		} else{
			$("#pwError").show()
		}
	})
	
	$("#pwConfirm").on("focusout keyup",function(){
		var pw = $("#pw").val()
		var thisval = $(this).val()
		if(pw.length != 0 && pw == thisval || thisval.length == 0){
			$("#pwConError").hide()
		} else{
			$("#pwConError").show()
		}
	})
	
	$("#backBtn").on("click",function(){
		history.back()
	})
	
	$("#signUpBtn").on("click",function(event){
		var emError = $("#emError").is(":visible")
		var pwError = $("#pwError").is(":visible")
		var pwConError = $("#pwConError").is(":visible")
		if(emError || pwError || pwConError){
			event.preventDefalut
			alert("에러뜨면 안되용!")
			return false;
		}
	})
	
})