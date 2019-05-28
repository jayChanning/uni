package edu.uni.gradeManagement1.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
* @author 陈少鑫
* @description 日期格式工具类
* @date 19:14 2019-05-12
* @modified 19:14 2019-05-12
*/
public class MyDataUtil {

    /** 年月日 时分秒模式字符串 */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_PATTERN = "yyyy-MM-dd HH:mm:ss";

    /** 年月日模式字符串 */
    public static final String YEAR_MONTH_DAY_PATTERN = "yyyy-MM-dd";

    /** 时分秒模式字符串 */
    public static final String HOUR_MINUTE_SECOND_PATTERN = "HH:mm:ss";

    /** 年月日时分模式字符串 */
    public static final String YMDHMS_PATTERN = "yyyy-MM-dd HH:mm";
    /** 年月日时模式字符串 */
    public static final String YMDH_PATTERN = "yyyy-MM-dd HH";


    private static SimpleDateFormat YMDHMSsdf = new SimpleDateFormat(YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_PATTERN);
    private static SimpleDateFormat YMDsdf = new SimpleDateFormat(YEAR_MONTH_DAY_PATTERN);

    /**
     *
     * 获取（yyyy-MM-dd HH:mm:ss）格式的日期时间
     *
     * @author 陈少鑫
     * @param date   要格式化的日期对象
     * @return 格式化后的日期字符串
     *
     * @date 11:10 2019-05-12
     */

    public static String getFormatYMDHMSDateTime(Date date){
        return YMDHMSsdf.format(date);
    }

    /**
     *
     * 获取（yyyy-MM-dd HH:mm）格式的日期时间
     *
     * @author 陈少鑫
     * @param date   要格式化的日期对象
     * @return 格式化后的日期字符串
     *
     * @date 11:10 2019-05-12
     */

    public static String getFormatYMDHateTime(Date date){
        return YMDsdf.format(date);
    }
}
