package me.tomisanhues.betterprefix.tasks;

import me.tomisanhues.betterprefix.BetterPrefix;
import me.tomisanhues.betterprefix.utils.Configuration;
import me.tomisanhues.betterprefix.utils.NametagChanger;
import me.tomisanhues.betterprefix.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class PrefixTask extends BukkitRunnable {

    BetterPrefix betterprefix;

    public PrefixTask(BetterPrefix betterprefix) {
        this.betterprefix = betterprefix;
    }

    @Override
    public void run() {
        if (Bukkit.getOnlinePlayers().size() == 0) {
            return;
        }

        for (Player player: Bukkit.getOnlinePlayers()) {

            if (!Configuration.getChatEnabled()) {
                player.setDisplayName(Utils.setPlaceholders(player, Configuration.getChatFormat()));
            }

            if (Configuration.getTabEnabled()) {
                player.setPlayerListName(Utils.setPlaceholders(player, Configuration.getTabFormat()));
            }

            if (Configuration.getNametagEnabled()) {
                NametagChanger.changePlayerName(player, Utils.setPlaceholders(player, Configuration.getNametagFormatPrefix()), Utils.setPlaceholders(player, Configuration.getNametagFormatSuffix()), NametagChanger.TeamAction.UPDATE);
            }
        }
    }
}
