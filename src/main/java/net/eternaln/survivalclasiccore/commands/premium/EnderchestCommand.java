package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("enderchest|ender|cofreender")
public class EnderchestCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.enderchest")
    public void god(Player sender) {
        sender.openInventory(sender.getEnderChest());
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.enderchest.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        sender.openInventory(targetPlayer.getEnderChest());
    }

}
