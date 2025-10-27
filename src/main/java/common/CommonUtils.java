package common;

public class CommonUtils {
    public void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception ignored) {
        }
    }
}
