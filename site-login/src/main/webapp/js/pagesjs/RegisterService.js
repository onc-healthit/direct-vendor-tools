var editingSystemID  = 0;
function RegisterService()
{
	var currentObject = this;
	var resultSet = [];
	
	this.registerService = function()
	{
		var callbackFunction = $.Callbacks('once');
		callbackFunction.add(currentObject.registerServiceSuccessHandler);
		var httpService = new HttpAjaxServices();
		httpService.registerDirectSystem(currentObject.readValues(), callbackFunction, false,true);
	};
	
	this.readValues = function()
	{
		$('#directEmailExistAlertID').hide();
		var registerServiceTO = new RegisterServiceTO();
		registerServiceTO.cehrtLabel =  $("#cehrtLabel").val();
		registerServiceTO.organizationName =  $("#orgName").val();
		registerServiceTO.directEmailAddress =  $("#directEmail").val();
		registerServiceTO.pointOfContact =  $("#pocEmail").val();
		registerServiceTO.pocFirstName =  $("#pocFirstName").val();
		registerServiceTO.pocLastName =  $("#pocLastName").val();
		registerServiceTO.timezone =  $("#timezone").val();
		registerServiceTO.directTrustMembership =  $("#registerForm input[type='radio']:checked").val();
		registerServiceTO.availFromDate =  $("#availFromDate").val();
		registerServiceTO.availToDate =  $("#availToDate").val();
		registerServiceTO.id = editingSystemID;
		registerServiceTO.userEmailAddress = MODEL.userEmail;
		return registerServiceTO;
	};
	
	this.updateService = function()
	{
		var callbackFunction = $.Callbacks('once');
		callbackFunction.add(currentObject.updateServiceSuccessHandler);
		var httpService = new HttpAjaxServices();
		httpService.registerDirectSystem(currentObject.readValues(), callbackFunction, false,false);
	};
	
	this.registerServiceSuccessHandler = function(successJson)
	{
		if(successJson.isEmailAvailable)
		{
			$("#vendorReg").show();
			$('#registrationModal').modal('hide');
			$('#DirectSystemRegAlertID').show();
			registerService.readUserDirectSystems();
			
		}else 
		{
			$('#directEmailExistAlertID').show();
		}
	};
	
	this.updateServiceSuccessHandler = function(successJson)
	{
		if(successJson.isEmailAvailable)
		{
			$("#vendorReg").show();
			$('#registrationModal').modal('hide');
			$('#updateSystemAlertID').show();
			registerService.readUserDirectSystems();
			
		}else 
		{
			$('#directEmailExistAlertID').show();
		}
	     
	};
	
	this.readAllDirectSystems = function()
	{
		var callbackFunction = $.Callbacks('once');
		callbackFunction.add(currentObject.readAllDirectSystemsSuccessHandler);
		var httpService = new HttpAjaxServices();
		httpService.readAllDirectSystem(callbackFunction, false,"");
	};
	
	this.readAllDirectSystemsSuccessHandler = function(successJson)
	{
		var resultArray = successJson.resultSet.results;
		var rows = "";
		 $(resultArray).each(function(){
		   rows += 	"<tr><td>"+this.cehrtLabel+"</td>"
			+	"<td>"+this.organizationName+"</td>"
			+	"<td>"+this.directEmailAddress+"</td>"
			+	"<td>"+this.pocFirstName + " " + this.pocLastName + " (" +this.pointOfContact +")</td>"
			+	"<td>"+this.availFromDate + " to " +this.availToDate +"</td>"
			+	"<td style='text-align: center;'>"+this.directTrustMembership+"</td>"
			+   "<td style='text-align: center;'></td>"
			+ "</tr>";
		 });
		 
		 $("#tableBody").append(rows);
	};
	
	this.readUserDirectSystems = function()
	{
		var callbackFunction = $.Callbacks('once');
		callbackFunction.add(currentObject.readUserDirectSystemsSuccessHandler);
		var httpService = new HttpAjaxServices();
		httpService.readAllDirectSystem(callbackFunction, false,MODEL.userEmail);
	};
	
	this.readUserDirectSystemsSuccessHandler = function(successJson)
	{
		$("#userDirectSysTableBody").empty();
		var resultArray = successJson.resultSet.results;
		currentObject.resultSet = resultArray;
		var rows = "";
		 $(resultArray).each(function(){
		   rows += 	"<tr><td><a onclick =onRowClick(this)> <span class='glyphicon glyphicon-edit'></span></a>&nbsp;&nbsp;"+this.cehrtLabel+"</td>"
			+	"<td>"+this.organizationName+"</td>"
			+	"<td>"+this.directEmailAddress+"</td>"
			+	"<td>"+this.pocFirstName + " " + this.pocLastName + " (" +this.pointOfContact +")</td>"
			+	"<td>"+this.availFromDate + " to " +this.availToDate +"</td>"
			+	"<td style='text-align: center;'>"+this.directTrustMembership+"</td>"
			+   "<td style='text-align: center;'></td>"
			+ "</tr>";
		 });
		 
		 $("#userDirectSysTableBody").append(rows);
	};
	
};


function onRowClick(object)
{
	cleanUp();
	$("#updateServiceButton").show();
	$("#submitButton").hide();
	var selectedDirectEmail = $(object).closest('tr').find('td:eq(2)').text();
	$(registerService.resultSet).each(function(){
		if(this.directEmailAddress == selectedDirectEmail)
		{
			$("#cehrtLabel").val(this.cehrtLabel);
			editingSystemID = this.id;
			$("#orgName").val(this.organizationName);
			$("#directEmail").val(this.directEmailAddress);
			$("#pocEmail").val(this.pointOfContact);
			$("#pocFirstName").val(this.pocFirstName);
			$("#pocLastName").val(this.pocLastName);
			var text = this.timezone;
			$('#timezone').val(text);
			if(this.directTrustMembership == 'Yes')
			{
				$("#option_yes").attr('checked', 'checked');	
			}else
			{  
				$("#option_no").attr('checked', 'checked');
			}
			$("#availFromDate").val(this.availFromDate);
			$("#availToDate").val(this.availToDate);
			
			
		   return;
		}
	});
}


