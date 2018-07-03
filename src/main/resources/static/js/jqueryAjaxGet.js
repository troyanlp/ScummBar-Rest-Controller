$(document).ready(function() {
	
	ajaxGet();
	
	  $(".add-row").click(function(){
          var name = $("#id").val();
          var email = $("#email").val();
          var markup = "<tr><td><input type='checkbox' name='record'></td><td>" + name + "</td><td>" + email + "</td></tr>";
          $("table tbody").append(markup);
      });
	
	// DO GET
	function ajaxGet(){
		$.ajax({
			type : "GET",
			url : window.location + "v1/board/all",
			success: function(result){
				$.each(result, function(i, customer){
					
					var customerRow = '<tr>' +
										'<td>' + customer.id + '</td>' +
										'<td>' + customer.name.toUpperCase() + '</td>' +
										
										'<td>' + 1 + '</td>' +
									  '</tr>';
					
					$('#customerTable tbody').append(customerRow);
					
		        });
				
				$( "#customerTable tbody tr:odd" ).addClass("info");
				$( "#customerTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});	
	}
})