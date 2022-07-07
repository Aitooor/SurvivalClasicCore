package net.eternaln.survivalclasiccore;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import lombok.Getter;
import net.eternaln.survivalclasiccore.annotations.RegisterExecutor;
import net.eternaln.survivalclasiccore.data.Configuration;
import net.eternaln.survivalclasiccore.data.WarpsFile;
import net.eternaln.survivalclasiccore.managers.menus.MenuManager;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SurvivalClasicCore extends JavaPlugin {

    @Getter
    private static SurvivalClasicCore instance;
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

        new MenuManager(this);


        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.log("&aHooked to PlaceholderAPI.");
            Utils.log("");
        } else {
            Utils.logError("&cCould not find PlaceholderAPI! This plugin is required.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        commands();
        new RegisterExecutor();

        Utils.log("&aEnabled correctly.");

    }

    @Override
    public void onDisable() {
        Utils.log("&cDisabled correctly");
    }

    private void commands() {
        cmdManager = new PaperCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.getLocales().setDefaultLocale(Locales.SPANISH);
    }
}
