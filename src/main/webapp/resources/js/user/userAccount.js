$(document).ready(function() {
	
	var emailTypedByNow = '';
	$("#emailTextbox").blur(function(){
		emailTypedByNow=$(this).val().trim();
		emailAvailable({'email':emailTypedByNow },emailCheckUrl);
		
	});
	
	$("#emailTextbox").focus(function(){
		$("#emailInvalidLabelError").hide();
		
	});
	
	$("#forgotPasswordBt").click(function(){
		forgotPasswordGet(forgotPasswordUrl);
	});
	
});

function forgotPasswordGet(url) {
    jQuery.ajax({
        url: url,
        type: "GET",
        success: function (response) {
        	$("#forgotPasswordForm").html(response);

        },
        error: function (xhr, errmsg, err) {
            console.log(xhr.status + ": " + xhr.responseText);
        }
    });

}

function emailAvailable(data,url) {
    jQuery.ajax({
        url: url,
        type: "GET",
        data: data,
        dataType: "json",
        success: function (response) {
        	if(response==true)
        		$("#emailInvalidLabelError").show();
        },
        error: function (xhr, errmsg, err) {
            console.log(xhr.status + ": " + xhr.responseText);
        }
    });

}