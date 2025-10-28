package utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {

    // 1. Kiểm tra chuỗi rỗng hoặc null
    public static boolean isNullOrEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    // 2. Viết hoa chữ cái đầu tiên
    public static String capitalizeFirst(String input) {
        if (isNullOrEmpty(input)) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    // 3. Chuyển thành chữ thường toàn bộ
    public static String toLower(String input) {
        return isNullOrEmpty(input) ? input : input.toLowerCase();
    }

    // 4. Chuyển thành chữ hoa toàn bộ
    public static String toUpper(String input) {
        return isNullOrEmpty(input) ? input : input.toUpperCase();
    }

    // 5. Xóa ký tự đặc biệt, chỉ giữ chữ và số
    public static String removeSpecialChars(String input) {
        return isNullOrEmpty(input) ? input : input.replaceAll("[^a-zA-Z0-9\\s]", "");
    }

    // 6. Loại bỏ dấu tiếng Việt
    public static String removeVietnameseAccents(String input) {
        if (isNullOrEmpty(input)) return input;
        String temp = Normalizer.normalize(input, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("đ", "d").replaceAll("Đ", "D");
    }

    // 7. Viết thường + bỏ dấu (so sánh keyword)
    public static String normalizeKeyword(String input) {
        if (isNullOrEmpty(input)) return input;
        return removeVietnameseAccents(input).toLowerCase().trim();
    }

    // 8. Rút gọn khoảng trắng dư thừa
    public static String trimAllSpaces(String input) {
        if (isNullOrEmpty(input)) return input;
        return input.trim().replaceAll("\\s+", " ");
    }

    // 9. Cắt chuỗi vượt quá độ dài
    public static String truncate(String input, int maxLength) {
        if (isNullOrEmpty(input) || input.length() <= maxLength) return input;
        return input.substring(0, maxLength);
    }

    // 10. Kiểm tra chuỗi có chứa số không
    public static boolean containsNumber(String input) {
        if (isNullOrEmpty(input)) return false;
        return input.matches(".*\\d.*");
    }
}
