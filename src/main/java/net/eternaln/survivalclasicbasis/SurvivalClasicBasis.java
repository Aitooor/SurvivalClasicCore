package net.eternaln.survivalclasicbasis;

import net.eternaln.survivalclasicbasis.commands.*;
import net.eternaln.survivalclasicbasis.listeners.PlayerListeners;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class SurvivalClasicBasis extends JavaPlugin {

    private static SurvivalClasicBasis instance;
    public static SurvivalClasicBasis getInstance() {
        return instance;
    }

    public Map<Player, Player> reply = new HashMap<Player, Player>();
    public boolean socialSpyToggle = false;

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
        this.getCommand("spawn").setExecutor(new SpawnCommand(this));
        this.getCommand("msg").setExecutor(new MessageCommand(this));
        this.getCommand("reply").setExecutor(new MessageCommand(this));
        this.getCommand("socialspy").setExecutor(new SocialSpyCommand(this));

        Utils.log("&aENABLED CORRECTLY");

    }

    @Override
    public void onDisable() { Utils.log("&cDISABLED CORRECTLY"); }
}
