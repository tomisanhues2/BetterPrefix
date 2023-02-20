package me.tomisanhues.betterprefix.utils;

import me.clip.placeholderapi.PlaceholderAPI;
import me.tomisanhues.betterprefix.BetterPrefix;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static BetterPrefix betterprefix;
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");

    public Utils(BetterPrefix betterprefix) {
        Utils.betterprefix = betterprefix;
    }

    public static String translateHexColorCodes(final String message) {
        final char colorChar = '§';
        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 32);
        while (matcher.find()) {
            final String group = matcher.group(1);
            matcher.appendReplacement(buffer, "§x§" + group.charAt(0) + '§' + group.charAt(1) + '§' + group.charAt(2) + '§' + group.charAt(3) + '§' + group.charAt(4) + '§' + group.charAt(5));
        }
        return matcher.appendTail(buffer).toString();
    }

    public static String colorize(final String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String setPlaceholders(Player player, String format) {
        CachedMetaData metaData = BetterPrefix.getApi().getPlayerAdapter(Player.class).getMetaData(player);
        format = format.replace("{PREFIX}", metaData.getPrefix())
                .replace("{NAME}", player.getName())
                .replace("{SUFFIX}", metaData.getSuffix());

        return setPlaceholderAPIPlaceholders(player, format);
    }

    public static String setPlaceholders(Player player, String format, String message) {
        CachedMetaData metaData = BetterPrefix.getApi().getPlayerAdapter(Player.class).getMetaData(player);
        format = format.replace("{PREFIX}", metaData.getPrefix())
                .replace("{NAME}", player.getName())
                .replace("{SUFFIX}", metaData.getSuffix())
                .replace("{MESSAGE}", message);

        return setPlaceholderAPIPlaceholders(player, format);
    }


    public static String setPlaceholderAPIPlaceholders(Player player, String message) {
        if (betterprefix.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            message = PlaceholderAPI.setPlaceholders(player, message);
        }
        message = translateHexColorCodes(message);
        message = colorize(message);
        return message;
    }
}
