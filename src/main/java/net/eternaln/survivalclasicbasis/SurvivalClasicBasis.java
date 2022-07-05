package net.eternaln.survivalclasicbasis;

import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.Locales;
import co.aikar.commands.MessageType;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.eternaln.survivalclasicbasis.commands.*;
import net.eternaln.survivalclasicbasis.data.Configuration;
import net.eternaln.survivalclasicbasis.listeners.PlayerListeners;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class SurvivalClasicBasis extends JavaPlugin {

    @Getter
    private static SurvivalClasicBasis instance;

    @Getter
    private static PaperCommandManager cmdManager;

    @Getter
    private static Configuration configuration;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new Configuration();
        configuration.loadAndSave();

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
}
