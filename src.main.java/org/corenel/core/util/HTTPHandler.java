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

 * @author ������
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
	 * Controller ���� @AsyncInvoke �� ����� �޼ҵ尡 ȣ�� �Ǿ��� ��� �񵿱� ȣ���̶�� �Ǵ���.
	 * �񵿱� ȣ��� ����ϰ��� �ϸ� Controller �� �ش� �޼ҵ忡 @AsyncInvoke �� �����ؾ� �񵿱� ���� ó���� �ϰ� �׷��� ������ ���� ���ܸ� �߻���Ų��. 
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
