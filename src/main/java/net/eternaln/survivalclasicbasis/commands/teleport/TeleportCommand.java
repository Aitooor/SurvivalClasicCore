package net.eternaln.survivalclasicbasis.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

@CommandAlias("tp|teleport|teletransportar")
@CommandPermission("survivalclasicbasis.tp")
public class TeleportCommand extends BaseCommand {

    private ArrayList<Player> teleportToggled = new ArrayList<Player>();
    private HashMap<Player, Player> requests = new HashMap();

    @Default @CatchUnknown
    public void teleport(Player sender, String target) {
        Location targetLocation = Bukkit.getPlayer(target).getLocation();

        if(target == null) {
            Utils.send(sender, "&cJugador no encontrado");
            return;
        }

        sender.teleport(targetLocation);
    }

    @Subcommand("all|todos")
    @CommandPermission("survivalclasicbasis.tpall")
    public void teleportAll(Player sender, String target) {
        if(!(target == null)) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                online.teleport(sender);
            }
            Utils.send(sender, "&aHas teletransportado a todos.");
        }
    }

    @Subcommand("pos|position|posicion")
    @CommandPermission("survivalclasicbasis.tppos")
    public void teleportPosition(Player sender, String x, String y, String z) {
        if (x == null || y == null || z == null) {
            Utils.send(sender,"&cEspecifica cordenadas");
            return;
        } else {
            try {
                Location location = new Location(sender.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                sender.teleport(location);
                Utils.send(sender,"&aHas sido teletransportado a las cordenadas:&6 " + x + "&a,&6 " + y + "&a,&6 " + z + "&a!");
            } catch (NumberFormatException e) {
                Utils.send(sender,"&cUsa numeros reales");
            }
        }
    }

    @Subcommand("toggle")
    @CommandPermission("survivalclasicbasis.tptoggle")
    public void teleportToggle(Player sender) {
        sender.sendMessage("works");
        for (Player player1 : requests.keySet())
            sender.sendMessage(player1.getName());
    }

    @Subcommand("request")
    @CommandPermission("survivalclasicbasis.tptoggle")
    public void teleportRequest(Player sender, String target) {
        Player player = (Player) sender;
        if (player.hasPermission("command.teleportrequest")) {
            if (target == null) {
                Utils.send(sender,"&cPlease specify the player!");
            } else {
                Player targetPlayer = Bukkit.getPlayer(target);
                if (target != null) {
                    Utils.send(sender,"&aYou sent teleport request to&6 " + targetPlayer.getName() + "&a!");
                    Utils.send(targetPlayer,"&aPlayer&6 " + player.getName() + " &awants to teleport to you!\n&aYou can confirm the request by &l/confirm&a,\n" +
                            "&aor you can deny it by &c&l/deny&a!");
                    requests.put(targetPlayer, player);
                } else {
                    Utils.send(sender,"&cThis player is not online!");
                }
            }
        }
    }
}
