package czx.wt.service;

import czx.wt.pojo.User;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:46 2018/8/24
 * @Modified By:
 */
public interface UserService {

    User getUserByName(String username);

    void updateUser(User user);

    void updateLastTimeByErrorCount();
}
