package edson.MyTemplate.utils.excel;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/10/30 0030.
 */
public class ExcelExportUtil {

    private final static String CONTENT_TYPE = "application/msexcel;charset=utf-8";
    private List<?>[] data;
    private String[][] headers;
    private String[] sheetNames = new String[]{};
    private int cellWidth;
    private String[] columns = new String[]{};
    private String fileName = "file.xls";
    private int headerRow;
    private String version;
    protected String view;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    public ExcelExportUtil(HttpServletRequest request, HttpServletResponse response, List<?>[] data) {
        this.request = request;
        this.response = response;
        this.data = data;
    }

    public static ExcelExportUtil me(HttpServletRequest request, HttpServletResponse response, List<?>... data) {
        return new ExcelExportUtil(request, response, data);
    }

    public void render() throws Exception{
        response.reset();
        response.setHeader("Content-disposition", "attachment; " + FileRenderUtil.encodeFileName(this.request, fileName));
        response.setContentType(CONTENT_TYPE);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/x-msdownload;charset=utf-8");
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            PoiExporter.data(data).version(version).sheetNames(sheetNames).headerRow(headerRow).headers(headers).columns(columns)
                    .cellWidth(cellWidth).export().write(os);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            try {
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }

        }
    }

    public ExcelExportUtil headers(String[]... headers) {
        this.headers = headers;
        return this;
    }

    public ExcelExportUtil headerRow(int headerRow) {
        this.headerRow = headerRow;
        return this;
    }

    public ExcelExportUtil columns(String... columns) {
        this.columns = columns;
        return this;
    }

    public ExcelExportUtil sheetName(String... sheetName) {
        this.sheetNames = sheetName;
        return this;
    }

    public ExcelExportUtil cellWidth(int cellWidth) {
        this.cellWidth = cellWidth;
        return this;
    }

    public ExcelExportUtil fileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ExcelExportUtil version(String version) {
        this.version = version;
        return this;
    }

}
