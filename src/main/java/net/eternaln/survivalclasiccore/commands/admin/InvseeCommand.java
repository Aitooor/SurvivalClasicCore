package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("invsee|inv|inventario|verinventario|verinv")
public class InvseeCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("help|ayuda")
    public void help(CommandHelp help) {
        help.showHelp();
    }
    @Default
    @CommandPermission("survivalclasiccore.invsee")
    public void god(Player sender) {
        sender.openInventory(sender.getInventory());
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.invsee.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        sender.openInventory(targetPlayer.getInventory());
    }

}
