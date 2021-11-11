package edson.MyTemplate.utils.excel;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public class FileRenderUtil {

    private static final String DEFAULT_CONTENT_TYPE = "application/octet-stream";
    private File file;
    private static String baseDownloadPath;
    private static ServletContext servletContext;
    private String downloadFileName;
    protected String view;
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private static String encoding = "UTF-8";

    public FileRenderUtil(File file) {
        this.downloadFileName = null;
        if (file == null) {
            throw new IllegalArgumentException("file can not be null.");
        } else {
            this.file = file;
        }
    }

    public FileRenderUtil(File file, String downloadFileName) {
        this(file);
        if (StringUtils.isBlank(downloadFileName)) {
            throw new IllegalArgumentException("downloadFileName can not be blank.");
        } else {
            this.downloadFileName = downloadFileName;
        }
    }

    public FileRenderUtil(String fileName) {
        this.downloadFileName = null;
        if (StringUtils.isBlank(fileName)) {
            throw new IllegalArgumentException("fileName can not be blank.");
        } else {
            fileName = fileName.trim();
            String fullFileName;
            if (!fileName.startsWith("/") && !fileName.startsWith("\\")) {
                fullFileName = baseDownloadPath + File.separator + fileName;
            } else if (baseDownloadPath.equals("/")) {
                fullFileName = fileName;
            } else {
                fullFileName = baseDownloadPath + fileName;
            }

            this.file = new File(fullFileName);
        }
    }

    public FileRenderUtil(String fileName, String downloadFileName) {
        this(fileName);
        if (StringUtils.isBlank(downloadFileName)) {
            throw new IllegalArgumentException("downloadFileName can not be blank.");
        } else {
            this.downloadFileName = downloadFileName;
        }
    }

    static void init(String baseDownloadPath, ServletContext servletContext) {
        baseDownloadPath = baseDownloadPath;
        servletContext = servletContext;
    }

    public void render() throws Exception{
        if (this.file != null && this.file.isFile()) {
            this.response.setHeader("Accept-Ranges", "bytes");
            String fn = this.downloadFileName == null ? this.file.getName() : this.downloadFileName;
            this.response.setHeader("Content-disposition", "attachment; " + this.encodeFileName(this.request, fn));
            String contentType = servletContext.getMimeType(this.file.getName());
            this.response.setContentType(contentType != null ? contentType : "application/octet-stream");
            if (StringUtils.isBlank(this.request.getHeader("Range"))) {
                this.normalRender();
            } else {
                this.rangeRender();
            }

        }
    }

    protected String encodeFileName(String fileName) {
        try {
            return new String(fileName.getBytes(encoding), "ISO8859-1");
        } catch (UnsupportedEncodingException var3) {
            return fileName;
        }
    }

    public static String encodeFileName(HttpServletRequest request, String fileName) {
        String userAgent = request.getHeader("User-Agent");

        try {
            String e = URLEncoder.encode(fileName, "UTF8");
            if (userAgent == null) {
                return "filename=\"" + e + "\"";
            } else {
                userAgent = userAgent.toLowerCase();
                return userAgent.indexOf("msie") != -1 ? "filename=\"" + e + "\"" : (userAgent.indexOf("opera") != -1 ? "filename*=UTF-8\'\'" + e : (userAgent.indexOf("safari") == -1 && userAgent.indexOf("applewebkit") == -1 && userAgent.indexOf("chrome") == -1 ? (userAgent.indexOf("mozilla") != -1 ? "filename*=UTF-8\'\'" + e : "filename=\"" + e + "\"") : "filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + "\""));
            }
        } catch (UnsupportedEncodingException var5) {
            throw new RuntimeException(var5);
        }
    }

    private void normalRender() throws Exception{
        this.response.setHeader("Content-Length", String.valueOf(this.file.length()));
        BufferedInputStream inputStream = null;
        ServletOutputStream outputStream = null;

        try {
            inputStream = new BufferedInputStream(new FileInputStream(this.file));
            outputStream = this.response.getOutputStream();
            byte[] e = new byte[1024];
            boolean len = true;

            int len1;
            while ((len1 = inputStream.read(e)) != -1) {
                outputStream.write(e, 0, len1);
            }

            outputStream.flush();
        } catch (IOException var18) {
            throw new Exception(var18);
        } catch (Exception var19) {
            throw new Exception(var19);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var17) {
                    System.err.println(var17.getMessage());
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var16) {
                    System.err.println(var16.getMessage());
                }
            }

        }

    }

    private void rangeRender() throws  Exception{
        Long[] range = new Long[]{null, null};
        this.processRange(range);
        String contentLength = String.valueOf(range[1].longValue() - range[0].longValue() + 1L);
        this.response.setHeader("Content-Length", contentLength);
        this.response.setStatus(206);
        StringBuilder contentRange = (new StringBuilder("bytes ")).append(String.valueOf(range[0])).append("-").append(String.valueOf(range[1])).append("/").append(String.valueOf(this.file.length()));
        this.response.setHeader("Content-Range", contentRange.toString());
        BufferedInputStream inputStream = null;
        ServletOutputStream outputStream = null;

        try {
            long e = range[0].longValue();
            long end = range[1].longValue();
            inputStream = new BufferedInputStream(new FileInputStream(this.file));
            if (inputStream.skip(e) != e) {
                throw new RuntimeException("File skip error");
            }

            outputStream = this.response.getOutputStream();
            byte[] buffer = new byte[1024];
            long position = e;

            while (true) {
                int len;
                while (position <= end && (len = inputStream.read(buffer)) != -1) {
                    if (position + (long) len <= end) {
                        outputStream.write(buffer, 0, len);
                        position += (long) len;
                    } else {
                        for (int i = 0; i < len && position <= end; ++i) {
                            outputStream.write(buffer[i]);
                            ++position;
                        }
                    }
                }

                outputStream.flush();
                break;
            }
        } catch (IOException var28) {
            throw new Exception(var28);
        } catch (Exception var29) {
            throw new Exception(var29);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var27) {
                    System.err.println(var27.getMessage());
                }
            }

            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException var26) {
                    System.err.println(var26.getMessage());
                }
            }

        }

    }

    private void processRange(Long[] range) {
        String rangeStr = this.request.getHeader("Range");
        int index = rangeStr.indexOf(44);
        if (index != -1) {
            rangeStr = rangeStr.substring(0, index);
        }

        rangeStr = rangeStr.replace("bytes=", "");
        String[] arr = rangeStr.split("-", 2);
        if (arr.length < 2) {
            throw new RuntimeException("Range error");
        } else {
            long fileLength = this.file.length();

            for (int i = 0; i < range.length; ++i) {
                if (StringUtils.isNotBlank(arr[i])) {
                    range[i] = Long.valueOf(Long.parseLong(arr[i].trim()));
                    if (range[i].longValue() >= fileLength) {
                        range[i] = Long.valueOf(fileLength - 1L);
                    }
                }
            }

            if (range[0] != null && range[1] == null) {
                range[1] = Long.valueOf(fileLength - 1L);
            } else if (range[0] == null && range[1] != null) {
                range[0] = Long.valueOf(fileLength - range[1].longValue());
                range[1] = Long.valueOf(fileLength - 1L);
            }

            if (range[0] == null || range[1] == null || range[0].longValue() > range[1].longValue()) {
                throw new RuntimeException("Range error");
            }
        }
    }
}
