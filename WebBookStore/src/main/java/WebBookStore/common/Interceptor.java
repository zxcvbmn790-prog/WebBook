package WebBookStore.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class Interceptor implements HandlerInterceptor {

    // 컨트롤러 실행 전
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 예시: 로그인 세션 체크
        Object user = request.getSession().getAttribute("loginUser");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login"); // 로그인 페이지로 이동
            return false; // 컨트롤러 실행 차단
        }
        return true; // 컨트롤러 실행 허용
    }

    // 컨트롤러 실행 후, 뷰 렌더링 전
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 예시: 요청 처리 후 로깅
        System.out.println("Request URL: " + request.getRequestURL());
    }

    // 뷰 렌더링 후
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 예시: 요청 완료 후 로깅
        System.out.println("Request 완료: " + request.getRequestURL());
        if (ex != null) {
            ex.printStackTrace();
        }
    }
}