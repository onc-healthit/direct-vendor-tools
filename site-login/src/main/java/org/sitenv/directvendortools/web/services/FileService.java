package org.sitenv.directvendortools.web.services;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sitenv.directvendortools.web.bl.FileProcess;
import org.sitenv.directvendortools.web.dto.ResponseTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.ApplicationUtil;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path(ApplicationConstants.FILE_SERVICE)
public class FileService {

	@POST
	@Path(ApplicationConstants.UPLOAD_CERT)
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			@FormDataParam("anchoruploadfile") InputStream uploadedInputStream,
			@FormDataParam("anchoruploadfile") FormDataContentDisposition fileDetail,
			@QueryParam("directEndPoint") String directEndPoint) {

		String[] folderNames = directEndPoint.split("@");
		File newFile = new File(ApplicationConstants.FILE_UPLOAD_DIRECTORY
				+ folderNames[1] + "/" + folderNames[0] + "/"
				+ fileDetail.getFileName());
		String uploadFileStatus;
		StringBuilder outMessage;
		try {

			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
			FileProcess.writeToFile(uploadedInputStream, newFile);
			uploadFileStatus = ApplicationUtil.getFormattedOutput(
					new ResponseTO(), isJSon);

		} catch (IOException IOException) {
			uploadFileStatus = ApplicationUtil.getFormattedOutput(IOException,
					isJSon);
		}

		outMessage = new StringBuilder(uploadFileStatus);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();
	}

	@GET
	@Path(ApplicationConstants.READ_ALL_CERTS)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response uploadFile(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			@QueryParam("directEndPoint") String directEndPoint) {

		ResultSetTO resultSetTO;
		String allCertsOutput;
		StringBuilder outMessage;
		try
		{
			resultSetTO = FileProcess.readAllCerts(directEndPoint);
			allCertsOutput = ApplicationUtil.getFormattedOutput(new ResponseTO(resultSetTO), isJSon);
		
		}catch (Exception e)
		{
			allCertsOutput = ApplicationUtil.getFormattedOutput(e, isJSon);
		}
		
		outMessage = new StringBuilder(allCertsOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();
	}
	
	
	@GET
	@Path(ApplicationConstants.DOWNLOAD_CERT)
	@Produces(ApplicationConstants.MIME_DER)
	public Response downloadCert(@QueryParam("filePath") final String filePath)
	{
		Response certFile = null;
		try
		{
			final File file = new File(filePath);
			certFile = Response
					.status(Response.Status.OK)
					.type("application/x-x509-ca-cert")
					.header("Content-Disposition",
							"attachment; filename=\""
								+ file.getName())
					.entity(file).build();
			
		}
		catch (Exception exception)
		{
			ApplicationUtil.logError(exception);
		}
		return certFile;
	}
	
	@GET
	@Path(ApplicationConstants.DELETE_CERT)
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deletCert(@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
								@QueryParam("filePath") final String filePath)
	{
		String fileDeleteOutput;
		StringBuilder outMessage;
	    final File file = new File(filePath);
	    fileDeleteOutput = ApplicationUtil.getFormattedOutput(
						   new ResponseTO(file.delete()), isJSon);
		outMessage = new StringBuilder(fileDeleteOutput);
		return Response
				.status(Response.Status.OK)
				.type(isJSon ? MediaType.APPLICATION_JSON
						: MediaType.APPLICATION_XML)
				.entity(outMessage.toString()).build();
	}
	
	
}