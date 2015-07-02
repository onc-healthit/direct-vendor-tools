function HttpAjaxServices()
{
	var currentObject = this;
	
	
	
	this.REGISTER_ACCOUNT = APP_CONTEXT+"rs/accountRegisterService/registerAccount?isJSon=true";
	this.CHECK_EMAIL = APP_CONTEXT+"rs/accountRegisterService/checkEmailAvailability?isJSon=true";
	this.LOGIN_AUTH = APP_CONTEXT+"rs/loginService/validateLogin?isJSon=true";
	this.REGISTER_DIRECT_SYSTEM = APP_CONTEXT+"rs/registerService/registerDirectSystem?isJSon=true";
	this.UPDATE_DIRECT_SYSTEM = APP_CONTEXT+"rs/registerService/updateDirectSystem?isJSon=true";
	this.READ_ALL_DIRECT_SYSTEM = APP_CONTEXT+"rs/registerService/readAllDirectSystem?isJSon=true";
	this.UPLOAD_FILE = APP_CONTEXT + "rs/file/upload?folderName="+MODEL.userEmail;
	
	this.registerAccount = function(accountRegisterTO,callback,freezeScreen,screenFreezeMessage)
	{
		var utility = new Utility();
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		$.ajax({type:"POST",
			data:JSON.stringify(accountRegisterTO),
			url: currentObject.REGISTER_ACCOUNT,
			cache: false,
			datatype : CONSTANTS.DATA_TYP_JSON,
			contentType: CONSTANTS.CONTENT_TYP_JSON,
			success:function (successJson, textStatus, oHTTP){
			if(parseInt(successJson.returnCode)!=0)
			{
				alert(successJson.error.errorMessage,"Error");
				return;
			}
			else
				callback.fire(successJson); 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				alert("Error Calling Service","Error");
			}
		});
	};
	
	this.login = function(userLoginTO,callback,freezeScreen,screenFreezeMessage)
	{
		var utility = new Utility();
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		$.ajax({type:"POST",
			data:JSON.stringify(userLoginTO),
			url: currentObject.LOGIN_AUTH,
			cache: false,
			datatype : CONSTANTS.DATA_TYP_JSON,
			contentType: CONSTANTS.CONTENT_TYP_JSON,
			success:function (successJson, textStatus, oHTTP){
			if(parseInt(successJson.returnCode)!=0)
			{
				alert(successJson.error.errorMessage,"Error");
				return;
			}
			else
				callback.fire(successJson); 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				alert("Error Calling Service","Error");
			}
		});
	};
	
	this.registerDirectSystem = function(registerServiceTO,callback,freezeScreen,register)
	{
		var URL = "";
		var utility = new Utility();
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		
		if(register)
		{
		  URL = currentObject.REGISTER_ACCOUNT;	
		}else
		{
			URL = currentObject.UPDATE_DIRECT_SYSTEM;
		}
		$.ajax({type:"POST",
			data:JSON.stringify(registerServiceTO),
			url: URL,
			cache: false,
			datatype : CONSTANTS.DATA_TYP_JSON,
			contentType: CONSTANTS.CONTENT_TYP_JSON,
			success:function (successJson, textStatus, oHTTP){
			if(parseInt(successJson.returnCode)!=0)
			{
				alert(successJson.error.errorMessage,"Error");
				return;
			}
			else
				callback.fire(successJson); 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				alert("Error Calling Service","Error");
			}
		});
	};
	
	
	this.readAllDirectSystem = function(callback,freezeScreen,userEmail)
	{
		var utility = new Utility();
		var URL = "";
		if(!utility.isEmptyString(userEmail))
		{
			URL = currentObject.READ_ALL_DIRECT_SYSTEM + "&userEmail=" + userEmail;
		}else 
		{
			URL = currentObject.READ_ALL_DIRECT_SYSTEM;
		}
			
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		$.ajax({type:"GET",
			url: URL,
			cache: false,
			datatype : CONSTANTS.DATA_TYP_JSON,
			contentType: CONSTANTS.CONTENT_TYP_JSON,
			success:function (successJson, textStatus, oHTTP){
			if(parseInt(successJson.returnCode)!=0)
			{
				alert(successJson.error.errorMessage,"Error");
				return;
			}
			else
				callback.fire(successJson); 
			},
			error: function(XMLHttpRequest, textStatus, errorThrown)
			{
				alert("Error Calling Service","Error");
			}
		});
	};
	
}



