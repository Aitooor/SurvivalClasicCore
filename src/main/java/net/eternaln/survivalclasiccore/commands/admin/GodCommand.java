package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
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

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasiccore.god")
    public void god(Player sender) {
        if (gods.contains(sender.getName())) {
            gods.remove(sender.getName());
            sender.setInvulnerable(false);
            Utils.send(sender,"&aYou are no longer immortal!");
        } else {
            gods.add(sender.getName());
            sender.setInvulnerable(true);
            Utils.send(sender,"&aYou are now immortal!");
        }
    }

    @Subcommand("otros|others|other|otro")
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
