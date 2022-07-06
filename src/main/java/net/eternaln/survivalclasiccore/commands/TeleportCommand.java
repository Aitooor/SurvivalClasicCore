package net.eternaln.survivalclasiccore.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.LocationUtil;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@CommandAlias("tp|teleport|teletransportar")
public class TeleportCommand extends BaseCommand {

    private ArrayList<Player> teleportToggled = new ArrayList<Player>();
    private HashMap<Player, Player> requests = new HashMap();

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default @CatchUnknown
    @CommandPermission("survivalclasiccore.tp")
    public void teleport(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if(!(targetPlayer == null)) {
            if(!(targetPlayer == sender)) {
                sender.teleport(targetPlayer.getLocation());
            } else {
                Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
            }
        } else{
            Utils.send(sender, "&cJugador no encontrado");
        }
    }

    @Subcommand("all|todos")
    @CommandPermission("survivalclasiccore.tpall")
    @CommandCompletion("@players")
    public void teleportAll(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if(targetPlayer == null || target == null) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if(online != sender) {
                    online.teleport(targetPlayer.getLocation());
                    Utils.send(online, "&fHas sido teletransportado hacia &b" + targetPlayer.getName());
                } else {
                    Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
                }
            }
            Utils.send(sender, "&fHas teletransportado a todos hacia &b" + targetPlayer.getName());
        }
    }

    @Subcommand("allhere|todosaqui|allh")
    @CommandPermission("survivalclasiccore.tpall")
    @CommandCompletion("@players")
    public void teleportAllHere(Player sender) {
        if(sender == null || sender == null) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                if(online != sender) {
                    online.teleport(sender.getLocation());
                    Utils.send(online, "&fHas sido teletransportado hacia &b" + sender.getName());
                } else {
                    Utils.send(sender, "&cNo puedes teletransportarte a ti mismo");
                }
            }
            Utils.send(sender, "&fHas teletransportado a todos hacia &b" + sender.getName());
        }
    }

    @Subcommand("top")
    @CommandPermission("survivalclasiccore.top")
    public void teleportTop(Player sender) {
        Location currentLocation = sender.getLocation();
        Location newLocation = LocationUtil.teleportToHighestBlock(currentLocation);
        if(currentLocation.getY() > newLocation.getY() + 1) {
            sender.teleport(newLocation);
            Utils.send(sender, "&aHas sido teletransportado hacia el top");
        } else {
            Utils.send(sender, "&cNo hay ningun bloque mas alto");
        }
    }

    @Subcommand("pos|position|posicion")
    @CommandPermission("survivalclasiccore.tppos")
    public void teleportPosition(Player sender, String x, String y, String z) {
        if (!(x == null || y == null || z == null)) {
            try {
                Location location = new Location(sender.getWorld(), Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(z));
                sender.teleport(location);
                Utils.send(sender,"&fHas sido teletransportado a las cordenadas &a" + x + "&a " + y + "&a " + z);
            } catch (NumberFormatException e) {
                Utils.send(sender,"&cUsa numeros reales, no letras");
            }
        } else {
            Utils.send(sender,"&cEspecifica las cordenadas &7x y z");
        }
    }

    @Subcommand("here|aqui")
    @CommandPermission("survivalclasiccore.here")
    @CommandCompletion("@players")
    public void teleportHere(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer != null) {
            if (targetPlayer != null) {
                if (!targetPlayer.getName().equals(targetPlayer.getName())) {
                    Utils.send(sender,"&cNo puedes teletransportarte hacia ti mismo");
                } else {
                    targetPlayer.teleport(sender);
                    Utils.send(sender,"&fHas teletranspotado hacia ti a &b" + targetPlayer.getName());
                    Utils.send(targetPlayer,sender.getName() + " &fte ha teletransportado hacia el");
                }
            } else {
                Utils.send(sender,"&cEste jugador no esta online");
            }
        } else {
            Utils.send(sender,"&cEste jugador no esta online");
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

    @Subcommand("request|solicitar|pedir")
    @CommandCompletion("@players")
    public void teleportRequest(Player sender, String target) {
        if (!sender.hasPermission("survivalclasiccore.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            sender.sendMessage(Utils.ct("&cDebes esperar &b" + cooldown.getSecondsRemaining(sender.getUniqueId()) + " &csegundos"));
            return;
        }
        Player targetPlayer = Bukkit.getPlayer(target);
        if (targetPlayer != null) {
            if(!target.equals(sender.getName())) {
                Utils.send(sender, "&fHas enviado solicitud a &b" + targetPlayer.getName());
                Utils.send(targetPlayer, "&fEl jugador &b" + sender.getName() + " &fQuiere teletrasportarse a ti\n&aPuedes aceptar usando &l/tp accept|confirm|aceptar|confirmar\n" +
                        "&cO rechazarlo usando &l/tp deny|rechazar");
                requests.put(targetPlayer, sender);
            } else {
                Utils.send(sender,"&cNo puedes teletransportarte a ti mismo");
            }
        } else {
            Utils.send(sender,"&cEste jugador no esta online");
        }
    }

    @Subcommand("accept|confirm|aceptar|confirmar")
    public void teleportAccept(Player sender) {
        if (requests.containsKey(sender)) {
            Utils.send(sender,"&fHas &a&lACEPTADO &fla petición de &b" + requests.get(sender).getPlayer().getDisplayName());
            requests.get(sender).sendMessage(Utils.ct("&fEl jugador &b" + sender.getName() + " &fha &a&lACEPTADO &ftu petición"));
            sender.teleport(requests.get(sender).getLocation());
        } else {
            Utils.send(sender,"&cNo tienes ninguna peticiones de teletransporte");
        }
    }

    @Subcommand("deny|rechazar")
    public void teleportDeny(Player sender) {
        if (requests.containsKey(sender)) {
            Utils.send(sender,"&fHas &c&lDENEGADO &ala petición de &b" + requests.get(sender).getPlayer().getDisplayName());
            requests.get(sender).sendMessage(Utils.ct("&fEl jugador &b" + sender.getName() + " &fha &c&lDENEGADO &ftu petición"));
            requests.remove(sender);
        } else {
            Utils.send(sender,"&cNo tienes ninguna peticiones de teletransporte");
        }
    }
}
