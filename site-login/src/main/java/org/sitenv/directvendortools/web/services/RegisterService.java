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

import org.sitenv.directvendortools.web.bl.RegisterServiceProcess;
import org.sitenv.directvendortools.web.dto.DirectSystemTO;
import org.sitenv.directvendortools.web.dto.ResponseTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.JSONGenerator;
import org.sitenv.directvendortools.web.util.XMLGenerator;

@Path(ApplicationConstants.REGISTER_SERVICE)
public class RegisterService {
	
	@POST
	@Path(ApplicationConstants.REGISTER_DIRECT_SYSTEM)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response registerDirectSystem(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			final String inputData)
	{
		int registerSystemStatus;
		String registerSystemOutput ="";
		StringBuilder outMessage;
		try
		{
			    DirectSystemTO directSystemTO;
			    if (isJSon)
				{
					final JSONGenerator<DirectSystemTO> jsonGenerator = new JSONGenerator<DirectSystemTO>();
					directSystemTO = jsonGenerator.createObject(inputData,DirectSystemTO.class);
				}
				else
				{
					directSystemTO = (DirectSystemTO) XMLGenerator.generateTOfromXML(inputData);
				}
			    
			    if(RegisterServiceProcess.isDirectSysEmailAvailable(directSystemTO.getDirectEmailAddress()))
			    {
			    	registerSystemStatus = RegisterServiceProcess.registerService(directSystemTO);
				    registerSystemOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(registerSystemStatus), isJSon);
			    }else
			    {
			    	registerSystemOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(false), isJSon);
			    }
			    
			    
		
		}catch (Exception e)
		{
			registerSystemOutput = ApplicationUtil.getFormattedOutput(e, isJSon);
		}
		
		outMessage = new StringBuilder(registerSystemOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();

	}
	
	
	@POST
	@Path(ApplicationConstants.UPDATE_DIRECT_SYSTEM)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response UpdateDirectSystem(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			final String inputData)
	{
		int registerSystemUpdateStatus;
		String registerSystemUpdateOutput ="";
		StringBuilder outMessage;
		try
		{
			    DirectSystemTO directSystemTO;
			    if (isJSon)
				{
					final JSONGenerator<DirectSystemTO> jsonGenerator = new JSONGenerator<DirectSystemTO>();
					directSystemTO = jsonGenerator.createObject(inputData,DirectSystemTO.class);
				}
				else
				{
					directSystemTO = (DirectSystemTO) XMLGenerator.generateTOfromXML(inputData);
				}
			    
			    if(RegisterServiceProcess.isUpdatedDirectSysEmailAvailable(directSystemTO.getDirectEmailAddress(),directSystemTO.getId()))
			    {
			    	registerSystemUpdateStatus = RegisterServiceProcess.updateDirectorySystem(directSystemTO);
			    	registerSystemUpdateOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(registerSystemUpdateStatus), isJSon);
			    }else
			    {
			    	registerSystemUpdateOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(false), isJSon);
			    }
			    
			    
		
		}catch (Exception e)
		{
			registerSystemUpdateOutput = ApplicationUtil.getFormattedOutput(e, isJSon);
		}
		
		outMessage = new StringBuilder(registerSystemUpdateOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();

	}
	
	
	
	
	@GET
	@Path(ApplicationConstants.READ_ALL_DIRECT_SYSTEM)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response readAllDirectSystems(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			@QueryParam(ApplicationConstants.USER_EMAIL) final String userEmail)
	{
		ResultSetTO resultSetTO;
		String allDirectSystemsOutput;
		StringBuilder outMessage;
		try
		{
			resultSetTO = RegisterServiceProcess.readAllDirectSystems(userEmail);
			allDirectSystemsOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(resultSetTO), isJSon);
		
		}catch (Exception e)
		{
			allDirectSystemsOutput = ApplicationUtil.getFormattedOutput(e, isJSon);
		}
		
		outMessage = new StringBuilder(allDirectSystemsOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();

	}

}
