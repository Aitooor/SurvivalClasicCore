package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("gamemode|gm|modo")
@CommandPermission("survivalclasiccore.gm")
public class GamemodeCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @HelpCommand
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandPermission("survivalclasiccore.gm.survival")
    public void setSurvival(CommandSender sender) {
        Player player = (Player) sender;
        player.setGameMode(GameMode.SURVIVAL);
    }

    @Subcommand("creative|c|creativo|1")
    @CommandPermission("survivalclasiccore.gm.creative")
    public void setCretive(CommandSender sender) {
        Player player = (Player) sender;
        player.setGameMode(GameMode.CREATIVE);
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandPermission("survivalclasiccore.gm.adventure")
    public void setAdventure(CommandSender sender) {
        Player player = (Player) sender;
        player.setGameMode(GameMode.ADVENTURE);
    }

    @Subcommand("spectator|spec|espectador|3")
    @CommandPermission("survivalclasiccore.gm.spectator")
    public void setSpectacto(CommandSender sender) {
        Player player = (Player) sender;
        player.setGameMode(GameMode.SPECTATOR);
    }
}
