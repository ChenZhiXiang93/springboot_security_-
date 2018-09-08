package czx.wt.pojo;

import java.io.Serializable;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 21:33 2018/8/28
 * @Modified By:
 */
public class LoginLogInfo implements Serializable {

    private Integer id;

    private String loginName;

    private String loginTime;

    private String loginIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }
}
