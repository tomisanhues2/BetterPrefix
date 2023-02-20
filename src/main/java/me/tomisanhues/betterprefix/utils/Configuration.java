package me.tomisanhues.betterprefix.utils;


public class Configuration {

    private static Boolean CHAT_ENABLED;
    private static Boolean TAB_ENABLED;
    private static Boolean NAMETAG_ENABLED;

    private static String CHAT_FORMAT;
    private static String TAB_FORMAT;
    private static String NAMETAG_FORMAT_PREFIX;
    private static String NAMETAG_FORMAT_SUFFIX;


    public Configuration() {

    }

    public static Boolean getChatEnabled() {
        return CHAT_ENABLED;
    }

    public static void setChatEnabled(Boolean chatEnabled) {
        CHAT_ENABLED = chatEnabled;
    }

    public static Boolean getTabEnabled() {
        return TAB_ENABLED;
    }

    public static void setTabEnabled(Boolean tabEnabled) {
        TAB_ENABLED = tabEnabled;
    }

    public static Boolean getNametagEnabled() {
        return NAMETAG_ENABLED;
    }

    public static void setNametagEnabled(Boolean nametagEnabled) {
        NAMETAG_ENABLED = nametagEnabled;
    }

    public static String getChatFormat() {
        return CHAT_FORMAT;
    }

    public static void setChatFormat(String chatFormat) {
        CHAT_FORMAT = chatFormat;
    }

    public static String getTabFormat() {
        return TAB_FORMAT;
    }

    public static void setTabFormat(String tabFormat) {
        TAB_FORMAT = tabFormat;
    }

    public static String getNametagFormatPrefix() {
        return NAMETAG_FORMAT_PREFIX;
    }

    public static void setNametagFormatPrefix(String nametagFormatPrefix) {
        NAMETAG_FORMAT_PREFIX = nametagFormatPrefix;
    }

    public static String getNametagFormatSuffix() {
        return NAMETAG_FORMAT_SUFFIX;
    }

    public static void setNametagFormatSuffix(String nametagFormatSuffix) {
        NAMETAG_FORMAT_SUFFIX = nametagFormatSuffix;
    }
}
