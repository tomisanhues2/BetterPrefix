package me.tomisanhues.betterprefix.commands;

import me.tomisanhues.betterprefix.BetterPrefix;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.List;

public class CommandHandler implements TabExecutor {

    BetterPrefix betterprefix;


    public CommandHandler(BetterPrefix betterprefix) {
        this.betterprefix = betterprefix;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("/bp reload");
            return true;
        }
        if (args.length == 1 && "reload".equals(args[0])) {
            betterprefix.getConfigFile();
            betterprefix.restartTask();
            sender.sendMessage("The config has been reloaded.");
            return true;
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            return List.of("reload");
        }

        return null;
    }
}
