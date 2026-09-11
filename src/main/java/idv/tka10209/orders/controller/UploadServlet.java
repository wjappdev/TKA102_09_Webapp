package idv.tka10209.orders.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class UploadServlet extends HttpServlet {
	private String uploadDirPath;

	public static File resolveUploadDir() throws ServletException {
		String tomcatBase = System.getProperty("catalina.base");
		if (tomcatBase == null) {
			tomcatBase = System.getProperty("catalina.home");
		}
		File uploadDir = new File(tomcatBase, "upload");
		if (!uploadDir.exists() && !uploadDir.mkdirs()) {
			throw new ServletException("無法建立上傳目錄：" + uploadDir.getAbsolutePath());
		}
		return uploadDir;
	}

	@Override
	public void init() throws ServletException {
		this.uploadDirPath = resolveUploadDir().getAbsolutePath();
		System.out.println("圖檔上傳目錄設定為：" + this.uploadDirPath);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		Part filePart = request.getPart("uploadFile");
		if (filePart == null || filePart.getSize() == 0) {
			response.getWriter().println("未選擇檔案");
			return;
		}

		String fileName = filePart.getSubmittedFileName();
		String lowerName = fileName.toLowerCase();
		if (!(lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png")
				|| lowerName.endsWith(".gif"))) {
			response.getWriter().println("僅允許上傳圖片檔案");
			return;
		}

		String newFileName = fileName;
		File targetFile = new File(uploadDirPath, newFileName);
		String filePath = targetFile.getAbsolutePath();
		try (InputStream input = filePart.getInputStream(); FileOutputStream output = new FileOutputStream(filePath)) {
			byte[] buffer = new byte[4096];
			int length;
			while ((length = input.read(buffer)) != -1) {
				output.write(buffer, 0, length);
			}
		}

		response.getWriter().println("上傳成功：" + newFileName + "<br>");
		response.getWriter().println("實際儲存路徑：" + targetFile.getAbsolutePath());
	}
}
