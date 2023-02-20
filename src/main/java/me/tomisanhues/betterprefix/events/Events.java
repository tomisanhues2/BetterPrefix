package me.tomisanhues.betterprefix.events;

import me.tomisanhues.betterprefix.BetterPrefix;
import me.tomisanhues.betterprefix.utils.Configuration;
import me.tomisanhues.betterprefix.utils.NametagChanger;
import me.tomisanhues.betterprefix.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    public BetterPrefix betterprefix;

    public Events(BetterPrefix betterprefix) {
        this.betterprefix = betterprefix;
    }


    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (Configuration.getNametagEnabled()) {
            Player player = event.getPlayer();
            NametagChanger.changePlayerName(player, "", "", NametagChanger.TeamAction.DESTROY);
        }
    }

    @EventHandler
    public void aSyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (Configuration.getChatEnabled()) {
            Player player = event.getPlayer();
            event.setMessage(Utils.colorize(Utils.translateHexColorCodes(event.getMessage())));
            String format = Utils.setPlaceholders(player, Configuration.getChatFormat(), event.getMessage());
            event.setFormat(format);
        }
    }
}
