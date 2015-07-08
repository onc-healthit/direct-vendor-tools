package org.sitenv.directvendortools.web.bl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import org.sitenv.directvendortools.web.dto.CertAnchorTO;
import org.sitenv.directvendortools.web.dto.ResultSetTO;
import org.sitenv.directvendortools.web.util.ApplicationConstants;


public class FileProcess {

	// save uploaded file to new location
	public static void writeToFile(InputStream uploadedInputStream, File file)
			throws IOException {
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
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}
	
	public static ResultSetTO readAllCerts(String directEndPoint)
			throws IOException {
		
		final ResultSetTO resultSet = new ResultSetTO();
		String[] folderNames = directEndPoint.split("@");
		File folder = new File(ApplicationConstants.FILE_UPLOAD_DIRECTORY
				+ folderNames[1] + "/" + folderNames[0]) ;
		File[] listOfFiles = folder.listFiles();
		String uploadedTimestamp;
		if(listOfFiles != null)
		{
			List<CertAnchorTO> allCerts = new ArrayList<CertAnchorTO>();
			CertAnchorTO certAnchorTO = null;
			for (int i = 0; i < listOfFiles.length; i++) {
				certAnchorTO = new CertAnchorTO();
				certAnchorTO.setCertFile(listOfFiles[i].getName());
				certAnchorTO.setAbsolutePath(listOfFiles[i].getAbsolutePath());
				uploadedTimestamp = Files.readAttributes(listOfFiles[i].toPath(), 
							BasicFileAttributes.class).creationTime().toString();
				certAnchorTO.setUploadedTimeStamp(uploadedTimestamp);
				allCerts.add(certAnchorTO);
			}
			resultSet.getResults().addAll(allCerts);
		}
		return resultSet;
	}

}
