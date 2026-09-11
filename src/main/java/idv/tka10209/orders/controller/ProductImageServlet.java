package idv.tka10209.orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.hibernate.Session;
import org.hibernate.Transaction;

import idv.tka10209.orders.dao.ProductImageDaoImpl;
import idv.tka10209.orders.entity.ProductImage;
import idv.tka10209.util.HibernateUtil;

/**
 * Servlet implementation class UploadImageServlet
 */
@WebServlet("/ProductImageServlet")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, 
        maxFileSize = 1024 * 1024 * 50, 
        maxRequestSize = 1024 * 1024 * 50)
public class ProductImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductImageDaoImpl productImageDao = new ProductImageDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
        String skuIdStr = request.getParameter("skuId");
        Part filePart = request.getPart("uploadFile");

        if (filePart == null || filePart.getSize() == 0) {
            response.getWriter().println("未選擇檔案");
            return;
        }
        
        String fileName = filePart.getSubmittedFileName();
        String contentType = filePart.getContentType();
        
        String lowerName = fileName.toLowerCase();
        if (!(lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg")
                || lowerName.endsWith(".png") || lowerName.endsWith(".gif"))) {
            response.getWriter().println("僅允許上傳圖片檔案");
            return;
        }
        
        // 把 Part 的 InputStream 讀成 byte[]，準備存進 imageData 欄位
        byte[] imageBytes;
        try (InputStream input = filePart.getInputStream();
             ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {

            byte[] chunk = new byte[8192];
            int length;
            while ((length = input.read(chunk)) != -1) {
                buffer.write(chunk, 0, length);
            }
            imageBytes = buffer.toByteArray();
        }

        ProductImage productImage = new ProductImage();
        productImage.setSkuId(Integer.parseInt(skuIdStr));
        productImage.setImageData(imageBytes);
        productImage.setImageName(fileName);
        productImage.setImageType(contentType);
        productImage.setFileSize((int) filePart.getSize());
        productImage.setSortOrder(3);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            productImageDao.save(productImage);
            tx.commit();

            response.getWriter().println("上傳成功，圖片 ID：" + productImage.getImageId());
            System.out.println("上傳成功，圖片 ID：" + productImage.getImageId());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("上傳失敗");
            throw new ServletException("上傳失敗", e);
        }
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
