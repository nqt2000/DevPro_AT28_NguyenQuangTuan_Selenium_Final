package utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

    public class DateTimeUtils {

    // 1. Lấy thời gian hiện tại theo định dạng mặc định (yyyy-MM-dd HH:mm:ss)
    public static String getCurrentDateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    // 2. Lấy thời gian hiện tại theo định dạng tùy chọn
    public static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    // 3. Lấy ngày hiện tại (yyyy-MM-dd)
    public static String getCurrentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    // 4. Lấy giờ hiện tại (HH:mm:ss)
    public static String getCurrentTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    // 5. Chuyển chuỗi thời gian sang LocalDateTime theo pattern
    public static LocalDateTime parseDateTime(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetime, formatter);
    }

    // 6. Cộng/trừ ngày theo offset (positive = cộng, negative = trừ)
    public static String addDays(String datetime, String pattern, long days) {
        LocalDateTime dateTime = parseDateTime(datetime, pattern);
        return dateTime.plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    // 7. Cộng/trừ giờ theo offset
    public static String addHours(String datetime, String pattern, long hours) {
        LocalDateTime dateTime = parseDateTime(datetime, pattern);
        return dateTime.plusHours(hours).format(DateTimeFormatter.ofPattern(pattern));
    }

    // 8. Lấy timestamp hiện tại (epoch millis)
    public static long getCurrentTimestamp() {
        return Instant.now().toEpochMilli();
    }

    // 9. Chuyển epoch millis sang chuỗi ngày giờ theo pattern
    public static String fromTimestamp(long millis, String pattern) {
        LocalDateTime dateTime = Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    // 10. Kiểm tra chuỗi có đúng định dạng datetime không
    public static boolean isValidDateTime(String datetime, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime.parse(datetime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
