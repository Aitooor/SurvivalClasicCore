package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("fly|volar|vuelo")
@CommandPermission("survivalclasic.fly")
public class FlyCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void fly(Player sender) {
        Player player = sender;
        if (!sender.getAllowFlight()) {
            sender.setAllowFlight(true);

            Utils.send(sender, "&aHas activado el fly");
        } else {
            sender.setAllowFlight(false);

            Utils.send(sender, "&cHas desactivado el fly");
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.fly.other")
    @CommandCompletion("@players")
    public void other(CommandSender sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if (!(targetPlayer == null)) {
            if (!targetPlayer.getAllowFlight()) {
                targetPlayer.setAllowFlight(true);

                Utils.send(sender, "&aHas activado el fly de &b" + targetPlayer.getName());
                Utils.send(targetPlayer, sender.getName() + " &fte ha &aactivado &fel fly");
            } else {
                targetPlayer.setAllowFlight(false);

                Utils.send(sender, "&cHas desactivado el fly de &b" + targetPlayer.getName());
                Utils.send(targetPlayer, sender.getName() + " &fte ha &cdesactivado &fel fly");
            }
        } else {
            Utils.send(sender, "&cJugador no encontrado");
        }
    }
}
