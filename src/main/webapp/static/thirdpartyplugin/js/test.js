/**
 * test.js
 */
//****************************************************************************************
// http://silviomoreto.github.io/bootstrap-select/methods/
$('.selectpicker').selectpicker('val', 'Ketchup');
// $('.selectpicker').selectpicker('val', ['Mustard','Relish']);

$('.rm-mustard').click(function () {
	  $('.remove-example').find('[value=Mustard]').remove();
	  $('.remove-example').selectpicker('refresh');
	});


// http://www.bootcss.com/p/bootstrap-switch/
// http://blog.csdn.net/mafan121/article/details/50402070
$("[name='myCheckbox']").bootstrapSwitch({
	onText : "yes",  
    offText : "no",  
    onColor : "success",  
    offColor : "warning",  
    size : "small",  
    onSwitchChange : function(event, state) {  
        var iptname = event.target.id + "value";  
    }  
});

// http://www.runoob.com/jqueryui/example-slider.html
function hexFromRGB(r, g, b) {
    var hex = [
      r.toString(16),
      g.toString(16),
      b.toString(16)
    ];
    $.each(hex, function(nr, val) {
      if (val.length === 1) {
        hex[nr] = "0" + val;
      }
    });
    return hex.join("").toUpperCase();
  }

function refreshSwatch() {
    var red = $("#red").slider("value"),
      green = $("#green").slider("value"),
      blue = $("#blue").slider("value"),
      hex = hexFromRGB(red, green, blue);
    $("#swatch").css("background-color", "#" + hex);
  }

$(function() {
    $("#red, #green, #blue").slider({
      orientation: "horizontal",
      range: "min",
      max: 255,
      value: 127,
      slide: refreshSwatch,
      change: refreshSwatch
    });
    $("#red").slider("value", 255);
    $("#green").slider("value", 140);
    $("#blue").slider("value", 60 );
  });

//****************************************************************************************

/*
 * pieCharts
 * */

// pie chart
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawPieChart);

function drawPieChart() {
  var data = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Work',     11],
    ['Eat',      2],
    ['Commute',  3],
    ['Watch TV', 2],
    ['Sleep',    7]
  ]);

  var options_pie_chart = {
    title: 'Normal PieChart'
  };

  var pie_chart = new google.visualization.PieChart(document.getElementById('piechart'));
  pie_chart.draw(data, options_pie_chart);
}

// piechart_3d
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawPieChart_3d);
function drawPieChart_3d() {
  var data = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Work',     11],
    ['Eat',      2],
    ['Commute',  2],
    ['Watch TV', 5],
    ['Sleep',    7]
  ]);

  var options_piechart_3d = {
    title: '3D PieChart',
    is3D: true,
  };

  var chart_piechart_3d = new google.visualization.PieChart(document.getElementById('piechart_3d'));
  chart_piechart_3d.draw(data, options_piechart_3d);
}

// donutchart
google.charts.load("current", {packages:["corechart"]});
google.charts.setOnLoadCallback(drawDonutChart);
function drawDonutChart() {
  var data = google.visualization.arrayToDataTable([
    ['Task', 'Hours per Day'],
    ['Work',     11],
    ['Eat',      2],
    ['Commute',  2],
    ['Watch TV', 2],
    ['Sleep',    8]
  ]);

  var options_donutchart = {
    title: 'Donut PieChart',
    pieHole: 0.4,
  };

  var chart_donutchart = new google.visualization.PieChart(document.getElementById('donutchart'));
  chart_donutchart.draw(data, options_donutchart);
}

//****************************************************************************************
//****************************************************************************************
//****************************************************************************************

/*
 * jQuery BootGrid
 * */

  $(document).ready(function(){
	  $("#acquireContent").click(function(){
		  $.ajax({
			  type: 'POST',
			  url: './test',
			  dataType: "json",
	          contentType: 'application/x-www-form-urlencoded',
			  cache: false,
			  timeout: 5000,
			  success: function(data){
				  console.log(data);
				  //$("#content").text(data.status);
			  }
		  });
	  });
  });
  
  $(document).ready(function(){
	  var test_data = $("#grid").bootgrid({
	  	  				ajax: true,
	  	  				post: function ()
	  	  				{
	  	  					return {
	  	  						type: "loadData",
	  	  						currentTime: "2018-02-01 18:00:53"
	  	  					};
	  	  				},
	  	  				url: './test',
	  	  				searchSettings: {
	  	  					delay: 200,
	  	  					characters: 3
	  	  				},
	  	  				labels: {
	  	  					all: "All",
	  	  				    loading: "Loading...",
	  	  				    refresh: "Refresh",
	  	  					noResults: "where are my results",
	  	  				    search: "what do you want search",
	  	  				},
	  	  				formatters: {  
	  	  					"sender": function(column, row)
	  	  					{
	  	  						var html = "<span id=\""+ row.id + "_sender" + "\"" + " received=\"" + row.received + "\"" + " name=" + "\"" + row.sender + "\">" + row.sender + "</span>";
	  	  						return html;
	  	  					},
	  	  					"received": function(column, row)
	  	  					{
	  	  						var html = "<span id=\""+ row.received + "_timer" + "\"" + " rowId=" + "\"" + row.id +"\"" + " name=" + "\"" + row.received +"\">" + row.received + "_timer" + "</span>";
	  	  						return html;
	  	  					},
	  	  					"link": function(column, row)
	  	  					{
	  	  						return "<a href=\"#\" class=\"glyphicon glyphicon-off\">" + "TV_" + row.id + "</a>";
	  	  					},
	  	  					"status": function(column, row)
	  	  					{
	  	  						var html =  '<input type="text" id ="' + row.id + '" orgvalue ="' + row.status +'" value="'+ row.status + '" ONKEYDOWN="javascript:return enterEvent(event);"' + 
	  	  									'style="width: 100%;float:left;text-align:left;border:0px solid #dddddd; background: none repeat scroll 0 0 ;"'+'name="status"';
	  	  						html += 'onchange="statusChange(this);"/>';
	  	  						return html;
	  	  					}
	  	  				},
	  	  				rowCount: [10, 20, 30],
	  	  				navigation: 3,
	  	  				selection: true,
	  	  				multiSelect: true,
	  	  				rowSelect: true,
	  	  				keepSelection: true,
	  	  				rowSelect: false
	  }).on("loaded.rs.jquery.bootgrid",function(){
		  test_data.find("[id$='_timer']").on("click", function(e){		
			  var received = $(this).attr("name");
			  var rowId = $(this).attr("rowId");
			  timeTip(received, rowId);
		  }).end().find("[id$='_sender']").on("click", function(e){
			  var received = $(this).attr("received");
			  var sender = $(this).attr("name");
			  $("#tip_msg").html("this is model tip message <br />Received: " + received +"<br />Sender: " + sender);
			  $('#tipModal').modal('show');
			  //showSender(sender);
		  });
	  }).on("selected.rs.jquery.bootgrid", function(e, rows){
		    var rowIds = [];
		    for (var i = 0; i < rows.length; i++)
		    {
		        rowIds.push(rows[i].id);
		    }
		    alert("Select: " + rowIds.join(","));
	  }).on("deselected.rs.jquery.bootgrid", function(e, rows){
		    var rowIds = [];
		    for (var i = 0; i < rows.length; i++)
		    {
		        rowIds.push(rows[i].id);
		    }
		    alert("Deselect: " + rowIds.join(","));
	 });
  });
  
  	statusChange = (function(obj){	
 		var newValue = $(obj).val();
 		newValue = newValue.replace(/^\s+|\s+$/g,'');
 		var numericReg =/[\/\\:*?\"<>|]/g; 
 		if(numericReg.test(newValue)) {
 			$().toastmessage('showErrorToast','Failure: Special characters > < \ / | : ? * " are not allowed for input.');
 			$(obj).val($(obj).attr('orgvalue'));
  			return false;
 		}
  		
   		$.ajax({
 			type: 'POST',
 			data: {
 				    type: 'saveStatusValue', 
 					id: $(obj).attr("id"), 
 					newStatusValue: $(obj).val()
 			      },
 			dataType: "json",
 			url: './test',
 			//contentType: 'application/x-www-form-urlencoded',				   
 			success: function(msg) {			
 				console.log("status: " + msg.status);
 				if(msg.status == 'success'){
 					$().toastmessage('showSuccessToast', "Status is updated successfully!");
 					$("#grid").bootgrid('reload');
 				}else {
 					$().toastmessage('showErrorToast', "Status is updated failed!");
 				} 
 				
 			}			
 		});
  		
  		//var rowId = $(obj).attr("id");
		//var statusVal = $(obj).val();
		//statusVal = statusVal.replace(/^\s+|\s+$/g,'');
        //alert(statusVal + " <=> " + rowId);
	});
  
  $(function(){
	  $("#deleteOk").click(function(){
		  console.log("hide model");
		  $('#tipModal').modal('hide');
	  });
  });
  
  $(function(){
	  $("#reloadGridTable").click(function(){
		  console.log("reload click");
		  $("#grid").bootgrid("reload");
	  });
  });
  
  $(document).ready(function(){
	  $("#serachPharse").click(function(){
		  console.log("search click");
		  $("#grid").bootgrid("search", "50");
	  });
	  $("#sortOperate").click(function(){
		  console.log("sort click");
		  $("#grid").bootgrid("sort", {"id":"asc"});
	  });
	  $("#totalPageCount").click(function(){
		  console.log("totalPageCount click");
		  var totalCount = $("#grid").bootgrid("getTotalPageCount");
		  alert(totalCount);
	  });
	  $("#getSortDictionary").click(function(){
		  console.log("getSortDictionary click");
		  var sortDictionary = $("#grid").bootgrid("getSortDictionary");
		  for(key in sortDictionary) {
			  alert(key + " " + sortDictionary[key]);
		  }
	  });
  });
  
  function timeTip(received, rowId) {
	  alert(received + " <=> " + rowId);
  }
  
  function showSender(sender) {
	  alert(sender);
  }
  
  function Dictionary() {  
	    this.data = new Array();  
	  
	    this.put = function(key, value) {  
	        this.data[key] = value;  
	    };  
	  
	    this.get = function(key) {  
	        return this.data[key];  
	    };  
	  
	    this.remove = function(key) {  
	        this.data[key] = null;  
	    };  
	  
	    this.isEmpty = function() {  
	        return this.data.length == 0;  
	    };  
	  
	    this.size = function() {  
	        return this.data.length;  
	    };  
	}  
  

  