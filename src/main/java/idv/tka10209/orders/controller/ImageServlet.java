package idv.tka10209.orders.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

/**
 * 把 {@link UploadServlet#resolveUploadDir()} 目錄（在 {@code webapps} 之外）裡的圖檔對外提供。
 * <p>
 * 掛在 {@code /upload/*}，JSP 寫
 * {@code ${pageContext.request.contextPath}/upload/檔名} 即可， 不需要在 Tomcat 另外設定
 * &lt;Context&gt;／{@code web.xml}／{@code context.xml}， Eclipse 與獨立 Apache
 * Tomcat 行為一致。
 * <p>
 * 效能處理（不需要任何設定檔）：
 * <ol>
 * <li>先用 ETag／If-Modified-Since 比對「檔案大小＋修改時間」，沒變就回 304，完全不傳內容。</li>
 * <li>要傳的時候，若連接器支援 <b>sendfile</b>（Tomcat NIO，非 TLS）就交給 Tomcat 做零複製傳輸， 位元組不經過
 * JVM heap，效能等同內建 DefaultServlet；不支援時才退回 {@link Files#copy}。</li>
 * </ol>
 */
@WebServlet("/upload/*")
public class ImageServlet extends HttpServlet {

    // Tomcat sendfile 用的 request attribute 名稱（Tomcat 6 起穩定不變）
    private static final String SENDFILE_SUPPORT = "org.apache.tomcat.sendfile.support";
    private static final String SENDFILE_FILENAME = "org.apache.tomcat.sendfile.filename";
    private static final String SENDFILE_START = "org.apache.tomcat.sendfile.start";
    private static final String SENDFILE_END = "org.apache.tomcat.sendfile.end";

    private File uploadDir;

    @Override
    public void init() throws ServletException {
        this.uploadDir = UploadServlet.resolveUploadDir();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        File file = new File(uploadDir, pathInfo.substring(1));

        // 防止路徑跳脫（../），確認檔案真的在 upload 目錄下
        File canonical = file.getCanonicalFile();
        if (!canonical.toPath().startsWith(uploadDir.getCanonicalFile().toPath()) || !canonical.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        long lastModified = canonical.lastModified() / 1000 * 1000; // 截到秒，與 HTTP 日期標頭一致
        long length = canonical.length();
        String etag = "\"" + length + "-" + lastModified + "\"";

        response.setHeader("ETag", etag);
        response.setDateHeader("Last-Modified", lastModified);

        // (1) 檔案的大小與修改時間都沒變 → 回 304，不傳內容
        String ifNoneMatch = request.getHeader("If-None-Match");
        long ifModifiedSince = request.getDateHeader("If-Modified-Since");
        boolean notModified = (ifNoneMatch != null) ? ifNoneMatch.equals(etag)
                : (ifModifiedSince != -1 && ifModifiedSince >= lastModified);
        if (notModified) {
            response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
            System.out.println("回傳 304");
            return;
        }

        String contentType = getServletContext().getMimeType(canonical.getName());
        response.setContentType(contentType != null ? contentType : "application/octet-stream");
        response.setContentLengthLong(length);

        // (2) 連接器支援 sendfile → 交給 Tomcat 零複製傳輸，不要自己寫 output stream
        if (Boolean.TRUE.equals(request.getAttribute(SENDFILE_SUPPORT))) {
            request.setAttribute(SENDFILE_FILENAME, canonical.getAbsolutePath());
            request.setAttribute(SENDFILE_START, Long.valueOf(0L));
            request.setAttribute(SENDFILE_END, Long.valueOf(length));
            System.out.println("連接器支援 sendfile");
            return;
        }

        // (3) 退路：sendfile 不可用（TLS 連線、非 Tomcat 容器…）才自己複製
        System.out.println("Use Files.copy");
        try (OutputStream out = response.getOutputStream()) {
            Files.copy(canonical.toPath(), out);
        }
    }
}
