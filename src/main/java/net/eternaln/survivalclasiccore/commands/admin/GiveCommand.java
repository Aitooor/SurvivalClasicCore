package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

@CommandAlias("give|dar")
public class GiveCommand extends BaseCommand {

    @Default
    @CatchUnknown
    public void give(Player sender, String item, int amount) {
        sender.getInventory().addItem(new ItemStack(Material.getMaterial(item), amount));
    }

    @Subcommand("other|otro|otros|others")
    @CommandPermission("survivalclasiccore.give.other")
    @CommandCompletion("@players")
    public void giveOther(Player sender, String target, String item, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        String itemName = item.toUpperCase();
        if(!(targetPlayer == null)) {
            targetPlayer.getInventory().addItem(new ItemStack(Material.getMaterial(itemName), amount));
            Utils.send(sender, "&fHas dado &b" + amount + " &f" + itemName + " a &b" + targetPlayer.getName());
            Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &f" + itemName + " de &b" + sender.getName());
            targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
        } else {
            Utils.send(sender, "&cJugador no encontrado");
        }
    }
}
