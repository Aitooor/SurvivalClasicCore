package net.eternaln.survivalclasicbasis;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.eternaln.survivalclasicbasis.data.Configuration;
import net.eternaln.survivalclasicbasis.listeners.PlayerListeners;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class SurvivalClasicBasis extends JavaPlugin {

    @Getter
    private static SurvivalClasicBasis instance;

    @Getter
    private static PaperCommandManager cmdManager;

    @Getter
    private static Configuration configuration;

    private File warpsConfigFile;
    private FileConfiguration warpsConfig;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new Configuration();
        configuration.loadAndSave();
        createWarpsConfig();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new PlayerListeners(this), this);
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        commands();

        Utils.log("&aENABLED CORRECTLY");

    }

    @Override
    public void onDisable() { Utils.log("&cDISABLED CORRECTLY"); }

    private void commands() {
        cmdManager = new PaperCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.getLocales().setDefaultLocale(Locales.SPANISH);
    }

    public FileConfiguration getWarpsConfig() {
        return this.warpsConfig;
    }

    private void createWarpsConfig() {
        warpsConfigFile = new File(getDataFolder(), "warps.yml");
        if (!warpsConfigFile.exists()) {
            warpsConfigFile.getParentFile().mkdirs();
            saveResource("warps.yml", false);
        }

        warpsConfig = new YamlConfiguration();
        try {
            warpsConfig.load(warpsConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
