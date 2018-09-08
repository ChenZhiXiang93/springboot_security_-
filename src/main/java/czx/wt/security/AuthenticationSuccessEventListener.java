package czx.wt.security;

import czx.wt.pojo.User;
import czx.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


/**
 * @Author:ChenZhiXiang
 * @Description: 登录成功监听
 * @Date:Created in 10:34 2018/8/27
 * @Modified By:
 */
@Component
public class AuthenticationSuccessEventListener implements
        ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    private UserService userService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        UserDetails userDetails = (UserDetails) authenticationSuccessEvent.getAuthentication().getPrincipal();
        String username = userDetails.getUsername();
        User user = userService.getUserByName(username);
        user.setErrorCount(0);
        user.setLastModifyTime(String.valueOf(System.currentTimeMillis()));
        userService.updateUser(user);
    }
}
