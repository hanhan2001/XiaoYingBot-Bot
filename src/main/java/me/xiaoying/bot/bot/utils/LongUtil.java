package me.xiaoying.bot.bot.utils;

/**
 * Util Long
 */
public class LongUtil {
    /**
     * 是否为 Long
     *
     * @param string String
     * @return Boolean
     */
    public static boolean isLong(String string) {
        try {
            Long.parseLong(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}