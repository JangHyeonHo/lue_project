/**
 * 
 */

$(function(){
	
	$("#createBtn").on("click",function(){
		var point = $("#chaVal").text()
		if(point<3){
			location.href="./select/create"
		}
		
	})
	
	
})