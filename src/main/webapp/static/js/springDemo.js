/**
 *   date: 04/03/2018
 *   springDemo.js 
 */

/*$(document).ready(function(){
	$(".submit").click(function(){
		var username = $("input[name=username]").val();
		var password = $("input[name=password]").val();
	});
});*/

$(function(){
	
	$("#requestExample").click(function(){
		var authors = [{name: "authorOne", age: 20, country: "Germany"},
			{name: "authorTwo", age: 21, country: "France"},
			{name: "authorThree", age: 23, country: "China"}];
		
		$.ajax({
			type: "POST",
			url: "/MavenSpringMVC/author/request",
			data: JSON.stringify(authors),
			dataType: "json",
			cache: false,
			contentType: "application/json;charset=utf-8",
			success: function(data) {
				var html = "name="+data.name+",age="+data.age+",country="+data.country;
				$("#responseResult").html(html);
				//console.log("name="+data.name+",age="+data.age+",country="+data.country);
			},
			error: function(textStatus) {
				console.log("textStatus="+textStatus);
			}
		});
	});
	
	/*$("#download").click(function(){
		var filename = $("input[name='filename']").val();
		if (filename != "") {
			//download(filename);
			//window.location.href = "/MavenSpringMVC/upload/down?filename=" + filename;
		}
	});*/
	
	function download(filename) {
		$.ajax({
			type: "POST",
			data: {"filename": filename},
			url: "/MavenSpringMVC/upload/down",
			success: function(data) {
				console.log("data="+data);
			},
			error: function(msg) {
				console.log("error="+msg);
			}
		});
	}
	
	$("#submitUpload").click(function(){
		var allFilename = [];
		var totalFieSize = 0;
		$("input[name='fileUpload']").each(function(index, element){
			var filepath = $(element).val();		
			if (filepath != "") {
				var fileSize = $(element)[0].files[0].size;
				totalFieSize += fileSize;
				var pos = filepath.lastIndexOf("\\");
				var filename = filepath.substring(pos+1);
				allFilename.push(filename);			
			}
		});
		console.log("totalFieSize="+totalFieSize);
		if (isRepeat(allFilename)) {
			console.log("exist same file name, can not upload");
			$().toastmessage('showErrorToast', "Exist same file name, can not upload");
		} else {
			if (totalFieSize > 1024*1024*10) {
				console.log("all file size sum exceed 10M");
				$().toastmessage('showErrorToast', "All file size sum exceed 10M");
			} else {
				console.log("upload");
				handleUpload();
			}
		}		
	});
	
	function isRepeat(arr) {
		var hash = {};
		for(var i in arr) {
			if(hash[arr[i]]) {
				return true;
			}
			hash[arr[i]] = true;
		}
		return false;
	}
	
	function handleUpload() {
		var formData = new FormData($("#form")[0]); 
		$.ajax({
			type: "POST",
			dataType: "json",
			data: formData,
			processData: false,
			contentType: false,
			//contentType: "multipart/form-data",
			url: "/MavenSpringMVC/upload/save",
			success: function(data) {
				console.log("data="+data);
				var total = data.total;
				var failureSaveDataBaseCount = data.failureSaveDataBaseCount;
				var successSaveToUploadCount = data.successSaveToUploadCount;
				var failure = data.failure;
				console.log("total="+total);
				console.log("failureSaveDataBaseCount="+failureSaveDataBaseCount+",successSaveToUploadCount="+successSaveToUploadCount);
				if (parseInt(failureSaveDataBaseCount) > 0 || 
						parseInt(successSaveToUploadCount) < parseInt(total)) {
					$().toastmessage('showErrorToast', "Some file can not upload to database or upload");
				} else {
					$().toastmessage('showSuccessToast', "upload success");
					$("input[name='fileUpload']").each(function(index, element){
						$(element).val("");		
					});
				}
			},
			error: function(msg) {
				console.log("error="+msg);
			}
		});
	}
});

