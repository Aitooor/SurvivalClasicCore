package net.eternaln.survivalclasicbasis.utils;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public interface Utils {

    static void log(String... args){
        for(String str : args)
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + str));
    }

    static void logError(String... args){
        for(String str : args){
            Bukkit.getServer().getConsoleSender().sendMessage(ct(getPrefix() + "[ERROR] &c" + str));
        }
    }

    static @NotNull String ct(String source){
        return ChatColor.translateAlternateColorCodes('&', source);
    }

    static String send(CommandSender sender, String... args){
        for(String str : args)
            sender.sendMessage(ct(getPrefixGame() + str));
        return "";
    }

    static String getPrefixGame(){
        return  ct("&6&lETERNAL &r");
    }

    static String getPrefix(){
        SurvivalClasicBasis survivalClasicBasis = SurvivalClasicBasis.getPlugin(SurvivalClasicBasis.class);
        return  "[" + survivalClasicBasis.getName() + "] ";
    }

}
