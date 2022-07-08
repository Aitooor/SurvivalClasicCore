package net.eternaln.survivalclasiccore.commands.admin.gamemode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm|modo")
@CommandPermission("survivalclasiccore.gm")
public class GamemodeCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandPermission("survivalclasiccore.gm.survival")
    public void setSurvival(Player sender) {
        if(sender.getGameMode() != GameMode.SURVIVAL) {
            sender.setGameMode(GameMode.SURVIVAL);
            Utils.send(sender, "&fHas cambiado tu modo de juego a &eSurvival");
        } else {
            Utils.send(sender, "&fYa tienes el modo de juego &eSurvival");
        }
    }

    @Subcommand("creative|c|creacion|1")
    @CommandPermission("survivalclasiccore.gm.creative")
    public void setCreative(Player sender) {
        if(sender.getGameMode() != GameMode.CREATIVE) {
            sender.setGameMode(GameMode.CREATIVE);
            Utils.send(sender, "&fHas cambiado tu modo de juego a &eCreative");
        } else {
            Utils.send(sender, "&fYa tienes el modo de juego &eCreative");
        }
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandPermission("survivalclasiccore.gm.adventure")
    public void setAdventure(Player sender) {
        if(sender.getGameMode() != GameMode.ADVENTURE) {
            sender.setGameMode(GameMode.ADVENTURE);
            Utils.send(sender, "&fHas cambiado tu modo de juego a &eAdventure");
        } else {
            Utils.send(sender, "&fYa tienes el modo de juego &eAdventure");
        }
    }

    @Subcommand("spectator|spec|spectator|3")
    @CommandPermission("survivalclasiccore.gm.spectator")
    public void setSpectator(Player sender) {
        if(sender.getGameMode() != GameMode.SPECTATOR) {
            sender.setGameMode(GameMode.SPECTATOR);
            Utils.send(sender, "&fHas cambiado tu modo de juego a &eEspectador");
        } else {
            Utils.send(sender, "&fYa tienes el modo de juego &eEspectador");
        }
    }

}
