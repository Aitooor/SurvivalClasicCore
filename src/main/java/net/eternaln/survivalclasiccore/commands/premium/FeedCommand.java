package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("feed|comida|food|alimento")
public class FeedCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.feed")
    public void heal(Player sender) {
        sender.setFoodLevel(20);
        Utils.send(sender, "&aTu comida ha sido restaurada");
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.feed.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setFoodLevel(20);
        Utils.send(targetPlayer, "&aTu comida ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasiccore.feed.set")
    @CommandCompletion("@players")
    public void set(String target, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setFoodLevel(amount);
        Utils.send(targetPlayer, "&aTu comida ha sido establecida a &e" + amount);
    }

}
