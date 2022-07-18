package net.eternaln.survivalclasiccore.commands.admin.gamemode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import jdk.jshell.execution.Util;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm|modo")
@CommandPermission("survivalclasiccore.gm")
public class GamemodeCommand extends BaseCommand {

    MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();

    @Default
    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandSender sender, CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandPermission("survivalclasiccore.gm.survival")
    public void setSurvival(Player sender) {
        if (sender.getGameMode() != GameMode.SURVIVAL) {
            sender.setGameMode(GameMode.SURVIVAL);
            Utils.send(sender, messageFile.setSurvival);
        } else {
            Utils.send(sender, messageFile.alreadySurvival);
        }
    }

    @Subcommand("creative|c|creacion|1")
    @CommandPermission("survivalclasiccore.gm.creative")
    public void setCreative(Player sender) {
        if (sender.getGameMode() != GameMode.CREATIVE) {
            sender.setGameMode(GameMode.CREATIVE);
            Utils.send(sender, messageFile.setCreative);
        } else {
            Utils.send(sender, messageFile.alreadyCreative);
        }
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandPermission("survivalclasiccore.gm.adventure")
    public void setAdventure(Player sender) {
        if (sender.getGameMode() != GameMode.ADVENTURE) {
            sender.setGameMode(GameMode.ADVENTURE);
            Utils.send(sender, messageFile.setAdventure);
        } else {
            Utils.send(sender, messageFile.alreadyAdventure);
        }
    }

    @Subcommand("spectator|spec|spectator|3")
    @CommandPermission("survivalclasiccore.gm.spectator")
    public void setSpectator(Player sender) {
        if (sender.getGameMode() != GameMode.SPECTATOR) {
            sender.setGameMode(GameMode.SPECTATOR);
            Utils.send(sender, messageFile.setSpectator);
        } else {
            Utils.send(sender, messageFile.alreadySpectator);
        }
    }

}
