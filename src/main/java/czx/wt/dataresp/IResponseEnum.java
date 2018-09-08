package czx.wt.dataresp;

/**
 * @Author:ChenZhiXiang
 * @Description: 返回信息的类
 * @Date:Created in 14:17 2018/8/24
 * @Modified By:
 */
public interface IResponseEnum {
    /**
     *@Description: 返回码
     *@Date: 14:18 2018/8/24
     */
    int code();

    /**
     *@Description: 返回文本信息
     *@Date: 14:19 2018/8/24
     */
    String msg();
}
