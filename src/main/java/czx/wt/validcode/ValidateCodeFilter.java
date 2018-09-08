package czx.wt.validcode;

import czx.wt.exception.ValidateCodeException;
import czx.wt.security.LoginFailureHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:ChenZhiXiang
 * @Description: OncePerRequestFilter : 保证过滤器只被调用一次
 * @Date:Created in 22:57 2018/8/30
 * @Modified By:
 */
public class ValidateCodeFilter extends OncePerRequestFilter {

    private LoginFailureHandler LoginFailureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    // 过滤 逻辑
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 是一个登陆请求
        if (StringUtils.equals("/form/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                // 有异常就返回自定义失败处理器
                LoginFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        // 不是一个登录请求，不做校验 直接通过
        filterChain.doFilter(request, response);
    }

    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(request, ValidController.SESSION_KEY);

        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
        System.out.println(codeInRequest+" "+codeInSession.getCode());
        if (StringUtils.isBlank(codeInRequest) || codeInSession == null) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession.isExpire()) {
            sessionStrategy.removeAttribute(request, ValidController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        if (!StringUtils.equals(codeInSession.getCode().toLowerCase(), codeInRequest.toLowerCase())) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, ValidController.SESSION_KEY);
    }

    public czx.wt.security.LoginFailureHandler getLoginFailureHandler() {
        return LoginFailureHandler;
    }

    public void setLoginFailureHandler(czx.wt.security.LoginFailureHandler loginFailureHandler) {
        LoginFailureHandler = loginFailureHandler;
    }
}
