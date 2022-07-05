package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SocialSpyCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;

    public SocialSpyCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration config = plugin.getConfig();
        
        if (!(sender instanceof Player)) {
            Utils.log("&cDebes ser un jugador para hacer eso");
            return true;
        }

        Player player = (Player) sender;

        if (player.hasPermission("survivalclasicbasis.socialspy")) {
            if (args.length == 0) {
                Utils.send(player, "&cUso: /socialspy <on/off>");
                return true;
            }
            /*if (args.length == 1 && args[0].equalsIgnoreCase("on")) {
                if (!plugin.socialSpyToggle) {
                    plugin.socialSpyToggle = true;
                    Utils.send(player, "&aSocial spy activado");
                } else {
                    Utils.send(player, "&cSocial spy ya esta activado");
                }
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("off")) {
                if (!plugin.socialSpyToggle) {
                    plugin.socialSpyToggle = false;
                    Utils.send(player, "&cSocial spy desactivado");
                } else {
                    Utils.send(player, "&cSocial spy ya esta desactivado");
                }
                return true;
            }*/

        }
        return true;
    }

}
