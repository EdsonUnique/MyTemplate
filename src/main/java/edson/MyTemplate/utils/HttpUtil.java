package edson.MyTemplate.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author: yangxi
 * @Date: 2021/8/5 14:52
 */
public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    /**
     * @description:POST 请求
     * 其中 url为请求路径  paramMap是请求类型为x-www-form-urlencoded是的请求参数
     * header为请求头内容，一般为Token
     * json是请求类型为application/json 的请求参数  是json字符串
     * @author: yangxi
     * @date: 2021/8/6 11:41
     */
    public static String post(String url, Map<String, String> paramMap, Header header,String json) throws Exception {
        String responseMsg = "";
        PostMethod postMethod = new PostMethod(url);
        if(null != header){
            postMethod.setRequestHeader(header);
        }

        //设置map参数
        if(paramMap != null && paramMap.size() > 0){
            for (String key : paramMap.keySet()) {
                postMethod.setParameter(key, paramMap.get(key));
            }
        }

        //设置json参数
        if(StringUtils.hasText(json)){
            RequestEntity se = new StringRequestEntity(json ,"application/json" ,"UTF-8");
            postMethod.setRequestEntity(se);
            postMethod.setRequestHeader("Content-Type","application/json");
        }

        HttpClient httpClient = new HttpClient();
        try {

            httpClient.setConnectionTimeout(50000);
            httpClient.executeMethod(postMethod);// 200
            responseMsg = postMethod.getResponseBodyAsString();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }
        return new String(responseMsg.getBytes("ISO-8859-1"),"utf-8");
    }

}
