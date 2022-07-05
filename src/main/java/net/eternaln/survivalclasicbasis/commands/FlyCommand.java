package net.eternaln.survivalclasicbasis.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasicbasis.SurvivalClasicBasis;
import net.eternaln.survivalclasicbasis.utils.Utils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("fly|volar|vuelo")
public class FlyCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasicbasis.fly")
    public void help(CommandSender sender) {
        Player player = (Player) sender;
        if (!player.getAllowFlight()){
            player.setAllowFlight(true);

            Utils.send(sender, "&aHas activado el fly");
        } else {
            player.setAllowFlight(false);

            Utils.send(sender, "&cHas desactivado el fly");
        }
    }

    @Subcommand("other")
    @CommandPermission("survivalclasicbasis.fly.others")
    public void help(CommandSender sender, Player target) {
        if (!target.getAllowFlight()) {
            target.setAllowFlight(true);

            Utils.send(sender, "&aHas activado el fly de &b" + target.getName());
            Utils.send(target, "&aHas sido activado el fly por &b" + sender.getName());
        } else {
            target.setAllowFlight(false);

            Utils.send(sender, "&cHas desactivado el fly de &b" + target.getName());
            Utils.send(target, "&cHas sido desactivado el fly por &b" + sender.getName());
        }
    }
}
