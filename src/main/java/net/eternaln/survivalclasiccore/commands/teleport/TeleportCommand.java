package net.eternaln.survivalclasiccore.commands.teleport;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

@CommandAlias("tp|teleport|teletransportar")
@CommandPermission("survivalclasic.tp")
public class TeleportCommand extends BaseCommand {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    private final HashMap<Player, Player> requests = new HashMap();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasic.tp")
    public void teleport(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if (!(targetPlayer == null)) {
            if (!(targetPlayer == sender)) {
                sender.teleport(targetPlayer.getLocation());
            } else {
                Utils.send(sender, messagesFile.tpSelf);
            }
        } else {
            Utils.send(sender, messagesFile.noOnlinePlayer.replace("%player%", target));
        }
    }

    @Subcommand("all|todos")
    @CommandPermission("survivalclasic.tpall")
    @CommandCompletion("@players")
    public void teleportAll(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if (targetPlayer == null || target == null) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online != sender) {
                    online.teleport(targetPlayer.getLocation());
                    Utils.send(online, messagesFile.tpAll.replace("%player%", targetPlayer.getName()));
                } else {
                    Utils.send(sender, messagesFile.tpAllSelf);
                }
            }
            Utils.send(sender, messagesFile.tpAllSender.replace("%player%", targetPlayer.getName()));
        }
    }

    @Subcommand("allhere|todosaqui|allh")
    @CommandPermission("survivalclasic.tpall")
    @CommandCompletion("@players")
    public void teleportAllHere(Player sender) {
        if (sender == null || sender == null) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if (online != sender) {
                    online.teleport(sender.getLocation());
                    Utils.send(online, messagesFile.tpAll.replace("%player%", sender.getName()));
                } else {
                    Utils.send(sender, messagesFile.tpAllSelf);
                }
            }
            Utils.send(sender, messagesFile.tpAllSender.replace("%player%", sender.getName()));
        }
    }

    @Subcommand("top")
    @CommandPermission("survivalclasic.top")
    public void teleportTop(Player sender) {
        Location currentLocation = sender.getLocation();
        Location newLocation = LocationUtil.teleportToHighestBlock(currentLocation);
        if (currentLocation.getY() < LocationUtil.highestBlock(currentLocation)) {
            sender.teleport(newLocation);
            Utils.send(sender, messagesFile.tpTop);
        } else {
            Utils.send(sender, messagesFile.tpTopFail);
        }
    }

    @Subcommand("pos|position|posicion")
    @CommandPermission("survivalclasic.tppos")
    public void teleportPosition(Player sender, String x, String y, String z) {
        if (!(x == null || y == null || z == null)) {
            try {
                Location location = new Location(sender.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                sender.teleport(location);
                Utils.send(sender, messagesFile.tpPos).replace("%x%", x).replace("%y%", y).replace("%z%", z);
            } catch (NumberFormatException e) {
                Utils.send(sender, messagesFile.tpPosRealNumber);
            }
        } else {
            Utils.send(sender, messagesFile.tpPosWriteNumber);
        }
    }

    @Subcommand("here|aqui")
    @CommandPermission("survivalclasic.here")
    @CommandCompletion("@players")
    public void teleportHere(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer != null) {
            if (targetPlayer != null) {
                if (!targetPlayer.getName().equals(targetPlayer.getName())) {
                    Utils.send(sender, messagesFile.tpSelf);
                } else {
                    targetPlayer.teleport(sender);
                    Utils.send(targetPlayer, messagesFile.tpHereSender.replace("%player%", sender.getName()));
                    Utils.send(sender, messagesFile.tpHereTarget.replace("%player%", targetPlayer.getName()));
                }
            } else {
                Utils.send(sender, messagesFile.noOnlinePlayer.replace("%player%", target));
            }
        } else {
            Utils.send(sender, messagesFile.noOnlinePlayer.replace("%player%", target));
        }
    }


    @Subcommand("toggle")
    public void teleportToggle(Player sender) {
        if (requests.containsKey(sender.getUniqueId())) {
            for (Player player1 : requests.keySet())
                Utils.send(player1, "&cHas desactivado el teletransporte");
            requests.remove(sender.getUniqueId());
        } else {
            requests.put(sender, sender);
            Utils.send(sender, "&aHas activado el teletransporte");
        }
    }
}
