package com.board.interceptor;

import com.board.util.web.HttpSessionUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HttpSession session = request.getSession(false);

        if (session == null || HttpSessionUtil.getAttribute(session, "BOARD_USER") == null) {
            response.sendRedirect("/login");
            return false;
        }

        return true;
    }
}
