package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;

    public MessageCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        /*FileConfiguration config = plugin.getConfig();
        
        if (!(sender instanceof Player)) {
            Utils.log("&cDebes ser un jugador para hacer eso");
            return true;
        }

        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("msg")) {
            if (player.hasPermission("simple.message")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /msg target message");
                    return true;
                }
                if (args.length == 1) {
                    player.sendMessage(ChatColor.RED + "Usage: /msg target message");
                    return true;
                }
                Player target = Bukkit.getServer().getPlayerExact(args[0]);
                if (target != null) {
                    plugin.reply.put(player, target);
                    plugin.reply.put(target, player);
                    String message = "";
                    for (int i = 1; i != args.length; i++)
                        message += args[i] + " ";
                    player.sendMessage(ChatColor.YELLOW + "To " + target.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                            + " >> " + ChatColor.GRAY + message);
                    target.sendMessage(ChatColor.YELLOW + "From " + player.getDisplayName() + ChatColor.YELLOW
                            + ChatColor.BOLD + " >> " + ChatColor.GRAY + message);
                    target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                    return true;
                } else {
                    player.sendMessage(ChatColor.RED + "That player is not online!");
                    return true;
                }
            } else {
                player.sendMessage(ChatColor.RED + "No permission!");
                return true;
            }
        }
        if (command.getName().equalsIgnoreCase("reply")) {
            if (player.hasPermission("simple.message")) {
                if (args.length == 0) {
                    player.sendMessage(ChatColor.RED + "Usage: /r message");
                    return true;
                }
                if (!plugin.reply.containsKey(player)) {
                    player.sendMessage(ChatColor.RED + "Nobody messaged you!");
                    return true;
                }
                Player target1 = (Player) plugin.reply.get(sender);
                if (target1 == null) {
                    player.sendMessage(ChatColor.RED + "The last player you messaged is offline.");
                    plugin.reply.remove(target1);
                    return true;
                }
                String message = "";
                for (int i = 0; i != args.length; i++)
                    message += args[i] + " ";
                player.sendMessage(ChatColor.YELLOW + "To " + plugin.reply.get(target1.getDisplayName())
                        + ChatColor.YELLOW + ChatColor.BOLD + " >> " + ChatColor.GRAY + message);
                target1.sendMessage(ChatColor.YELLOW + "From " + player.getDisplayName() + ChatColor.YELLOW + ChatColor.BOLD
                        + " >> " + ChatColor.GRAY + message);
                target1.playSound(target1.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                return true;
            } else {
                player.sendMessage(ChatColor.RED + "No permission!");
                return true;
            }
        }*/
        return true;
    }

}
