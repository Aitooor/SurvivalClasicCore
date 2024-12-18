package net.eternaln.survivalclasiccore;

import co.aikar.commands.Locales;
import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import lombok.Getter;
import net.eternaln.survivalclasiccore.managers.annotations.RegisterExecutor;
import net.eternaln.survivalclasiccore.data.configuration.*;
import net.eternaln.survivalclasiccore.data.mongo.DataManager;
import net.eternaln.survivalclasiccore.data.mongo.MongoDB;
import net.eternaln.survivalclasiccore.data.mongo.PlayerData;
import net.eternaln.survivalclasiccore.managers.menus.MenuManager;
import net.eternaln.survivalclasiccore.objects.freeze.FreezeHandler;
import net.eternaln.survivalclasiccore.objects.staff.StaffHandler;
import net.eternaln.survivalclasiccore.data.Placeholders;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class SurvivalClasicCore extends JavaPlugin {

    @Getter
    private static SurvivalClasicCore instance;
    @Getter
    private static PaperCommandManager cmdManager;
    @Getter
    private static Configuration configuration;
    @Getter
    private static MenusFile menusFile;
    @Getter
    private static MessagesFile messagesFile;
    @Getter
    private static WarpsFile warpsFile;

    @Getter
    private static MongoDB mongo;
    @Getter
    private static DataManager dataManager;
    private Placeholders placeholders;


    @Override
    public void onEnable() {
        instance = this;

        createMessageFolder();
        configuration = new Configuration();
        messagesFile = new MessagesFile();
        menusFile = new MenusFile();
        warpsFile = new WarpsFile(this);

        try {
            mongo = new MongoDB();
        } catch(Exception e) {
            Utils.log("MongoDB not connected");
        }
        dataManager = new DataManager();

        new MenuManager(this);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            Utils.log("&7Initializing placeholders...");
            placeholders = new Placeholders(this);
            placeholders.register();
            Utils.log("&aHooked to PlaceholderAPI.");
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
        placeholders.unregister();
        StaffHandler.disable();
        FreezeHandler.disable();
        Utils.log("&cDisabled correctly");
    }

    private void commands() {
        cmdManager = new PaperCommandManager(getInstance());
        cmdManager.enableUnstableAPI("help");

        cmdManager.getCommandCompletions().registerCompletion("warps", c -> {
            if (getWarpsFile().getConfig().getConfigurationSection("warps") == null)
                return ImmutableList.of();
            return new ImmutableList.Builder<String>().addAll(Objects.requireNonNull(getWarpsFile().getConfig().getConfigurationSection("warps")).getValues(true).keySet()).build();
        });

        List<String> materialNames = Arrays.stream(Material.values())
                .map(Material::name)
                .collect(Collectors.toList());
        cmdManager.getCommandCompletions().registerCompletion("items", c -> {
            if (materialNames == null)
                return ImmutableList.of();
            return new ImmutableList.Builder<String>().addAll(materialNames).build();
        });

        List<String> enchantmetsNames = Arrays.stream(Enchantment.values())
                .map(Enchantment::getName)
                .collect(Collectors.toList());
        cmdManager.getCommandCompletions().registerCompletion("enchantments", c -> {
            if (enchantmetsNames == null)
                return ImmutableList.of();
            return new ImmutableList.Builder<String>().addAll(enchantmetsNames).build();
        });

        cmdManager.getCommandCompletions().registerCompletion("homes", c -> {
            if (!(c.getSender() instanceof Player p))
                return ImmutableList.of();
            PlayerData playerData = dataManager.getData(p.getUniqueId());
            return new ImmutableList.Builder<String>().addAll(playerData.getHomes().keySet()).build();
        });

        cmdManager.getLocales().setDefaultLocale(Locales.SPANISH);
    }

    public void createMessageFolder() {
        File messagesFolder = new File(instance.getDataFolder(), "messages");
        if(!messagesFolder.exists()) {
            messagesFolder.mkdirs();
        }
    }
}
