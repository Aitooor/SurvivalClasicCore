package net.eternaln.survivalclasiccore.commands.admin.gamemode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

@CommandAlias("gamemodeto|gmto|modoa|gmother|gamemodeother")
@CommandPermission("survivalclasiccore.gm.other")
public class GamemodeOtherCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandCompletion("@players")
    public void setSurvival(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.SURVIVAL) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.SURVIVAL);
                    Utils.send(sender, "&fHas cambiado el modo de juego de &b" + targetPlayer.getName() + " &fa &eSurvival");
                    Utils.send(targetPlayer, "&fTu modo de juego ha sido cambiado por &b" + sender.getName() + " &fa &eSurvival");
                } else {
                    Utils.send(sender, "&fNo puedes cambiar tu propio modo de juego");
                }
            } else {
                Utils.send(sender, "&fEl jugador &b" + targetPlayer.getName() + " &fya tiene el modo de juego &eSurvival");
            }
        } else {
            Utils.send(sender, "&fEl jugador &b" + target + " &fno esta online");
        }
    }

    @Subcommand("creative|c|creacion|1")
    @CommandCompletion("@players")
    public void setCreative(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.CREATIVE) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.CREATIVE);
                    Utils.send(sender, "&fHas cambiado el modo de juego de &b" + targetPlayer.getName() + " &fa &eCreative");
                    Utils.send(targetPlayer, "&fTu modo de juego ha sido cambiado por &b" + sender.getName() + " &fa &eCreative");
                } else {
                    Utils.send(sender, "&fNo puedes cambiar tu propio modo de juego");
                }
            } else {
                Utils.send(sender, "&fEl jugador &b" + targetPlayer.getName() + " &fya tiene el modo de juego &eCreative");
            }
        } else {
            Utils.send(sender, "&fEl jugador &b" + target + " &fno esta online");
        }
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandCompletion("@players")
    public void setAdventure(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.ADVENTURE) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.ADVENTURE);
                    Utils.send(sender, "&fHas cambiado el modo de juego de &b" + targetPlayer.getName() + " &fa &eAdventure");
                    Utils.send(targetPlayer, "&fTu modo de juego ha sido cambiado por &b" + sender.getName() + " &fa &eAdventure");
                } else {
                    Utils.send(sender, "&fNo puedes cambiar tu propio modo de juego");
                }
            } else {
                Utils.send(sender, "&fEl jugador &b" + targetPlayer.getName() + " &fya tiene el modo de juego &eAdventure");
            }
        } else {
            Utils.send(sender, "&fEl jugador &b" + target + " &fno esta online");
        }
    }

    @Subcommand("spectator|spec|spectator|3")
    @CommandCompletion("@players")
    public void setSpectator(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        if (!(targetPlayer == null || !targetPlayer.isOnline())) {
            if (targetPlayer.getGameMode() != GameMode.SPECTATOR) {
                if (targetPlayer != sender) {
                    targetPlayer.setGameMode(GameMode.SPECTATOR);
                    Utils.send(sender, "&fHas cambiado el modo de juego de &b" + targetPlayer.getName() + " &fa &eEspectador");
                    Utils.send(targetPlayer, "&fTu modo de juego ha sido cambiado por &b" + sender.getName() + " &fa &eEspectador");
                } else {
                    Utils.send(sender, "&fNo puedes cambiar tu propio modo de juego");
                }
            } else {
                Utils.send(sender, "&fEl jugador &b" + targetPlayer.getName() + " &fya tiene el modo de juego &eEspectador");
            }
        } else {
            Utils.send(sender, "&fEl jugador &b" + target + " &fno esta online");
        }
    }

}
