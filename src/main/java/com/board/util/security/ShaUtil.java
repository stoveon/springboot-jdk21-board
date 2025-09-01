package com.board.util.security;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA 해시 유틸 (순수 SHA-256)
 */
@Slf4j
public class ShaUtil {

    private static final String SHA256 = "SHA-256";

    private ShaUtil() {}

    public static String sha256(String input) {
        return sha256(input, null);
    }

    public static String sha256(String input, String salt) {
        if (input == null) return "";

        if (salt == null) salt = "";

        try {
            MessageDigest digest = MessageDigest.getInstance(SHA256);
            digest.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            log.error("SHA-256 not available: {}", e.getMessage());
            return "";
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
