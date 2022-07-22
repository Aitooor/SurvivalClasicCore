package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@CommandAlias("give|dar")
@CommandPermission("survivalclasiccore.give")
public class GiveCommand extends BaseCommand {

    MessagesFile messagesFile = SurvivalClasicCore.getMessagesFile();

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    @CommandCompletion("@items @range:1-2304")
    public void give(Player sender, String item, int amount) {
        if(amount > 0) {
            Utils.send(sender, "&fHas recibido &b" + amount + " &fde &a" + item);
            sender.getInventory().addItem(new ItemStack(Material.getMaterial(item.toUpperCase()), amount));
        } else {
            Utils.send(sender, "&cIngresa un numero del 1-2304");
        }
    }

    @Subcommand("otros|otro|other|others")
    @CommandPermission("survivalclasiccore.give.other")
    @CommandCompletion("@players @items @range:1-2304")
    public void giveOther(Player sender, String target, String item, int amount) {
        Player targetPlayer = Bukkit.getPlayer(target);
        String itemName = item.toUpperCase();

        if (!(targetPlayer == null)) {
            if(amount > 0) {
                targetPlayer.getInventory().addItem(new ItemStack(Material.getMaterial(itemName), amount));
                Utils.send(sender, "&fHas dado &b" + amount + " &a" + itemName + " &fa &b" + targetPlayer.getName());
                Utils.send(targetPlayer, "&fHas recibido &b" + amount + " &a" + itemName + " &fde &b" + sender.getName());
                targetPlayer.playSound(targetPlayer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1, 1);
            } else {
                Utils.send(sender, "&cIngresa un numero del 1-2304");
            }
        } else {
            Utils.send(sender, "&cJugador no encontrado");
        }
    }
}
