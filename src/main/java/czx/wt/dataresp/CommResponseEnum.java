package czx.wt.dataresp;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 14:23 2018/8/24
 * @Modified By:
 */
public enum CommResponseEnum implements IResponseEnum {

    FAILURE(1001,"系统错误"),

    USER1(2001,"没有该用户"),
    USER2(2002,"密码输入错误"),
    USER3(2003,"用户密码错误超过5次,请次日再试"),
    USER4(2004,"密码过期,请修改密码"),
    USER5(2005,"密码不能为空"),
    USER6(2006,"密码必须包含英文大写以及数字"),
    USER7(2007,"请先登录"),

    LOGIN1(3001,"登录日志记录失败!"),

    VALID1(4001,"验证码不能为空"),
    VALID2(4002,"验证码已过期"),
    VALID3(4003,"验证码不匹配"),
    SUCCESS(200,"操作成功");

    private int respCode;
    private String respMsg;

    CommResponseEnum(int respCode, String respMsg) {
        this.respCode = respCode;
        this.respMsg = respMsg;
    }

    @Override
    public int code() {
        return this.respCode;
    }

    @Override
    public String msg() {
        return this.respMsg;
    }
}
