package czx.wt.controller;

import czx.wt.dataresp.CommResponseEnum;
import czx.wt.dataresp.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 17:17 2018/8/28
 * @Modified By:
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    /**存放请求的缓存*/
    private RequestCache requestCache = new HttpSessionRequestCache();
    /**SpringSecurity中的跳转工具*/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
/**
 *@Author:ChenZhiXiang
 *@Description: 用户访问的时候，判断请求是否是html和jsp页面，不是返回登录信息
 *@Date: 17:22 2018/8/28
 */
    @GetMapping("/auth")
    public Object login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //从缓存中拿到请求
        SavedRequest savedRequest = requestCache.getRequest(request,response);
        if (savedRequest != null){
            String url = savedRequest.getRedirectUrl();
            if (StringUtils.endsWith(url,".html") || StringUtils.endsWith(url,".jsp")){
                redirectStrategy.sendRedirect(request,response,"/html/login.html");
            }else {
                return ResponseData.failureResponse(CommResponseEnum.USER7);
            }
        }
        return null;
    }
}
