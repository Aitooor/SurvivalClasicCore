package net.eternaln.survivalclasicbasis.utils;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

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

    static String send(CommandSender sender, String... args){
        for(String str : args)
            sender.sendMessage(ct(getPrefixGame() + str));
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
