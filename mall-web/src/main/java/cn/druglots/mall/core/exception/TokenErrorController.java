package cn.druglots.mall.core.exception;

import com.google.common.base.Strings;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @BelongsProject: cloud-mall
 * @BelongsPackage: cn.druglots.mall.core.exception
 * @Author: King-Pan(pwpw1218@gmail.com)
 * @CreateTime: 2019-09-05 23:51
 * @Description:
 */
@Controller
public class TokenErrorController extends BasicErrorController {
    public TokenErrorController(){
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    private static final String PATH = "/error";

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);
        if (!Strings.isNullOrEmpty((String)body.get("exception"))){
            body.put("status", HttpStatus.FORBIDDEN.value());
            status = HttpStatus.FORBIDDEN;
        }
        return new ResponseEntity<Map<String, Object>>(body, status);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
