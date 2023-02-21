package me.tomisanhues.betterprefix;

import me.tomisanhues.betterprefix.commands.CommandHandler;
import me.tomisanhues.betterprefix.events.Events;
import me.tomisanhues.betterprefix.metrics.Metrics;
import me.tomisanhues.betterprefix.scoreboard.ScoreboardHandler;
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

    FileConfiguration config;
    ScoreboardHandler scoreboardHandler;
    Utils utils;

    private static LuckPerms api;

    private BukkitTask task;

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

        utils = new Utils(this);
        getServer().getPluginCommand("bp").setExecutor(new CommandHandler(this));
        getServer().getPluginManager().registerEvents(new Events(this), this);
        try {
            setConfigFile();
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
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

    private void setConfigFile() throws IOException, InvalidConfigurationException {
        config = getConfig();
        int configVersion = config.getKeys(false).contains("CONFIG_VERSION") ? config.getInt("CONFIG_VERSION") : -1;
        //Make a switch to check what version the config is currently at. If it is not the latest version, update to the next version
/*            switch (configVersion) {
                case 1:
                    break;
                default:
                    break;

            }*/
        //If the config version is not found, it is most likely the first time the plugin is being run. Set the config version to 1
    }

    public void getConfigFile() {
        Configuration.setChatEnabled(config.getBoolean("CHAT_ENABLED"));
        Configuration.setNametagEnabled(config.getBoolean("NAMETAG_ENABLED"));
        Configuration.setTabEnabled(config.getBoolean("TAB_ENABLED"));


        Configuration.setChatFormat(config.getString("CHAT_FORMAT"));
        Configuration.setTabFormat(config.getString("TAB_FORMAT"));
        Configuration.setNametagFormatPrefix(config.getString("NAMETAG_FORMAT_PREFIX"));
        Configuration.setNametagFormatSuffix(config.getString("NAMETAG_FORMAT_SUFFIX"));
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
