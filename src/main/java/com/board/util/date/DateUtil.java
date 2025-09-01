package com.board.util.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 기준일을 기준으로 이번 주의 시작일(Sunday)과 마지막일(Saturday)을 구하고,
     * Day1 ~ Day7로 7일간의 날짜를 반환한다.
     *
     * @param base 기준일
     * @return 시작일, 마지막일, Day1 ~ Day7 날짜를 포함한 Map
     */
    public static Map<String, String> getThisWeek(Calendar base) {
        Calendar date = (Calendar) base.clone();
        Map<String, String> dateMap = new HashMap<>();

        // 이번 주 시작일(Sunday)
        date.add(Calendar.DATE, 1 - date.get(Calendar.DAY_OF_WEEK));
        addDateToMap(dateMap, date, "startDate", "Day1");

        // Day2 ~ Day6 날짜 추가
        for (int idx = 2; idx <= 6; idx++) {
            date.add(Calendar.DATE, 1);
            addDateToMap(dateMap, date, "Day" + idx);
        }

        // 이번 주 마지막일(Saturday)
        date.setTime(base.getTime());
        date.add(Calendar.DATE, 7 - date.get(Calendar.DAY_OF_WEEK));
        addDateToMap(dateMap, date, "endDate", "Day7");

        return dateMap;
    }

    /**
     * 기준일을 기준으로 해당 월의 주간 시작일과 종료일을 반환한다.
     *
     * @param base 기준일
     * @return 각 주의 시작일과 종료일을 포함한 Map
     */
    public static Map<String, String> getWeeksInMonth(Calendar base) {
        Map<String, String> dateMap = new HashMap<>();
        Calendar date = (Calendar) base.clone();

        final int YEAR = date.get(Calendar.YEAR);
        final int MONTH = date.get(Calendar.MONTH) + 1;
        final int MAX_DATE_OF_THIS_MONTH = date.getActualMaximum(Calendar.DAY_OF_MONTH);
        final String PREFIX = String.format("%d-%02d-", YEAR, MONTH);
        final int MAX_WEEK_OF_THIS_MONTH = date.getMaximum(Calendar.WEEK_OF_MONTH);

        for (int week = 1; week <= MAX_WEEK_OF_THIS_MONTH; week++) {
            // 해당 주의 첫 날(Sunday)과 마지막 날(Saturday) 계산
            date.set(Calendar.WEEK_OF_MONTH, week);
            date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
            int startDay = date.get(Calendar.DAY_OF_MONTH);

            date.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
            int endDay = date.get(Calendar.DAY_OF_MONTH);

            // 첫 주 일요일이 지난달인 경우
            if (week == 1 && startDay >= 7) {
                startDay = 1;
            }

            // 마지막 주 토요일이 다음 달인 경우
            if (week == MAX_WEEK_OF_THIS_MONTH && endDay <= 7) {
                endDay = MAX_DATE_OF_THIS_MONTH;
            }

            dateMap.put("W" + week + "_startDate", PREFIX + String.format("%02d", startDay));
            dateMap.put("W" + week + "_endDate", PREFIX + String.format("%02d", endDay));
        }

        return dateMap;
    }

    /**
     * 날짜를 Map에 추가하는 유틸리티 메서드
     *
     * @param dateMap 날짜를 저장할 Map
     * @param date    날짜
     * @param keys    추가할 키들
     */
    private static void addDateToMap(Map<String, String> dateMap, Calendar date, String... keys) {
        for (String key : keys) {
            dateMap.put(key, sdf.format(date.getTime()));
        }
    }
}
