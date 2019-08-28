package cn.druglots.mall.core.exception;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.exception
 * @Author: king-pan
 * @CreateTime: 2019-08-28 10:26
 * @Description: 全局异常处理器, 注意捕获异常类型不能重复，不然会报异常:
 * Ambiguous @ExceptionHandler method mapped for
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * json格式异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object jsonErrorHandler(Exception e, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 100);
        result.put("msg", e.getMessage());
        result.put("url", request.getRequestURL());
        return result;
    }

    /**
     * 异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    public Object viewErrorHandler(HttpServletRequest request, BusinessException e) {
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        boolean containsFlag = false;
        if(StringUtils.isNotBlank(contentTypeHeader)){
            containsFlag = contentTypeHeader.contains("application/json");
        }
        boolean xmlRequestFlag = "XMLHttpRequest".equalsIgnoreCase(xRequestedWith);
        if (contentTypeHeader != null && containsFlag) {
            return getErrorResult(request, e);
        }
        if (acceptHeader != null && acceptHeader.contains("application/json")) {
            return getErrorResult(request, e);
        }
        if (xmlRequestFlag) {
            return getErrorResult(request, e);
        }
        ModelAndView view = new ModelAndView("error");
        view.addObject("code", e.getCode());
        view.addObject("msg", e.getMsg());
        view.addObject("url", request.getRequestURL());
        view.addObject("stackTrace", e.getStackTrace());
        return view;
    }

    private Map<String, Object> getErrorResult(HttpServletRequest request, BusinessException e) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", e.getCode());
        result.put("msg", e.getMsg());
        result.put("url", request.getRequestURL());
        return result;
    }

    /**
     * 判断请求是否是ajax请求
     *
     * @param httpRequest 请求
     * @return
     */
    public static boolean isAjax(HttpServletRequest httpRequest) {
        return (httpRequest.getHeader("X-Requested-With") != null
                && "XMLHttpRequest"
                .equals(httpRequest.getHeader("X-Requested-With").toString()));
    }
}
