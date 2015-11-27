package com.zz.springbootweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/error")
public class SimpleErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    @Autowired
    public SimpleErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request) {
        Map<String, Object> model = getErrorAttributes(request, getTraceParameter(request));
        return new ModelAndView("error/500", model);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        if (parameter == null) {
            return false;
        }
        return !"false".equals(parameter.toLowerCase());
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
        RequestAttributes requestAttributes = new ServletRequestAttributes(aRequest);
        return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
