function doUpload() {  
     var formData = new FormData($( "#uploadForm" )[0]);  
     $.ajax({  
         // url: 'http://fileread.chenhailu.top/upload_file' ,
          url: 'http://localhost:8081/upload_file' ,
          type: 'POST',  
          data: formData,  
          dataType: 'json',
          async: false,  
          cache: false,  
          contentType: false,  
          processData: false,  
          success: function (returndata) {
          	if (returndata.retCode == 0) {
                alert(returndata.retMsg);
          	    $("#showContent").show();
          	    var obj = returndata.result;
          	    var x = '';
          	    $.each(obj,function (n,value) {
                    x += "<tr><td><a href=\'" +value +"' target='_blank' >"+value+"</a></td></tr>";
                })
         	    $("#showContent").html(x);
          	} else {
          		alert(returndata.retMsg);
          	}
          	 
          },  
          error: function (returndata) {  
          	alert(returndata.status);
           
          }  
     });  
} 

