package czx.wt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import czx.wt.dataresp.CommResponseEnum;
import czx.wt.dataresp.ResponseData;
import czx.wt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 22:59 2018/8/30
 * @Modified By:
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    private static final String NOT_NULL = "验证码不能为空";
    private static final String NOT_MATCH = "验证码不匹配";
    private static final String NOT_EXPIRE = "验证码已过期";
    private static final String NOT_USER1 = "没有该用户";
    private static final String NOT_USER2 = "密码过期";
    private static final String NOT_USER3 = "用户密码错误超过5次,请次日再试";
    private static final String NOT_USER4 = "Bad credentials";

    private static final Integer FAIL_MAX_COUNT = 5;


    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        System.out.println("异常"+exception);
        if (exception.toString().indexOf(NOT_NULL) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.VALID1)));
            /*throw new GlobalException(CommResponseEnum.VALID1);*/
        }
        if (exception.toString().indexOf(NOT_MATCH) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.VALID3)));
            /*throw new GlobalException(CommResponseEnum.VALID3);*/
        }
        if (exception.toString().indexOf(NOT_EXPIRE) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.VALID2)));
            /*throw new GlobalException(CommResponseEnum.VALID2);*/
        }
        if (exception.toString().indexOf(NOT_USER1) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER1)));
        }
        if (exception.toString().indexOf(NOT_USER2) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER4)));
        }
        if (exception.toString().indexOf(NOT_USER3) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER3)));
        }
        if (exception.toString().indexOf(NOT_USER4) != -1){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(
                    objectMapper.writer().writeValueAsString(new ResponseData(CommResponseEnum.USER2)));
        }
    }
}
