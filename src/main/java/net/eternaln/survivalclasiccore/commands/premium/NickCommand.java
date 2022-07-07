package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("nick|apodo|nombre")
public class NickCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasiccore.nick")
    public void nick(CommandSender sender, String string) {
        Player player = (Player) sender;

        if(Utils.checkNameLength(string)) {
            player.setDisplayName(string);
            player.setPlayerListName(Utils.ct(string));
            Utils.send(sender, "&fTu nombre ha sido cambiado a " + string);
        } else {
            Utils.send(sender, "&cNo puedes usar un nombre con mas de 16 caracteres");
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasiccore.nick.other")
    @CommandCompletion("@players")
    public void other(CommandSender sender, String target, String string) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if(Utils.checkNameLength(string)) {
            if (!(targetPlayer == null)) {
                targetPlayer.setDisplayName(string);
                targetPlayer.setPlayerListName(Utils.ct(string));
                Utils.send(sender, "&fEl nombre de &b" + targetPlayer.getName() + " &fha sido cambiado a " + string);
                Utils.send(targetPlayer, "&fTu nombre ha sido cambiado a " + string + " &fpor &b" + sender.getName());
            } else {
                Utils.send(sender, "&cJugador no encontrado");
            }
        } else {
            Utils.send(sender, "&cNo puedes usar un nombre con mas de 16 caracteres");
        }
    }
}
