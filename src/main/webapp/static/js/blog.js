/**
 *   date: 04/14/2018
 *   blog.js 
 */

$(function(){
	
	var grid_devices_data = $("#grid-data").bootgrid({
	    ajax: true,
	    rowCount: [5, 10, 15, 20],
	    post: function ()
	    {
	        return {
	            paraid: "b0df282a",
	            type: "selectAll"
	        };
	    },
	    url: "/MavenSpringMVC/author/bootgridTwo",
	    selection: true,
		multiSelect: true,
		keepSelection: true,
	    formatters: {
	        "email": function(column, row)
	        {
	            return "<a href=\"#\">" + column.id + ": " + row.id + ": " + row.email + "</a>";
	        }
	    }
	}).on("loaded.rs.jquery.bootgrid",function(){
	    console.log("loaded");
	}).on("selected.rs.jquery.bootgrid", function(e, rows){
		console.log("selected");
	}).on("deselected.rs.jquery.bootgrid", function(e, rows){
		console.log("deselected");
	});
	
	fixBootStrapProblem();
	
	$.ajax({
		type: "GET",
		dataType: "json",
		url: "/MavenSpringMVC/author/select",
		success: function(data) {
			var html = "<option value=\"0\" selected=\"selected\">none</option>";
			var rows = data.rows;
			for (var i=0; i<rows.length; i++) {
				html += "<option value=\"" + rows[i].id + "\">" + rows[i].name + "</option>";
			}
			$("#authorname").html(html);
		},
		error: function(msg) {
			console.log("select failure");
		}
	});
	/*$("select#authorname").change(function(){
		 console.log($(this).val());
	})*/
	$("input#submit").click(function(){
		if (parseInt($("select#authorname").val()) == 0) {
			alert("please select authorname");
		}
		$.ajax({
			type: "POST",
			data: {
				    "authorid": $("select#authorname").val(),
				    "title": $("input[name='title']").val(),
				    "mainbody": $("input[name='mainbody']").val()
				  },
			dataType: "json",
			url: "/MavenSpringMVC/blog/insert",
			success: function(msg) {
				if (msg.status == "success") {
					$("#result").text("success");
				} else {
					$("#result").text("failure");
				}
			},
			error: function(msg) {
				console.log("insert failure");
			}
		});
	});
	
	$("#selectBlog").click(function(){
		window.location.href = "/MavenSpringMVC/blog/getAll?id1="+2+"&id2="+3; 
		/*$.ajax({
			type: "GET",
			data: {"id": 1},
			dataType: "json",
			url: "/blog/getAll",
			success: function(data) {
				
			}
		});*/
	});
	
	$("#deleteBlog").click(function(){
		$.ajax({
			type: "POST",
			data: {"id": 5},
			dataType: "json",
			url: "/MavenSpringMVC/blog/delete",
			success: function(msg) {
				console.log("status="+msg.status);
				if (msg.status == "success") {
					$("#result").text("success");
				} else {
					$("#result").text("failure");
				}
			}
		});
	});
	
	function fixBootStrapProblem() {
		$("span.fa-refresh").parent().css({"height": "34px", "width": "50px"});
		$("span.dropdown-text").parent().css({"height": "34px", "width": "50px"});
		$("button[title='Refresh']").html("<span class=\"icon fa fa-refresh\"></span><span class=\"glyphicon glyphicon-refresh\"></span>");
		$("<span class=\"glyphicon glyphicon-list\"></span>").appendTo(".fa-th-list");
	}
	
});

