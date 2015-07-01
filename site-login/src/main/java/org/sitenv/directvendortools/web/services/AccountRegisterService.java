package org.sitenv.directvendortools.web.services;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.sitenv.directvendortools.web.bl.AccountRegisterProcess;
import org.sitenv.directvendortools.web.dto.AccountRegisterTO;
import org.sitenv.directvendortools.web.dto.ResponseTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.JSONGenerator;
import org.sitenv.directvendortools.web.util.XMLGenerator;





@Path(ApplicationConstants.ACCOUNT_REGISTER_SERVICE)
public class AccountRegisterService {
	
	@POST
	@Path(ApplicationConstants.REGISTER_ACCOUNT)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response registerAccount(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			final String inputData)
	{
		int registerAccountStatus;
		String accountRegistryOutput ="";
		StringBuilder outMessage;
		try
		{
			    AccountRegisterTO accountRegisterTO;
			    if (isJSon)
				{
					final JSONGenerator<AccountRegisterTO> jsonGenerator = new JSONGenerator<AccountRegisterTO>();
					accountRegisterTO = jsonGenerator.createObject(inputData,AccountRegisterTO.class);
				}
				else
				{
					accountRegisterTO = (AccountRegisterTO) XMLGenerator.generateTOfromXML(inputData);
				}
			    
			    if(AccountRegisterProcess.isEmailAvailable(accountRegisterTO.getEmail()))
			    {
			    	registerAccountStatus = AccountRegisterProcess.registerAccount(accountRegisterTO);
			    	accountRegistryOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(registerAccountStatus), isJSon);
			    }else 
			    {
			    	accountRegistryOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(false), isJSon);
			    }
		
		}catch (SQLException sqlException)
		{
			accountRegistryOutput = ApplicationUtil.getFormattedOutput(sqlException, isJSon);
		}catch(PropertyVetoException propertyVetoException)
		{
			accountRegistryOutput = ApplicationUtil.getFormattedOutput(propertyVetoException, isJSon);
		}
		
		outMessage = new StringBuilder(accountRegistryOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();

	}
	
	@GET
	@Path(ApplicationConstants.CHECK_EMAIL_AVAILABILITY)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN })
	public Response checkEmailAvailability(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			@QueryParam(ApplicationConstants.EMAIL_ADDRESS) final String emailAddress)
	{
		boolean isEmailAvailable ;
		String emailAvailableOutput ="";
		StringBuilder outMessage;
		JSONArray jsonArray = new JSONArray();
		try
		{
			isEmailAvailable = AccountRegisterProcess.isEmailAvailable(emailAddress);
			emailAvailableOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(isEmailAvailable), isJSon);
			jsonArray.add("email");
			jsonArray.add(isEmailAvailable);
		
		}catch (SQLException sqlException)
		{
			emailAvailableOutput = ApplicationUtil.getFormattedOutput(sqlException, isJSon);
		}catch(PropertyVetoException propertyVetoException)
		{
			emailAvailableOutput = ApplicationUtil.getFormattedOutput(propertyVetoException, isJSon);
		}
		
		outMessage = new StringBuilder(emailAvailableOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(jsonArray.toString()).build();
	
	}
	
}
