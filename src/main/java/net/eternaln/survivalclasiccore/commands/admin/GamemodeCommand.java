package net.eternaln.survivalclasiccore.commands.admin;

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
    @HelpCommand
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Subcommand("survival|s|supervivencia|0")
    @CommandPermission("survivalclasiccore.gm.survival")
    public void setSurvival(Player sender) {
        Player player = (Player) sender;
        sender.setGameMode(GameMode.SURVIVAL);
        Utils.send(sender, "&aHas cambiado tu modo de juego a &bSurvival");
    }

    @Subcommand("creative|c|creativo|1")
    @CommandPermission("survivalclasiccore.gm.creative")
    public void setCretive(Player sender) {
        Player player = (Player) sender;
        sender.setGameMode(GameMode.CREATIVE);
        Utils.send(sender, "&aHas cambiado tu modo de juego a &bCreative");
    }

    @Subcommand("adventure|a|aventura|2")
    @CommandPermission("survivalclasiccore.gm.adventure")
    public void setAdventure(Player sender) {
        Player player = (Player) sender;
        sender.setGameMode(GameMode.ADVENTURE);
        Utils.send(sender, "&aHas cambiado tu modo de juego a &bAdventure");
    }

    @Subcommand("spectator|spec|espectador|3")
    @CommandPermission("survivalclasiccore.gm.spectator")
    public void setSpectacto(Player sender) {
        Player player = (Player) sender;
        sender.setGameMode(GameMode.SPECTATOR);
        Utils.send(sender, "&aHas cambiado tu modo de juego a &bSpectator");
    }

    @CommandAlias("other|otro|otros|others")
    @CommandPermission("survivalclasiccore.gm.other")
    public class OthersGamemodeCommand extends BaseCommand {
        @Subcommand("survival|s|supervivencia|0")
        public void setSurvival(Player sender, Player target) {
            target.setGameMode(GameMode.SURVIVAL);
            Utils.send(sender, "&fHas cambiado el modo de juego de &b" + target.getName() + " &f a &bSurvival");
            Utils.send(target, "&aHas cambiado el modo de juego de &b" + target.getName() + " &a a &bSurvival");
        }

        @Subcommand("creative|c|creativo|1")
        public void setCretive(Player sender, Player target) {
            target.setGameMode(GameMode.CREATIVE);
            Utils.send(sender, "&fHas cambiado el modo de juego de &b" + target.getName() + " &f a &bCreative");
            Utils.send(target, "&aHas cambiado el modo de juego de &b" + target.getName() + " &a a &bCreative");
        }

        @Subcommand("adventure|a|aventura|2")
        public void setAdventure(Player sender, Player target) {
            target.setGameMode(GameMode.ADVENTURE);
            Utils.send(sender, "&fHas cambiado el modo de juego de &b" + target.getName() + " &f a &bAdventure");
            Utils.send(target, "&aHas cambiado el modo de juego de &b" + target.getName() + " &a a &bAdventure");
        }

        @Subcommand("spectator|spec|espectador|3")
        public void setSpectacto(Player sender, Player target) {
            target.setGameMode(GameMode.SPECTATOR);
            Utils.send(sender, "&fHas cambiado el modo de juego de &b" + target.getName() + " &f a &bSpectator");
            Utils.send(target, "&aHas cambiado el modo de juego de &b" + target.getName() + " &a a &bSpectator");
        }
    }
}
