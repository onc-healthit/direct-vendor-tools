
var UTILITY = new Utility();

function Utility()
{
	var currentObject = this;
	
	this.getOptionList = function(list,valueName,labelName,includeAll,includeSelect)
	{
		var optionList = "";
		if(includeAll)
			optionList += "<option title='All' value='-1'>All</option>";
		if(includeSelect)
			optionList += "<option title='Select' value='-1'>Select</option>";
		$(list).each(function(){
			optionList += "<option title='"+ this[labelName] +"' value='"+ this[valueName] +"'>" + this[labelName] + "</option>";
		});
		return optionList;
	};
	

	this.getCheckBoxList = function (list,chkName,valueName,labelName)
	{
		var optionList = "";
		$(list).each(function(){
			optionList += "<tr><td><input type='checkbox' id='"+(chkName+this[valueName])+"' name='"+chkName+"' value='"+this[valueName]+"'/><label for='"+(chkName+this[valueName])+"'>"+this[labelName]+"</label></td></tr>";
		});
		return optionList;
	};

	this.getHorizontalCheckBoxList = function (list,chkName,valueName,labelName,rowCount)
	{
		var optionList = "<tr>";
		var count = 0;
		$(list).each(function(){
			optionList += "<td><input type='checkbox' id='"+(chkName+this[valueName])+"' name='"+chkName+"' value='"+this[valueName]+"' nowrap='nowrap'/><label for='"+(chkName+this[valueName])+"'>"+this[labelName]+"</label></td>";
			count++;
			if(((count % rowCount) == 0))
			{
				optionList+="</tr><tr>";
			}
		});
		optionList +="</tr>";
		return optionList;
	};

	this.getSelectedItems = function (checkBoxName)
	{
		var list = document.getElementsByName(checkBoxName);
		var arr = [];
		var labelValue = "";
		for(var i = 0; i < list.length; i++)
		{ 
			var object = {};
			if(list[i].checked) 
			{ 
				object.code =list[i].value;
				labelValue = "label[for='"+checkBoxName+list[i].value+"']";
				object.description = $(labelValue).text(); 
				arr.push(object);
			} 
		} 
		return arr;
	};

	this.getCurrentDate = function ()
	{
		var today = new Date(); 
		var dd = today.getDate(); 
		var mm = today.getMonth()+1; //January is 0!  
		var yyyy = today.getFullYear(); 
		if(dd<10)
			{dd='0'+dd;} 
		if(mm<10)
			{mm='0'+mm;} 
		var today = mm+'/'+dd+'/'+yyyy;
		return today;
	};
	
	
	this.resetCheckBoxList = function (checkBoxName)
	{
		$("input[name="+checkBoxName+"]'").attr('checked', false);
	};
	
	
	this.getDefaultValueForString = function (input)
	{
		if(input==null || $.trim(input).length==0)
			return "";
		else
			return input;
	};
	
	
	this.isEmptyString = function (input)
	{
		if(input==null || $.trim(input).length==0)
			return true;
		else
			return false;
	};
	
	
	this.isEmptyArray = function (input)
	{
		if(input==null || input.length==0)
			return true;
		else
			return false;
	};
	

	this.isEmptyNumber = function (input)
	{
		if(input==null || $.trim(input).length==0 || parseInt(input)==0)
			return true;
		else
			return false;
	};
	
	
	this.createError = function (errorArray)
	{
		if(!currentObject.isEmptyArray(errorArray))
		{
			var errorString = "";
			$(errorArray).each(function(index, error){
				errorString +=  error+"<br>";
			});
			jErrorAlert(errorString,"Please correct the Following Errors");
		}
	};

	
	this.screenFreeze = function (mssg)
	{
		infoMessage = "<h1><img src='images/Processing.gif'/>&nbsp;&nbsp;<label>"+mssg+"</label></h1>";
		//$.blockUI({message:infoMessage});
	};

	
	jQuery.download = function(url,method,dataArray,isJson)
	{
		//url and data options required
		if(url)
		{ 
			var input = '';
			var count = 0;
			if(isJson)
				input += "<input type='hidden' name='isJSon' value='true'/>";
			if(!currentObject.isEmptyArray(dataArray))
			{
				$(dataArray).each(function(){
					input+="<input type='hidden' id='"+this.key+count+"' name='"+this.key+"'/>";
					$("#"+this.key+count).val(this.value);
					count++;
				});
			}
			$("<form action='"+ url +"' method='"+method+"' id='exportForm1'>"+input+"</form>").appendTo('body');
			var counter = 0;
			if(!currentObject.isEmptyArray(dataArray))
			{
				$(dataArray).each(function(){
					$("#"+this.key+counter).val(this.value);
					counter++;
				});
			}
			$('#exportForm1').submit().remove();
		}
	};


	this.exportGrid = function (inputGridId,fileName,workSheetName)
	{
		var gridId = "#"+inputGridId;
		var allRows = $(gridId).jqGrid('getGridParam', 'data');
		var columnNames = $(gridId).jqGrid('getGridParam','colNames');
		var colModel = $(gridId).jqGrid('getGridParam','colModel');
		var exportObject = {};
		var gd = {};
		var hs = [];
		var ds = null;
		var rs = [];
		var h = null;
		var d = null;
		var r = {};
		var cellData = {};
		if(!currentObject.isEmptyArray(colModel) && !currentObject.isEmptyArray(allRows))
		{
			$(colModel).each(function(index, object){
				if(!object.hidden)
				{
					h = {};
					h.v = columnNames[index];
					hs.push(h);
				}
			});
			for(var i=0;i<allRows.length;i++)
			{
				ds = [];
				$(colModel).each(function(index, object){
					if(!object.hidden)
					{
						d = {};
						cellData = allRows[i][object.name];
						d.v = cellData;
						ds.push(d);
					}
				});
				r = {};
				r.ds = ds;
				rs.push(r);
			}
			gd.hs = hs;
			gd.rs = rs;
			exportObject.wsnm = workSheetName;
			exportObject.fnm = fileName;
			exportObject.gd = gd;
			
			//jAlert(JSON.stringify(exportObject));
			var obj = {};
			obj.key = "reportingData";
			obj.value = JSON.stringify(exportObject);
			var dataArray = [];
			dataArray.push(obj);
			var httpAjaxServices = new HttpAjaxServices();
			httpAjaxServices.downloadExcelFile(dataArray);
		}
		else
		{
			jAlert("No Records in the Grid to Export");
		}
	};
	
	
	this.validateNumeric = function (evt) 
	{   
		var theEvent = evt || window.event;   
		var key = theEvent.keyCode || theEvent.which;   
		key = String.fromCharCode( key );
		var regex = /[0-9]/;   
		if( !regex.test(key) ) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};

	
	this.getComboSelectedValue = function (id)
	{
		var selectedValue = parseInt($("#"+id).val());
		return selectedValue;
	};
	
	
	this.clearSelected = function (id)
	{
		$( "#"+id ).val("");
	};
	
	this.setDefaultForCombo = function (id)
	{
		$( "#"+id ).val(-1);
	};
	
	this.getScreenDimension = function()
	{
		var winW = 630, winH = 460;
		if (document.body && document.body.offsetWidth) {
			 winW = document.body.offsetWidth;
			 winH = document.body.offsetHeight;
			}
			if (document.compatMode=='CSS1Compat' &&
			    document.documentElement &&
			    document.documentElement.offsetWidth ) {
			 winW = document.documentElement.offsetWidth;
			 winH = document.documentElement.offsetHeight;
			}
			if (window.innerWidth && window.innerHeight) {
			 winW = window.innerWidth;
			 winH = window.innerHeight;
			}
		var obj = new Object();
		obj.winW = winW;
		obj.winH = winH;
		return obj;
	};
	
	this.isEmptyInput = function(id)
	{
		var trimmedVal = $.trim($("#"+id).val()); 
		if(trimmedVal != null && trimmedVal != '' && trimmedVal != "")
		{
			return false;
		}
		return true;
	};
	
	this.validateDateFormat = function(id)
	{
		var validformat=/^\d{2}\/\d{2}\/\d{4}$/;
		var returnval=false;
		var inputDate = $(id).val();
		if (validformat.test(inputDate)) 
		{
			var month =inputDate.split("/")[0];
            var day =inputDate.split("/")[1];
            var year =inputDate.split("/")[2];
            
	     	var dayobj = new Date(year,month-1, day);
	     	
		   if ((dayobj.getMonth()+1!=month)||(dayobj.getDate()!=day)||(dayobj.getFullYear()!=year))
			    returnval=false;
		   else
			   returnval=true;
		}
	
			return returnval;
	};
	
	this.getCaptionText = function(searchResult)
	{
		var text = "No Records Found";
		if(searchResult)
		{
			var totalResultsReturned = parseInt(searchResult.total);
			if(totalResultsReturned>0)
				text = totalResultsReturned+" Result(s) Returned";
		}
		return text;
	};
	
	this.getCaptionTextWAttribute = function(searchResult,attribute)
	{
		var text = "No Records Found";
		if(searchResult)
		{
			var totalResultsReturned = parseInt(searchResult[attribute]);
			if(totalResultsReturned>0)
				text = totalResultsReturned+" Result(s) Returned";
		}
		return text;
	};
	
	this.getTotalNumberOfRecords = function(searchResult)
	{
		var totalRecords = 0;
		if(searchResult)
		{
			totalRecords = parseInt(searchResult.total);
        }
		return totalRecords;
	};
	
	this.validateSpecialChar = function(evt) 
	{   
		var theEvent = evt || window.event;   
		var key = theEvent.keyCode || theEvent.which;   
		key = String.fromCharCode( key );   
		var regex = /^[A-Z,a-z,0-9]/;
		if( !regex.test(key) ) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};
	
	this.validateSpecialCharSpace = function(evt) 
	{   
		if(currentObject.isEmptyInput('txtPartnerSearchInput'))
		{
			currentObject.validateWhiteSpace(evt);
		}
		var theEvent = evt || window.event;   
		var numberKey = theEvent.keyCode || theEvent.which;   
		key = String.fromCharCode( numberKey );   
		var regex = /^[A-Z,a-z,0-9,-]/;
		if( !regex.test(key) && numberKey!=32) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};
	
	this.validateWhiteSpace = function(evt)
	{
		var theEvent = evt || window.event;
		var key = theEvent.keyCode || theEvent.which;
		if(key==32) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};
	
	this.validateAlphaCode = function(evt) 
	{   
		var theEvent = evt || window.event;   
		var key = theEvent.keyCode || theEvent.which;   
		key = String.fromCharCode( key );   
		var regex = /^[A-Z,a-z]/;
		if( !regex.test(key) ) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};
	
	this.isCheckBoxSelected = function(chkBoxId)
	{
		var isChecked = $('#'+chkBoxId+':checked').val();
		if(isChecked == "on")
			return true;
		return false;
	};
	
	this.validateNonNumeric = function(evt) 
	{    
		var theEvent = evt || window.event;   
		var key = theEvent.keyCode || theEvent.which;   
		key = String.fromCharCode( key );   
		var regex = /[^0-9]/;
		if( !regex.test(key) ) 
		{     
			theEvent.returnValue = false;     
			if(theEvent.preventDefault) 
				theEvent.preventDefault();   
		} 
	};
	
	this.compareDate = function(start, end)
	{	
		var difference = currentObject.daydiff(start, end);
		if (difference >=0) 
		{
			return true;
		} 
		else 
		{
			return false;
		}
	};
	
	this.daydiff = function(start, end) 
	{
	    return (((currentObject.parseDate(end)-currentObject.parseDate(start))/(1000*60*60*24)));
	};
	
	this.parseDate = function (str) {
	    var mdy = str.split('/');
	    return new Date(mdy[2], mdy[0]-1, mdy[1]);
	};
	
	this.setJqGridColumnWidth = function(gridWidth,columnWidth)
	{
		return gridWidth*columnWidth/100;
	};
}
