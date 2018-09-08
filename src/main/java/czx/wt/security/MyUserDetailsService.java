package czx.wt.security;

import czx.wt.dataresp.CommResponseEnum;
import czx.wt.exception.GlobalException;
import czx.wt.pojo.User;
import czx.wt.service.UserService;
import czx.wt.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;



/**
 * @Author:ChenZhiXiang
 * @Description: SpringSecurity中用户查询类   不在这里判断密码错误的原因是密码这个参数没有
 * @Date:Created in 10:35 2018/8/24
 * @Modified By:
 */
@Component("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private MyPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s){
        User user = userService.getUserByName(s);
        if (user == null){
            throw new GlobalException(CommResponseEnum.USER1);
        }
        //密码过期
        if (Integer.parseInt(user.getExpired()) < Integer.parseInt(DateUtils.beforeMonth(-1))){
            throw new GlobalException(CommResponseEnum.USER4);
        }
        if (user.getErrorCount() >= 5 && Long.parseLong(user.getLastModifyTime()) > (System.currentTimeMillis()-3600*24*1000)){
            //  超出失败次数，停用账户
            throw new GlobalException(CommResponseEnum.USER3);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
