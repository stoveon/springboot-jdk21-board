package com.board.util.web;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Optional;

/**
 * <pre>
 *     Cookie 관련 유틸리티
 *     - HttpOnly 기본 적용
 *     - Secure 기본 true (localhost/127.* 환경에서만 false)
 *     - SameSite=Lax 지원
 * </pre>
 */
@Slf4j
public class CookieUtil {

    private static final String SAME_SITE_ATTRIBUTE = "SameSite=Lax";

    /**
     * 기본 설정으로 쿠키 추가 (HttpOnly: true, Secure: 자동판단, SameSite=Lax, 세션 유지)
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieValue) {
        addCookie(request, response, cookieName, cookieValue, -1, true, true);
    }

    /**
     * 지정된 만료 시간으로 쿠키 추가
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieValue, int maxAge) {
        addCookie(request, response, cookieName, cookieValue, maxAge, true, true);
    }

    /**
     * 커스텀 설정으로 쿠키 추가
     */
    public static void addCookie(HttpServletRequest request, HttpServletResponse response,
                                 String cookieName, String cookieValue,
                                 int maxAge, boolean isHttpOnly, boolean isSecure) {
        Cookie cookie = buildCookie(request, cookieName, cookieValue, maxAge, isHttpOnly, isSecure);
        response.addCookie(cookie);

        // SameSite 속성 수동 추가
        response.addHeader("Set-Cookie",
                String.format("%s=%s; Path=%s; Max-Age=%d; %s%s%s",
                        cookie.getName(),
                        cookie.getValue(),
                        cookie.getPath(),
                        cookie.getMaxAge(),
                        cookie.isHttpOnly() ? "HttpOnly; " : "",
                        cookie.getSecure() ? "Secure; " : "",
                        SAME_SITE_ATTRIBUTE));
    }

    /**
     * 쿠키 조회
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        if (request.getCookies() == null) {
            return null;
        }
        Optional<Cookie> cookieOpt = Arrays.stream(request.getCookies())
                .filter(c -> c.getName().equals(cookieName))
                .findFirst();
        return cookieOpt.map(Cookie::getValue).orElse(null);
    }

    /**
     * 쿠키 삭제
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName) {
        Cookie cookie = buildCookie(request, cookieName, "", 0, true, true);
        response.addCookie(cookie);
        log.debug("[REMOVE] Cookie: {}", cookieName);
    }

    /**
     * 공통 Cookie 생성 로직
     */
    private static Cookie buildCookie(HttpServletRequest request, String name, String value,
                                      int maxAge, boolean isHttpOnly, boolean isSecure) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(isHttpOnly);

        // 로컬 환경에서는 Secure 자동 해제
        boolean isLocal = request.getServerName().equals("localhost")
                || request.getServerName().startsWith("127.");
        cookie.setSecure(!isLocal && isSecure);

        cookie.setMaxAge(maxAge);
        return cookie;
    }
}
