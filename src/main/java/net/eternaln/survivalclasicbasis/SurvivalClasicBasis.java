package net.eternaln.survivalclasicbasis;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.eternaln.survivalclasicbasis.annotations.RegisterExecutor;
import net.eternaln.survivalclasicbasis.data.Configuration;
import net.eternaln.survivalclasicbasis.data.WarpsFile;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalClasicBasis extends JavaPlugin {

    @Getter
    private static SurvivalClasicBasis instance;
    @Getter
    private static PaperCommandManager cmdManager;
    @Getter
    private static Configuration configuration;
    @Getter
    private static WarpsFile warpsFile;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new Configuration();
        warpsFile = new WarpsFile(this);


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("Could not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        commands();
        new RegisterExecutor();

        Utils.log("&aENABLED CORRECTLY");

    }

    @Override
    public void onDisable() {
        Utils.log("&cDISABLED CORRECTLY");
    }

    private void commands() {
        cmdManager = new PaperCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.getLocales().setDefaultLocale(Locales.SPANISH);
    }
}
