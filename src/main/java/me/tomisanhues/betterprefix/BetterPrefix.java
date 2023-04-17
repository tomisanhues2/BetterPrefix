package me.tomisanhues.betterprefix;

import me.tomisanhues.betterprefix.commands.CommandHandler;
import me.tomisanhues.betterprefix.config.Config;
import me.tomisanhues.betterprefix.events.Events;
import me.tomisanhues.betterprefix.metrics.Metrics;
import me.tomisanhues.betterprefix.utils.Configuration;
import me.tomisanhues.betterprefix.tasks.PrefixTask;
import me.tomisanhues.betterprefix.utils.UpdateChecker;
import me.tomisanhues.betterprefix.utils.Utils;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

public final class BetterPrefix extends JavaPlugin {

    Utils utils;

    private static LuckPerms api;

    private BukkitTask task;

    public Config config;

    @Override
    public void onEnable() {
        int pluginId = 17122;
        Metrics metrics = new Metrics(this, pluginId);
        new UpdateChecker(this).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                getLogger().info("There is not a new update available.");
            } else {
                getLogger().severe("There is a new update available. Old versions are NOT supported!");
                getLogger().severe(String.format("Current version: %s - New version: %s", this.getDescription().getVersion(), version));
            }
        });
        config = new Config(this);

        utils = new Utils(this);
        getServer().getPluginCommand("bp").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);
        getConfigFile();

        Bukkit.getScheduler().runTask(this, () -> {
            luckPerms();
            getLogger().info("was enabled successfully!");
        });
        task = new PrefixTask(this).runTaskTimer(this, 20L, 20L);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void luckPerms() {
        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            api = provider.getProvider();

        }
    }

    public static LuckPerms getApi() {
        return api;
    }

    public void getConfigFile() {
        Configuration.setChatEnabled(config.getConfig().getBoolean("CHAT_ENABLED"));
        Configuration.setNametagEnabled(config.getConfig().getBoolean("NAMETAG_ENABLED"));
        Configuration.setTabEnabled(config.getConfig().getBoolean("TAB_ENABLED"));


        Configuration.setChatFormat(config.getConfig().getString("CHAT_FORMAT"));
        Configuration.setTabFormat(config.getConfig().getString("TAB_FORMAT"));
        Configuration.setNametagFormatPrefix(config.getConfig().getString("NAMETAG_FORMAT_PREFIX"));
        Configuration.setNametagFormatSuffix(config.getConfig().getString("NAMETAG_FORMAT_SUFFIX"));
    }

    public BukkitTask getTask() {
        return task;
    }

    public void setTask(BukkitTask task) {
        this.task = task;
    }

    public void restartTask() {
        task.cancel();
        task = new PrefixTask(this).runTaskTimer(this, 20L, 20L);
    }
}
