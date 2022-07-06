package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("fly|volar|vuelo")
public class FlyCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.fly")
    public void fly(CommandSender sender) {
        Player player = (Player) sender;
        if (!player.getAllowFlight()){
            player.setAllowFlight(true);

            Utils.send(sender, "&aHas activado el fly");
        } else {
            player.setAllowFlight(false);

            Utils.send(sender, "&cHas desactivado el fly");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.fly.other")
    @CommandCompletion("@players")
    public void other(CommandSender sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        if(!(targetPlayer == null)) {
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