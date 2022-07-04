package net.eternaln.survivalclasicbasis.utils;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface Utils {

    FileConfiguration config = SurvivalClasicBasis.getInstance().getConfig();

    static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + str));
    }

    static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + "[ERROR] &c" + str));
        }
    }

    static String ct(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    public static List<String> formatList(List<String> s) {
        return s.stream().map(Utils::ct).collect(Collectors.toList());
    }

    static String send(CommandSender sender, String... args){
        for(String str : args)
            sender.sendMessage(ct(getPrefixGame() + str));
        return "";
    }
    static String sendNoPrefix(CommandSender sender, String... args){
        for(String str : args)
            sender.sendMessage(ct(str));
        return "";
    }

    static String getPrefixGame(){
        return  ct(config.getString("prefix"));
    }

    static String getPrefix(){
        SurvivalClasicBasis survivalClasicBasis = SurvivalClasicBasis.getPlugin(SurvivalClasicBasis.class);
        return  "[" + survivalClasicBasis.getName() + "] ";
    }

}
