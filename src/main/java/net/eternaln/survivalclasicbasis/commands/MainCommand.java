package net.eternaln.survivalclasicbasis.commands;

import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.LocationUtil;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    private final SurvivalClasicBasis plugin;

    public MainCommand(SurvivalClasicBasis instance) {
        plugin = instance;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) return false;
            if (args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                Utils.log("&aConfig recargada");
                return true;
            }
            if(args[0].equalsIgnoreCase("setspawn")) {
                Utils.log("Necesitas ser un jugador para hacer eso.");
                return true;
            }
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("survivalclasicbasis.reload")) {
            Utils.send(player, "&cNo puedes hacer eso.");
            return true;
        }

        if (args.length == 0) return false;

        if (args[0].equalsIgnoreCase("reload")) {
            Utils.send(player, "&cConfig recargada.");
            plugin.reloadConfig();
            return true;
        }

        if (!player.hasPermission("survivalclasicbasis.setspawn")) {
            return true;
        }

        if(args[0].equalsIgnoreCase("setspawn")) {
            plugin.getInstance().getConfig().set("LOCATION.SPAWN", LocationUtil.parseToString(player.getLocation()));
            plugin.getInstance().saveConfig();
            plugin.getInstance().reloadConfig();

            player.sendMessage(Utils.ct(Utils.getPrefixGame() + "&aEl spawn ha sido establecido."));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            return true;
        }

        return false;
    }

}
