package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.PlayerUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("invsee|inv|inventario|verinventario|verinv")
@CommandPermission("survivalclasic.invsee")
public class InvseeCommand extends BaseCommand {

    @CatchUnknown
    public void help(CommandHelp help) { help.showHelp(); }

    @Default
    @CommandCompletion("@players")
    public void invSee(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        sender.getPlayer().openInventory(PlayerUtil.customPlayerInventory(targetPlayer));
    }

    @Subcommand("realtime|rtime|tiemporeal")
    @CommandCompletion("@players")
    public void realtime(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);

        sender.openInventory(targetPlayer.getInventory());

    }

}
