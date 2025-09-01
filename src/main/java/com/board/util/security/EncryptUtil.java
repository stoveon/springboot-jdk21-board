package com.board.util.security;

import lombok.extern.slf4j.Slf4j;

import java.security.NoSuchAlgorithmException;

/**
 * 암호화 유틸 (SHA-256 + Salt)
 */
@Slf4j
public class EncryptUtil {

    public static final String DEFAULT_SALT = "CHANGE_THIS_DEFAULT_SALT";

    private EncryptUtil() {}

    public static String encryptSHA256(String input) throws NoSuchAlgorithmException {
        return encryptSHA256(input, DEFAULT_SALT);
    }

    public static String encryptSHA256(String input, String salt) throws NoSuchAlgorithmException {
        if (input == null) throw new IllegalArgumentException("Input cannot be null");
        return ShaUtil.sha256(input, salt);
    }
}
