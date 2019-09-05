package cn.druglots.mall.core.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.filter
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-05 23:15
 * @Description: 跨域过滤器
 */
@Slf4j
@Order(Integer.MIN_VALUE)
@WebFilter(filterName = "corsFilter",urlPatterns = "/*")
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        res.setContentType("text/html;charset=UTF-8");
        res.setHeader("Access-Control-Allow-Origin", "*");
        res.setHeader("Access-Control-Allow-Methods", "*");
        res.setHeader("Access-Control-Max-Age", "0");
        res.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("XDomainRequestAllowed","1");
        if(log.isInfoEnabled()){
            log.info("cors过滤器");
        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
