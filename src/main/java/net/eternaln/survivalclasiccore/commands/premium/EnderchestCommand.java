package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("enderchest|ender|cofreender")
public class EnderchestCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandPermission("survivalclasiccore.enderchest")
    public void god(Player sender) {
        sender.openInventory(sender.getEnderChest());
        Utils.send(sender, "&aAbriendo tu cofre");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasiccore.enderchest.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        sender.openInventory(targetPlayer.getEnderChest());
        Utils.send(sender, "&fAbriendo el cofre ender de &b" + targetPlayer.getName());
        Utils.send(targetPlayer, sender.getDisplayName() + " &fha abierto tu cofre ender");
    }

}
