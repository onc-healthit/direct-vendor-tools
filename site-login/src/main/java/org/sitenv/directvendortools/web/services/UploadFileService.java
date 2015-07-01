package org.sitenv.directvendortools.web.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.sitenv.directvendortools.web.dto.ResponseTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;
import org.sitenv.directvendortools.web.util.ApplicationUtil;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/file")
public class UploadFileService {

	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadFile(
			@DefaultValue(ApplicationConstants.TRUE) @QueryParam(ApplicationConstants.IS_JSON) final boolean isJSon,
			@FormDataParam("anchoruploadfile") InputStream uploadedInputStream,
			@FormDataParam("anchoruploadfile") FormDataContentDisposition fileDetail,
			@QueryParam("folderName") String folderName) {

		File newFile = new File(ApplicationConstants.FILE_UPLOAD_DIRECTORY + folderName.replace("@", "") + "/" + fileDetail.getFileName());
		String uploadFileStatus;
		StringBuilder outMessage;
		try {

			newFile.getParentFile().mkdirs();
			newFile.createNewFile();
			writeToFile(uploadedInputStream, newFile);
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

	// save uploaded file to new location
	private void writeToFile(InputStream uploadedInputStream,
			File file)throws IOException {
		OutputStream out = null;

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			out = new FileOutputStream(file);
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
		}finally{
			if (out != null) {
				out.flush();
				out.close();
			}
		}

	}

}