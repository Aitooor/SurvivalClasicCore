package net.eternaln.survivalclasicbasis.utils;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public interface Utils {

    static void log(String... args) {
        for (String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + str));
    }

    static void logError(String... args) {
        for (String str : args) {
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + "[ERROR] &c" + str));
        }
    }

    static String ct(String source) {
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    static List<String> formatList(List<String> s) {
        return s.stream().map(Utils::ct).collect(Collectors.toList());
    }

    static String send(CommandSender sender, String... args) {
        for (String str : args)
            sender.sendMessage(ct(getPrefixGame() + str));
        return "";
    }

    static String sendNoPrefix(CommandSender sender, String... args) {
        for (String str : args)
            sender.sendMessage(ct(str));
        return "";
    }

    static String getPrefixGame() {
        return ct(SurvivalClasicBasis.getConfiguration().getPrefix());
    }

    static String getPrefix() {
        SurvivalClasicBasis survivalClasicBasis = SurvivalClasicBasis.getPlugin(SurvivalClasicBasis.class);
        return "[" + survivalClasicBasis.getName() + "] ";
    }

}
