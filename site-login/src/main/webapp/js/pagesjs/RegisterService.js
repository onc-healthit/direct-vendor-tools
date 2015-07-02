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
		registerServiceTO.id =  $("#directSysId").val();
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
		currentObject.afterAjaxCall(successJson);
	};
	
	this.updateServiceSuccessHandler = function(successJson)
	{
		currentObject.afterAjaxCall(successJson);
		$('#DirectSystemRegAlertID').val("Updated Successfully");
	};
	
	this.afterAjaxCall = function(successJson)
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


function selectDropDown(value)
{
	$("#timezone option").each(function() {
		  if($(this).text() == value) {
		    $(this).attr('selected', 'selected');            
		  }                        
		});
}

