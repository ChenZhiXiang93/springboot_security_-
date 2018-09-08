package czx.wt.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 14:05 2018/8/24
 * @Modified By:
 */
public class DateUtils {

    /**
     *@Author:ChenZhiXiang
     *@Description: 获取当前日期的前几个月
     *@Date: 14:07 2018/8/24
     */
    public static String beforeMonth(Integer month){
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH,month);
        Date date = calendar.getTime();
        return df.format(date);
    }

    /**
     *@Author:ChenZhiXiang
     *@Description: 获得当前时间，格式yyyy:MM:dd HH:mm:ss
     *@Date: 9:11 2018/8/29
     */
    public static String getNowTime(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
        Date date = new Date();
        return df.format(date);
    }
}
