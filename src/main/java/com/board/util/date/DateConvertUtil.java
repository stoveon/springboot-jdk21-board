package com.board.util.date;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 날짜와 시간 관련 형 변환을 지원하는 유틸리티 클래스.
 * 문자열 → LocalDate / LocalDateTime 변환 시 사용하며, 실패 시 기본값 반환.
 */
@Slf4j
public class DateConvertUtil {

    // --- 문자열 → 날짜 변환 ---

    /**
     * 문자열을 LocalDate로 변환 (기본 포맷: yyyy-MM-dd)
     * 변환 실패 시 null 반환
     */
    public static LocalDate toLocalDate(Object value) {
        return toLocalDate(value, DateTimeFormatter.ofPattern("yyyy-MM-dd"), null);
    }

    /**
     * 문자열을 LocalDate로 변환 (포맷 지정 가능)
     * 변환 실패 시 기본값 반환
     */
    public static LocalDate toLocalDate(Object value, DateTimeFormatter formatter, LocalDate defaultValue) {
        try {
            if (value instanceof LocalDate) return (LocalDate) value;
            if (value != null) {
                return LocalDate.parse(value.toString().trim(), formatter);
            }
        } catch (Exception ignored) {
        }
        return defaultValue;
    }

    /**
     * 문자열을 LocalDateTime으로 변환 (기본 포맷: yyyy-MM-dd HH:mm:ss)
     * 변환 실패 시 null 반환
     */
    public static LocalDateTime toLocalDateTime(Object value) {
        return toLocalDateTime(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"), null);
    }

    /**
     * 문자열을 LocalDateTime으로 변환 (포맷 지정 가능)
     * 변환 실패 시 기본값 반환
     */
    public static LocalDateTime toLocalDateTime(Object value, DateTimeFormatter formatter, LocalDateTime defaultValue) {
        try {
            if (value instanceof LocalDateTime) return (LocalDateTime) value;
            if (value != null) {
                return LocalDateTime.parse(value.toString().trim(), formatter);
            }
        } catch (Exception ignored) {
        }
        return defaultValue;
    }

    // --- 날짜 → 문자열 포맷팅 ---

    /**
     * LocalDate → 문자열 변환 (기본 포맷: yyyy-MM-dd)
     */
    public static String format(LocalDate date) {
        return format(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * LocalDate → 문자열 변환 (사용자 지정 포맷)
     */
    public static String format(LocalDate date, DateTimeFormatter formatter) {
        return date != null ? date.format(formatter) : "";
    }

    /**
     * LocalDateTime → 문자열 변환 (기본 포맷: yyyy-MM-dd HH:mm:ss)
     */
    public static String format(LocalDateTime datetime) {
        return format(datetime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * LocalDateTime → 문자열 변환 (사용자 지정 포맷)
     */
    public static String format(LocalDateTime datetime, DateTimeFormatter formatter) {
        return datetime != null ? datetime.format(formatter) : "";
    }

    /**
     * java.util.Date → 문자열 변환 (기본 포맷: yyyy-MM-dd HH:mm:ss)
     */
    public static String format(Date date) {
        return format(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * java.util.Date → 문자열 변환 (사용자 지정 포맷)
     */
    public static String format(Date date, String pattern) {
        if (date == null) return "";
        try {
            return new SimpleDateFormat(pattern).format(date);
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * ISO 8601 형식의 날짜 문자열을 KST(한국 표준시)로 변환하여 java.util.Date 객체로 반환
     *
     * @param isoDateStr ISO 8601 형식의 문자열 (예: "2025-05-14T16:24:17+09:00")
     * @return java.util.Date 객체
     */
    public static Date parseIso8601ToDate(String isoDateStr) {
        ZonedDateTime zonedDateTime = OffsetDateTime.parse(isoDateStr).atZoneSameInstant(ZoneId.of("Asia/Seoul"));
        return Date.from(zonedDateTime.toInstant());
    }
}
