package czx.wt.security;

import czx.wt.pojo.User;
import czx.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

/**
 * @Author:ChenZhiXiang
 * @Description: 认证失败监听
 * @Date:Created in 10:45 2018/8/27
 * @Modified By:
 */
@Component
public class AuthenticationFailureListener implements
        ApplicationListener<AuthenticationFailureBadCredentialsEvent> {
    private static final Integer FAIL_MAX_COUNT = 5;
    @Autowired
    private UserService userService;

    /**
     *@Author:ChenZhiXiang
     *@Description: 密码错误更新错误次数
     *@Date: 11:27 2018/9/3
     */
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent authenticationFailureBadCredentialsEvent) {
        String username = authenticationFailureBadCredentialsEvent.getAuthentication().getPrincipal().toString();
        User user = userService.getUserByName(username);
        if (user != null){
            //用户登录失败次数
            int failCount = user.getErrorCount();
            failCount = failCount + 1;
            user.setErrorCount(failCount);
            userService.updateUser(user);
            //  超出失败次数，停用账户
            if (user.getErrorCount() >= FAIL_MAX_COUNT){
                user.setLastModifyTime(String.valueOf(System.currentTimeMillis()));
                userService.updateUser(user);
                return;
            }
        }
    }
}
