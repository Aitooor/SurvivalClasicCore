package net.eternaln.survivalclasicbasis;

import lombok.Getter;
import net.eternaln.survivalclasicbasis.commands.ItemCommand;
import net.eternaln.survivalclasicbasis.commands.MainCommand;
import net.eternaln.survivalclasicbasis.listeners.PlayerListeners;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalClasicBasis extends JavaPlugin {

    @Getter private static SurvivalClasicBasis instance;

    @Override
    public void onEnable() {
        instance = this;

        this.saveDefaultConfig();

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Bukkit.getPluginManager().registerEvents(new PlayerListeners(this), this);
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        this.getCommand("basis").setExecutor(new MainCommand(this));
        this.getCommand("itemc").setExecutor(new ItemCommand(this));

    }

    @Override
    public void onDisable() { Utils.log("&cDISABLED CORRECTLY"); }
}
