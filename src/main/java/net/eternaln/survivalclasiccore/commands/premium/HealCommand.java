package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

@CommandAlias("heal|curar|health|salud")
public class HealCommand extends BaseCommand {

    @Default
    @CatchUnknown
    @CommandPermission("survivalclasiccore.heal")
    public void heal(Player sender) {
        sender.setHealth(20);
        Utils.send(sender, "&aTu salud ha sido restaurada");
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasiccore.heal.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setHealth(20);
        Utils.send(targetPlayer, "&aTu salud ha sido restaurada por &b" + sender.getName());
    }

    @Subcommand("set|establecer")
    @CommandPermission("survivalclasiccore.heal.set")
    @CommandCompletion("@players")
    public void set(String target, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        targetPlayer.setHealth(amount);
        Utils.send(targetPlayer, "&aTu salud ha sido establecida a &e" + amount);
    }

}
