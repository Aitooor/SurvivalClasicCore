package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("enchanttable|tablaenchante|tablaenchant|mesaencantar")
public class EnchantTableCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.enchanttable")
    public void god(Player sender) {
        sender.openEnchanting(null, true);
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.enchanttable.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.openEnchanting(null, true);
    }

}
