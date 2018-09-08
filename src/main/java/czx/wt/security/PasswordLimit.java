package czx.wt.security;

/**
 * @Author:ChenZhiXiang
 * @Description: 密码是否符合要求
 * @Date:Created in 15:57 2018/8/24
 * @Modified By:
 */
public class PasswordLimit {

    public static Boolean isPass(String password){
        //判断密码是否包含数字：包含返回1，不包含返回0；
        int i = password.matches(".\\d+.*") ? 1 : 0;
        //判断密码是否包含小写字母：包含返回1，不包含返回0；
        int j = password.matches(".*[a-z]+.*") ? 1 : 0;
        //判断密码是否包含大写字母：包含返回1，不包含返回0；
        int m = password.matches(".*[A-Z]+.*") ? 1 : 0;
        // 判断密码是否包含特殊符号(~!@#$%^&*()_+|<>,.?/:;'[]{}\)：包含返回1，不包含返回0
        int k = password.matches(".*[~!@#$%^&*()_+|<>,.?/:;'\\[\\]{}\"]+.*") ? 1 : 0;

        // 判断密码长度是否在6-16位
        int l = password.length();

        // 判断密码中是否包含用户名
        /*boolean contains = password.contains(username);*/

        if (i + j + k + m < 3 || l < 8 || l > 16) {
            return false;
        }else {
            return true;
        }
    }
}
