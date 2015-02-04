package org.corenel.core.aop;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.corenel.core.common.ServiceExceptionResolver;
import org.corenel.core.util.HTTPHandler;

/**

 * @author ������
 * @description Application �� ��� ���ܸ� �����ϴ� Ŭ�����μ� ����/�񵿱� ����� ���ܸ� ���� ó���Ѵ�.
 */
@ControllerAdvice
public class ExceptionControllerAdvice {
 
    @ExceptionHandler(Throwable.class)
    public @ResponseBody ModelAndView exception(HttpServletResponse response, Exception e) {
    	ModelAndView mav = new ModelAndView();
    	String errorMessage = HTTPHandler.sendErrorToClient(response, e, null);
    	boolean isAsync = HTTPHandler.isAsync(e);

    	if(isAsync){
    		mav.setViewName("jsonView");
    		if(e instanceof ServiceExceptionResolver){
    			if(((ServiceExceptionResolver)e).getMap() != null){
    				mav.addObject("result", ((ServiceExceptionResolver)e).getMap());
    				return mav;
    			}
    		}
    		return mav;
    		
    	}else{
    		mav.setViewName("cmmn/error/error");
    		mav.addObject("errorMessage", errorMessage);
    		return mav;
    	}
	}
}
