/*
 * package com.kh.spring.util.interceptor;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
 * 
 * public class LoginInterceptor extends HandlerInterceptorAdapter{
 * 
 * 
 * Intercepter
 * 
 * RequestHandler가 호출되기 전 또는 수행된 이후에 실행할 내용을 작성할 수 있음
 * 
 * preHandler (전처리) : 핸들러 수행되기 전 낚아챔 postHandler (후처리) : 수행된 이후 낚아챔
 * 
 * 
 * 
 * private void preHandler(HttpServletRequest request,HttpServletRequest
 * response, Object handler ) throws Exception {
 * 
 * HttpSession session = request.getSession();
 * 
 * if(session.getAttribute("lognMember") != null) {
 * 
 * }else { session.setAttribute("message, "권한이 없어요"); response
 * 
 * }
 * 
 * 
 * 
 * }
 * 
 * }
 */