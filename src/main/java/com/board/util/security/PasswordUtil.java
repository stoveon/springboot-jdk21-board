package com.board.util.security;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * 비밀번호 검증 및 암호화 유틸
 */
@Slf4j
public class PasswordUtil {

    private PasswordUtil() {}

    // ==========================
    // 비밀번호 검증
    // ==========================
    public static boolean isValidPassword(String password, String confirmPassword) {
        if (StringUtils.isBlank(password)) {
            log.warn("Password is empty");
            return false;
        }
        if (StringUtils.isNotBlank(confirmPassword) && !password.equals(confirmPassword)) {
            log.warn("Password and confirmPassword do not match");
            return false;
        }
        return true;
    }

    // ==========================
    // 비밀번호 암호화
    // ==========================
    public static String encrypt(String password) {
        return encrypt(password, null, EncryptUtil.DEFAULT_SALT);
    }

    public static String encrypt(String password, String confirmPassword) {
        return encrypt(password, confirmPassword, EncryptUtil.DEFAULT_SALT);
    }

    public static String encrypt(String password, String confirmPassword, String salt) {
        if (!isValidPassword(password, confirmPassword)) return null;
        try {
            return EncryptUtil.encryptSHA256(password, salt);
        } catch (Exception e) {
            log.error("Password encryption failed: {}", e.getMessage());
            return null;
        }
    }
}
