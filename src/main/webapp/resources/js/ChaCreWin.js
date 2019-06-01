/**
 * 
 */

$(function(){
	
	
	$(".statBtn").on("click",function(){
		var thisId = $(this).attr("id")
		var isStatus = thisId.substring(0,3)
		var isBtn = thisId.substring(3)
		var statPoint = $("#statPoint").text()
		var textPoint = "Point"
		var btnPoint = $("#"+isStatus+textPoint).text()
		var hiddenId = "#hi"+isStatus
		var hiddenValue = $(hiddenId).val()
		if(isBtn=="Plus"){
			if(statPoint<1){
				$("#statError").show()
				return false;
			} else{
				$("#statError2").hide()
				statPoint--
				btnPoint++
				hiddenValue++
			}
		}else if(isBtn=="Minus"){
			if(btnPoint>5){
				statPoint++
				btnPoint--
				hiddenValue--
			} 
			
		}
		$(hiddenId).val(hiddenValue)
		$("#"+isStatus+textPoint).html(btnPoint)
		$("#statPoint").html(statPoint)
		$("#statError").hide()
		
	})
	
	$("#smBtn").on("click",function(){
		if($("#statPoint").text() != 0){
			$("#statError2").show()
			return false;
		}
		var today = new Date()
		$("#creDate").val(today.getFullYear() + "-" + (today.getMonth()+1) + "-" + today.getDate())
	})
	
	$("#backBtn").on("click",function(){
		location.href="../select"
	})
	
})