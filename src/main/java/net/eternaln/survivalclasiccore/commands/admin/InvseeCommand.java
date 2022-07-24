package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("invsee|inv|inventario|verinventario|verinv")
@CommandPermission("survivalclasic.invsee")
public class InvseeCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandCompletion("@players")
    public void invSee(Player sender, Player target) {
        if(target.equals(sender)) {
            sender.openInventory(sender.getInventory());
            return;
        }

        sender.openInventory(target.getInventory());
    }

}
