package czx.wt.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:56 2018/8/30
 * @Modified By:
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7932793974645209799L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
