package czx.wt.service.impl;

import czx.wt.mapper.UserMapper;
import czx.wt.pojo.User;
import czx.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 10:47 2018/8/24
 * @Modified By:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateLastTimeByErrorCount() {
        String lastModifyTime = String.valueOf(System.currentTimeMillis());
        userMapper.updateLastTimeByErrorCount(lastModifyTime);
    }
}
