package library;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.Part;

public class FileLibrary {
	public static String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : partHeader.split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	// đổi tên file
	public static String renameFile(String fileName) {

		String[] arrName = fileName.split("\\.");

		String duoiFile = arrName[arrName.length - 1];

		String tenFile = "";
		tenFile = arrName[0];

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
		tenFile = tenFile + "-" + sdf.format(date) + "." + duoiFile;
		return tenFile;
	}
}
