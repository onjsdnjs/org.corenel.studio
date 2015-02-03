package org.corenel.core.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.corenel.core.annotation.AsyncInvoke;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

/**

 * @author 정수원
 */
public class HTTPHandler{

	public static String sendErrorToClient(HttpServletResponse response, Throwable e, ModelMap model) {
		System.out.println("Payment sendErrorToClient : "+ e.toString());
		if(model != null) model.clear();
		String cause = null;
		try {
			
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setCharacterEncoding("EUC-KR");
			if(e.getCause() != null){
				cause = e.getCause().toString();
				int causeIndex = cause.indexOf("Cause");
				if(causeIndex != -1){
					cause = cause.substring(causeIndex);
					int rfIndex = cause.indexOf("\n");
					cause = cause.substring(0,rfIndex);
				}
			}else{
				cause = e.toString();
			}
			response.setHeader("errorCode", String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
			response.setHeader("errorStatus", "Server Internal Error");
			response.setHeader("errorCause", cause);
			response.getWriter().write(cause);
		} catch (IOException e1) {
		}
		return cause;
	}
	
	public static void setReponseHeader(HttpServletResponse response, File file, String fileName) {
		response.reset();
		response.setHeader("Content-Type", "application/vnd.ms-excel; charset=euc-kr");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName + ".xlsx");
		
		response.setHeader ("Content-Length", ""+file.length());
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "0;");
	}
	
	/**
	 * Controller 에서 @AsyncInvoke 가 선언된 메소드가 호출 되었을 경우 비동기 호출이라고 판단함.
	 * 비동기 호출로 통신하고자 하면 Controller 의 해당 메소드에 @AsyncInvoke 를 선언해야 비동기 예외 처리를 하고 그렇지 않으면 동기 예외를 발생시킨다. 
	 */
	public static boolean isAsync(Exception ex){
		StackTraceElement[] ste = ex.getStackTrace();
		Method[] methods;
		String methodName;
		Class<?> clazz;
		try{
			for(StackTraceElement st : ste){
				clazz = Class.forName(st.getClassName());
				Object obj = clazz.getAnnotation(Controller.class);
				if(obj == null){
					continue;
				}
				methods = clazz.getMethods();
				methodName = st.getMethodName();
				for(Method method: methods){
					if(StringUtils.equals(method.getName(), methodName) ){
						Object rb = method.getAnnotation(AsyncInvoke.class);
						return rb != null;
					}
				}
			}
		}catch(SecurityException se){
		}catch(ClassNotFoundException ce){
		}
		return false;
	}
	
}
