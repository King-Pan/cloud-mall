package cn.druglots.mall.core.exception;

import cn.druglots.mall.common.result.ResultCode;
import cn.druglots.mall.common.result.ResultGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String JSON_TYPE = "application/json";
    private static final String XML_REQUEST = "XMLHttpRequest";


    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {
        // TODO Auto-generated method stub
        log.error("==============异常开始=============");
        //如果是shiro无权操作，因为shiro 在操作auno等一部分不进行转发至无权限url
        if (ex instanceof UnauthorizedException) {
            ModelAndView mv = new ModelAndView("manage/unauth/index");
            return mv;
        }
        ex.printStackTrace();
        log.error("GlobalExceptionHandler异常处理", ex);
        log.error("==============异常结束=============");
        ModelAndView mv = new ModelAndView("error");
        mv.addObject("exception", ex.toString().replaceAll("\n", "<br/>"));
        return mv;
    }

    /**
     * json格式异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public Object jsonErrorHandler(Exception e, HttpServletRequest request) {
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        boolean containsFlag = false;
        if (StringUtils.isNotBlank(contentTypeHeader)) {
            containsFlag = contentTypeHeader.contains(JSON_TYPE);
        }
        if (acceptHeader != null && containsFlag) {
            return getErrorResult(request, e);
        }
        if (containsFlag) {
            return getErrorResult(request, e);
        }
        boolean xmlRequestFlag = XML_REQUEST.equalsIgnoreCase(xRequestedWith);
        if (xmlRequestFlag) {
            return getErrorResult(request, e);
        }
        ModelAndView view = new ModelAndView("global_error");
        view.addObject("code", 500);
        view.addObject("msg", e.getMessage());
        view.addObject("url", request.getRequestURL());
        view.addObject("stackTrace", e.getStackTrace());
        return view;
    }

    /**
     * 异常处理
     *
     * @param e
     * @param request
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = BusinessException.class)
    public Object viewErrorHandler(HttpServletRequest request, BusinessException e) {
        //使用HttpServletRequest中的header检测请求是否为ajax, 如果是ajax则返回json, 如果为非ajax则返回view(即ModelAndView)
        String contentTypeHeader = request.getHeader("Content-Type");
        String acceptHeader = request.getHeader("Accept");
        String xRequestedWith = request.getHeader("X-Requested-With");
        boolean containsFlag = false;
        if (StringUtils.isNotBlank(contentTypeHeader)) {
            containsFlag = contentTypeHeader.contains(JSON_TYPE);
        }
        if (containsFlag) {
            return getErrorResult(request, e);
        }
        if (acceptHeader != null && containsFlag) {
            return getErrorResult(request, e);
        }
        boolean xmlRequestFlag = XML_REQUEST.equalsIgnoreCase(xRequestedWith);
        if (xmlRequestFlag) {
            return getErrorResult(request, e);
        }
        ModelAndView view = new ModelAndView("global_error");
        view.addObject("code", e.getResultCode().code());
        view.addObject("msg", e.getResultCode().msg());
        view.addObject("url", request.getRequestURL());
        view.addObject("stackTrace", e.getStackTrace());
        return view;
    }

    private Object getErrorResult(HttpServletRequest request, BusinessException e) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", e.getResultCode().code());
        result.put("msg", e.getMsg());
        result.put("url", request.getRequestURL());
        return ResultGenerator.failResult(e.getResultCode()).setData(result);
    }

    private Object getErrorResult(HttpServletRequest request, Exception e) {
        Map<String, Object> result = new HashMap<>(3);
        result.put("code", 500);
        result.put("msg", e.getMessage());
        result.put("url", request.getRequestURL());
        return ResultGenerator.failResult(e.getMessage()).setData(result);
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


    /**
     * 未授权错误处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    public Object handleShiroException(Exception ex) {
        return ResultGenerator.failResult(HttpStatus.UNAUTHORIZED);
    }

    /**
     * 授权失败处理
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(AuthorizationException.class)
    public Object AuthorizationException(Exception ex) {
        return ResultGenerator.failResult(ResultCode.AUTHORIZED_FAIL).setMessage(ex.getMessage());
    }
}
