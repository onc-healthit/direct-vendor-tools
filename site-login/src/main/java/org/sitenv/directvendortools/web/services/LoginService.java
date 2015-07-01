package org.sitenv.directvendortools.web.services;

import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sitenv.directvendortools.web.bl.LoginProcess;
import org.sitenv.directvendortools.web.dto.ResponseTO;
import org.sitenv.directvendortools.web.dto.UserLoginTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.ApplicationUtil;
import org.sitenv.directvendortools.web.util.HashException;
import org.sitenv.directvendortools.web.util.JSONGenerator;
import org.sitenv.directvendortools.web.util.XMLGenerator;

@Path(ApplicationConstants.LOGIN_SERVICE)
public class LoginService {
	/**
	 * This method is used to validate Login User Credentials REST HTTP method:
	 * GET Produces: xml/json The isJSon - Boolean
	 * 
	 * @param inputData
	 * 
	 * @return xml/json response
	 */
	@POST
	@Path(ApplicationConstants.VALIDATE_LOGIN)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response validateLoginDetails(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			final String inputData) {
		String userAutheticated;
		String loginAuthOutput;
		StringBuilder outMessage;
		try {
			UserLoginTO userLoginTO;
			if (isJSon) {
				final JSONGenerator<UserLoginTO> jsonGenerator = new JSONGenerator<UserLoginTO>();
				userLoginTO = jsonGenerator.createObject(inputData,
						UserLoginTO.class);
			} else {
				userLoginTO = (UserLoginTO) XMLGenerator
						.generateTOfromXML(inputData);
			}

			userAutheticated = LoginProcess.loginAuthentication(userLoginTO);
			loginAuthOutput = ApplicationUtil.getFormattedOutput(
					new ResponseTO(userAutheticated), isJSon);

		} catch (SQLException sqlException) {
			loginAuthOutput = ApplicationUtil.getFormattedOutput(sqlException,
					isJSon);
		} catch (PropertyVetoException propertyVetoException) {
			loginAuthOutput = ApplicationUtil.getFormattedOutput(
					propertyVetoException, isJSon);
		} catch (HashException hashException) {
			loginAuthOutput = ApplicationUtil.getFormattedOutput(
					hashException, isJSon);
		}

		outMessage = new StringBuilder(loginAuthOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();
	}
}
