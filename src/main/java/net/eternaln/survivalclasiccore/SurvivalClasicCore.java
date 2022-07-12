package net.eternaln.survivalclasiccore;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import net.eternaln.survivalclasiccore.annotations.RegisterExecutor;
import net.eternaln.survivalclasiccore.data.Configuration;
import net.eternaln.survivalclasiccore.data.WarpsFile;
import net.eternaln.survivalclasiccore.data.mongo.DataManager;
import net.eternaln.survivalclasiccore.data.mongo.MongoDB;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.managers.menus.MenuManager;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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
    @Getter
    private static MongoDB mongo;
    @Getter
    private static DataManager dataManager;

    @Override
    public void onEnable() {
        instance = this;
        configuration = new Configuration();
        warpsFile = new WarpsFile(this);
        mongo = new MongoDB();
        dataManager = new DataManager();

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

        cmdManager.getCommandCompletions().registerCompletion("warps", c -> {
            if (getWarpsFile().getConfig().getConfigurationSection("warps") == null)
                return ImmutableList.of();
            return new ImmutableList.Builder<String>().addAll(getWarpsFile().getConfig().getConfigurationSection("warps").getValues(true).keySet()).build();
        });

        cmdManager.getCommandCompletions().registerCompletion("homes", c -> {
            if (!(c.getSender() instanceof Player p))
                return ImmutableList.of();
            PlayerData playerData = dataManager.getData(p.getUniqueId());
            return new ImmutableList.Builder<String>().addAll(playerData.getHomes().keySet()).build();
        });

        cmdManager.getLocales().setDefaultLocale(Locales.SPANISH);
    }
}
