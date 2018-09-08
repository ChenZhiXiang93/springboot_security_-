package czx.wt.security;

import czx.wt.dataresp.CommResponseEnum;
import czx.wt.exception.GlobalException;
import czx.wt.util.AesEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author:ChenZhiXiang
 * @Description: 自定义密码加密
 * @Date:Created in 22:16 2018/8/26
 * @Modified By:
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {

    private static final String KEY = "123456";

    @Override
    public String encode(CharSequence charSequence) {
        String pwd = charSequence.toString();
        System.out.println("前端传过来的明文密码1:" + pwd);
        System.out.println("加密后:" + AesEncoder.encrypt(charSequence.toString(),KEY));
        return AesEncoder.encrypt(charSequence.toString(),KEY);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        String pwd = charSequence.toString();
        System.out.println("前端传过来的明文密码2:" + pwd);
        if (pwd == null || pwd.equals("")){
            throw new GlobalException(CommResponseEnum.USER5);
        }
        if (PasswordLimit.isPass(pwd)){
            throw new GlobalException(CommResponseEnum.USER6);
        }
        String md5Pwd = AesEncoder.encrypt(charSequence.toString(),KEY).toUpperCase();
        System.out.println("加密后:" + md5Pwd);
        if(md5Pwd.equals(s)){
            System.out.println("pass");
            return true;
        }else {
            /*throw new ApplicationException(CommResponseEnum.USER2);*/
            return false;
        }
    }
}
