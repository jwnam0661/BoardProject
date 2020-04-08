package com.project.ny.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.ny.annotation.NoAuth;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 呼び出し先コントローラに @NoAuth がついているか判定（クラスまたはメソッドのいづれか）
        HandlerMethod hm = (HandlerMethod) handler;
        if (hm.getBeanType().getAnnotation(NoAuth.class) != null
                || hm.getMethodAnnotation(NoAuth.class) != null) {
            // @NoAuthが付いているため、ログインしてるかどうかはチェックしない。ここで処理終了。
            return true;
        }

		return super.preHandle(request, response, handler);
	}
}
