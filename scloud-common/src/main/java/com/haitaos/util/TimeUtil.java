package com.haitaos.util;


import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TimeUtil {

    /**
     * default data format
     */
    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter
            .ofPattern(DEFAULT_PATTERN)
            .withLocale(java.util.Locale.CHINA);

    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.systemDefault();

    /**
     *  LocalDateTime to String, specify date format
     * @param localDateTime
     * @param pattern
     * @return String
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(localDateTime.atZone(DEFAULT_ZONE_ID));
    }

    /**
     *  LocalDateTime to String, default date format
     * @param localDateTime
     * @return String
     */

    public static String format(LocalDateTime localDateTime) {
        return DEFAULT_DATE_TIME_FORMATTER.format(localDateTime.atZone(DEFAULT_ZONE_ID));
    }

    /**
     * Date to String, specify date format
     * @param time
     * @param pattern
     * @return String
     */
    public static String format(Date time, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(time.toInstant().atZone(DEFAULT_ZONE_ID));
    }

    /**
     * Date to String, default date format
     * @param time
     * @return String
     */

    public static String format(Date time) {
        return DEFAULT_DATE_TIME_FORMATTER.format(time.toInstant().atZone(DEFAULT_ZONE_ID));
    }

    /**
     * timestamp to String, specify date format
     * @param timestamp
     * @param pattern
     * @return String
     */
    public static String format(long timestamp, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return formatter.format(new Date(timestamp).toInstant().atZone(DEFAULT_ZONE_ID));
    }

    /**
     * timestamp to String, default date format
     * @param timestamp
     * @return String
     */
    public static String format(long timestamp) {
        return DEFAULT_DATE_TIME_FORMATTER.format(new Date(timestamp).toInstant().atZone(DEFAULT_ZONE_ID));
    }

    /**
     * String to Date
     * @param time
     * @return Date
     */
    public static Date parse(String time) {
        return Date.from(LocalDateTime.parse(time, DEFAULT_DATE_TIME_FORMATTER).atZone(DEFAULT_ZONE_ID).toInstant());
    }

    /**
     * get the number of seconds left in the day for traffic package expiration configuration
     * @param currentDate
     * @return Integer
     */
    public static Integer getRemainSecondsOneDay(Date currentDate) {
        LocalDateTime midnight = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault())
                .plusDays(1)
                .withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);

        LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentDate.toInstant(),
                ZoneId.systemDefault());
        long seconds = ChronoUnit.SECONDS.between(currentDateTime, midnight);
        return (int) seconds;
    }


}
