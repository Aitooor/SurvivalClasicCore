package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@CommandAlias("god|dios|inmortal")
public class GodCommand extends BaseCommand {

    private ArrayList<String> gods = new ArrayList<String>();

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.god")
    public void god(CommandSender sender) {
        Player player = (Player) sender;
        if (gods.contains(sender.getName())) {
            gods.remove(sender.getName());
            player.setInvulnerable(false);
            Utils.send(sender,"&aYou are no longer immortal!");
        } else {
            gods.add(sender.getName());
            player.setInvulnerable(true);
            Utils.send(sender,"&aYou are now immortal!");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.god.other")
    @CommandCompletion("@players")
    public void other(CommandSender sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if(!(targetPlayer == null)) {
            if (gods.contains(targetPlayer.getName())) {
                gods.remove(targetPlayer.getName());
                targetPlayer.setInvulnerable(false);
                Utils.send(sender, "&aYou are no longer immortal!");
                Utils.send(targetPlayer, sender.getName() + " &fte ha &aactivado &fel fly");
            } else {
                gods.add(targetPlayer.getName());
                targetPlayer.setInvulnerable(true);
                Utils.send(sender, "&aYou are now immortal!");
                Utils.send(targetPlayer, sender.getName() + " &fte ha &aactivado &fel fly");
            }
        } else {
            Utils.send(sender, "&cJugador no encontrado");
        }
    }
}
