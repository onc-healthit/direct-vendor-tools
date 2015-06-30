function HttpAjaxServices()
{
	var currentObject = this;
	
	var APP_CONTEXT = "/site-login/";
	
	this.REGISTER_ACCOUNT = APP_CONTEXT+"rs/accountRegisterService/registerAccount?isJSon=true";
	this.CHECK_EMAIL = APP_CONTEXT+"rs/accountRegisterService/checkEmailAvailability?isJSon=true";
	this.LOGIN_AUTH = APP_CONTEXT+"rs/loginService/validateLogin?isJSon=true";
	this.REGISTER_DIRECT_SYSTEM = APP_CONTEXT+"rs/registerService/registerDirectSystem?isJSon=true";
	this.READ_ALL_DIRECT_SYSTEM = APP_CONTEXT+"rs/registerService/readAllDirectSystem?isJSon=true";
	
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
	
	this.registerDirectSystem = function(registerServiceTO,callback,freezeScreen,screenFreezeMessage)
	{
		var utility = new Utility();
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		$.ajax({type:"POST",
			data:JSON.stringify(registerServiceTO),
			url: currentObject.REGISTER_DIRECT_SYSTEM,
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
	
	this.readAllDirectSystem = function(callback,freezeScreen,screenFreezeMessage)
	{
		var utility = new Utility();
		if(freezeScreen)
		{
			if(utility.isEmptyString(screenFreezeMessage))
			   screenFreezeMessage =  "Processing. Please wait...";
			   UTILITY.screenFreeze(screenFreezeMessage);
		}
		$.ajax({type:"GET",
			url: currentObject.READ_ALL_DIRECT_SYSTEM,
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



