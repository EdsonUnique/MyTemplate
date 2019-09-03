package cn.reegle.deductgoodsmanagerment.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.logging.log4j.util.Strings.isBlank;

/**
 * 时间格式化
 */
public class DateFormatUtil {

    public static String getDateStr(LocalDateTime date){

        if(null==date)return "";

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String res=date.format(formatter);
        return res;
    }

    public static String getDateStr(LocalDateTime date,String pattern){

        if(null==date || isBlank(pattern))return "";

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        String res=date.format(formatter);
        return res;
    }

}
