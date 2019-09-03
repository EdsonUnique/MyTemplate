package book.utils;

import book.exceptions.MyException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class UploadFileUtils {

    public static String writeToUploadFiles(MultipartFile data,String storeLocation) throws Exception{

        BufferedInputStream bis=null;
        BufferedOutputStream bos=null;
        String filePath=null;
        File destFile;

        try{
            bis=new BufferedInputStream(data.getInputStream());

            File file=new File(storeLocation);
            if(!file.exists()){
                file.mkdirs();
            }

            byte[] buffer=new byte[1024];
            int len=0;

            filePath=file+"/"+ UUID.randomUUID().toString()+data.getOriginalFilename();//存储路径
            destFile=new File(filePath);
            bos=new BufferedOutputStream(new FileOutputStream(destFile));

            while((len=bis.read(buffer))!=-1){
                bos.write(buffer);
                bos.flush();
            }


        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("文件存储失败！");
        }finally {
            try{
                bis.close();
                bos.close();
            }catch (Exception e){
                e.printStackTrace();
                System.err.println("流关闭失败");
                throw new MyException("文件存储失败");
            }

        }

        return destFile.getPath();
    }

    /**
     * 获取本地文件并返回给浏览器
     */
    public static void writeFileToClient(HttpServletResponse response,String filePath) throws Exception{

        if(null==filePath || !new File(filePath).exists()){
            throw new MyException("文件已不存在！");
        }

        File file=new File(filePath);
        String title=filePath.substring(filePath.lastIndexOf("\\"));

        BufferedInputStream bis=null;
        OutputStream os=null;
        try{

            bis=new BufferedInputStream(new FileInputStream(file));

            byte[] buffer=new byte[1024];
            int len=0;

            //防止中文乱码
            String headStr = "attachment;filename=\"" + new String( title.getBytes("gbk"), "iso8859-1" ) + "\"";
            response.reset();
            response.setHeader("Content-disposition", headStr);
            response.setContentType("application/x-download");
            os=response.getOutputStream();

            while((len=bis.read(buffer))!=-1){
                os.write(buffer);
                os.flush();
            }

        }catch (Exception e){
            e.printStackTrace();
            throw new MyException("操作失败！");
        }finally {
            try{
                bis.close();
                os.close();
            }catch (Exception e){
                e.printStackTrace();
                System.err.println("流关闭失败！");
            }
        }


    }

}
