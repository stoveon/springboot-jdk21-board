package com.board.util.web;

import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 *     HttpSession 관련 공통 유틸
 *     - 모든 키/값을 파라미터로 처리
 *     - 타입 안전, 로그 확인 가능
 * </pre>
 */
@Slf4j
@UtilityClass
public class HttpSessionUtil {

    /**
     * 세션에 값 저장
     *
     * @param session HttpSession
     * @param key     세션 키
     * @param value   저장할 값
     */
    public static void setAttribute(HttpSession session, String key, Object value) {
        if (session != null && key != null) {
            session.setAttribute(key, value);
            log.debug("[SAVE] SESSION: {}={}", key, value);
        }
    }

    /**
     * 세션에서 값 조회
     *
     * @param session HttpSession
     * @param key     세션 키
     * @param <T>     반환 타입
     * @return 값이 없으면 null
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAttribute(HttpSession session, String key) {
        if (session == null || key == null) return null;
        return (T) session.getAttribute(key);
    }

    /**
     * 세션에서 특정 값 제거
     *
     * @param session HttpSession
     * @param key     세션 키
     */
    public static void removeAttribute(HttpSession session, String key) {
        if (session != null && key != null) {
            session.removeAttribute(key);
            log.debug("[REMOVE] SESSION: {}", key);
        }
    }

    /**
     * 세션 전체 무효화
     *
     * @param session HttpSession
     */
    public static void invalidate(HttpSession session) {
        if (session != null) {
            session.invalidate();
            log.debug("[INVALIDATE] SESSION");
        }
    }

    /**
     * 세션에 특정 키가 존재하는지 확인
     *
     * @param session HttpSession
     * @param key     세션 키
     * @return 존재하면 true
     */
    public static boolean hasAttribute(HttpSession session, String key) {
        if (session == null || key == null) return false;
        return session.getAttribute(key) != null;
    }
}
