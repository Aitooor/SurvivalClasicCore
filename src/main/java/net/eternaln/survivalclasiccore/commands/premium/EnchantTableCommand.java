package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("enchanttable|tablaenchante|tablaenchant|mesaencantar")
public class EnchantTableCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("help|ayuda")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasiccore.enchanttable")
    public void god(Player sender) {
        sender.openEnchanting(null, true);
        Utils.send(sender, "&aAbriendo tu mesa de encantamiento");
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.enchanttable.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.openEnchanting(null, true);
        Utils.send(sender, "&fAbriendo la mesa de encantamiento de &b" + targetPlayer.getName());
        Utils.send(targetPlayer, sender.getDisplayName() + " &fha abierto tu mesa de encantamiento");
    }

}
