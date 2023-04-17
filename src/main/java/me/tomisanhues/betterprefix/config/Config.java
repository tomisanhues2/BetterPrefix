package me.tomisanhues.betterprefix.config;

import me.tomisanhues.betterprefix.BetterPrefix;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Config {

    private final BetterPrefix plugin;

    private File file;
    private FileConfiguration config;

    public Config(BetterPrefix plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) plugin.saveResource("config.yml", false);
        config = new YamlConfiguration();
        try {
            config.load(file);
            plugin.getLogger().info("Loaded config.yml");
        } catch (Exception e) {
            plugin.getLogger().severe("Could not load config.yml");
            plugin.getLogger().severe(e.getMessage());
        }
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void reload() {
        try {
            config.load(file);
            plugin.getLogger().info("Reloaded config.yml");
        } catch (Exception e) {
            plugin.getLogger().severe("Could not reload config.yml");
            plugin.getLogger().severe(e.getMessage());
        }
    }

}
