package edson.MyTemplate.utils;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.logging.log4j.util.Strings.isBlank;

/**
 * 时间格式化
 */
public class DateFormatUtil {

    /**
     * 日期时间转字符串
     * @param date
     * @param pattern "yyyy-MM-dd HH:mm:ss"
     * @return
     */
    public static String getStrFromDateTime(LocalDateTime date,String pattern){

        if(null==date || isBlank(pattern))return "";

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        String res=date.format(formatter);
        return res;
    }

    public static LocalDateTime getDateTimeFromStr(String dateStr,String pattern){

        if(null == dateStr || isBlank(pattern))return null;

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        LocalDateTime ldt=LocalDateTime.parse(dateStr,formatter);

        return ldt;
    }

    /**
     * 字符串转日期
     * @param dateStr
     * @param pattern "yyyy-MM-dd"
     * @return
     */
    public static LocalDate getDateFromStr(String dateStr,String pattern){
        if(null == dateStr || isBlank(pattern))return null;

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);

        LocalDate ld=LocalDate.parse(dateStr,formatter);

        return ld;
    }

    public static String getStrFromDate(LocalDate date,String pattern){

        if(null==date || isBlank(pattern))return "";

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern(pattern);
        String res=date.format(formatter);
        return res;
    }


}
