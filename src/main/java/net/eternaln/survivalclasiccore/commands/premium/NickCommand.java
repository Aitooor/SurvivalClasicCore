package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

@CommandAlias("nick|apodo|nombre")
public class NickCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.nick")
    public void god(CommandSender sender, String string) {
        Player player = (Player) sender;
        String shortString = string.substring(0, Math.min(string.length(), 16));

        if(shortString.length() < 16) {
            player.setDisplayName(Utils.ct(shortString));
            player.setPlayerListName(Utils.ct(shortString));
            Utils.send(sender, "&fTu nombre ha sido cambiado a " + Utils.ct(shortString));
        } else {
            Utils.send(sender, "&cEl nombre debe tener más de 16 caracteres");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.nick.other")
    public void other(CommandSender sender, String target, String string) {
        Player targetPlayer = Bukkit.getPlayer(target);
        String shortString = string.substring(0, Math.min(string.length(), 16));

        if(shortString.length() < 16) {
            if (!(targetPlayer == null)) {
                targetPlayer.setDisplayName(Utils.ct(shortString));
                targetPlayer.setPlayerListName(Utils.ct(shortString));
                Utils.send(sender, "&fEl nombre de &b" + targetPlayer.getName() + " &fha sido cambiado a " + Utils.ct(shortString));
                Utils.send(targetPlayer, "&fTu nombre ha sido cambiado a " + Utils.ct(shortString) + " &fpor &b" + sender.getName());
            } else {
                Utils.send(sender, "&cJugador no encontrado");
            }
        } else {
            Utils.send(sender, "&cEl nombre debe tener más de 16 caracteres");
        }
    }

}
