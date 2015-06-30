package org.sitenv.services;

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

import org.sitenv.bl.RegisterServiceProcess;
import org.sitenv.dto.DirectSystemTO;
import org.sitenv.dto.ResponseTO;
import org.sitenv.dto.ResultSetTO;
import org.sitenv.util.ApplicationConstants;
import org.sitenv.util.ApplicationUtil;
import org.sitenv.util.JSONGenerator;
import org.sitenv.util.XMLGenerator;

@Path(ApplicationConstants.REGISTER_SERVICE)
public class RegisterService {
	
	@POST
	@Path(ApplicationConstants.REGISTER_DIRECT_SYSTEM)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response registerAccount(
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
			    
			    registerSystemStatus = RegisterServiceProcess.registerService(directSystemTO);
			    registerSystemOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(registerSystemStatus), isJSon);
		
		}catch (SQLException sqlException)
		{
			registerSystemOutput = ApplicationUtil.getFormattedOutput(sqlException, isJSon);
		}catch(PropertyVetoException propertyVetoException)
		{
			registerSystemOutput = ApplicationUtil.getFormattedOutput(propertyVetoException, isJSon);
		}
		
		outMessage = new StringBuilder(registerSystemOutput);
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
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon)
	{
		ResultSetTO resultSetTO;
		String allDirectSystemsOutput;
		StringBuilder outMessage;
		try
		{
			resultSetTO = RegisterServiceProcess.readAllDirectSystems();
			allDirectSystemsOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(resultSetTO), isJSon);
		
		}catch (SQLException sqlException)
		{
			allDirectSystemsOutput = ApplicationUtil.getFormattedOutput(sqlException, isJSon);
		}catch(PropertyVetoException propertyVetoException)
		{
			allDirectSystemsOutput = ApplicationUtil.getFormattedOutput(propertyVetoException, isJSon);
		}
		
		outMessage = new StringBuilder(allDirectSystemsOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();

	}

}
