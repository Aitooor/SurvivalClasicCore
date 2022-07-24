package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

@CommandAlias("hat|cabeza|gorro")
@CommandPermission("survivalclasic.hat")
public class HatCommand extends BaseCommand {

    @CatchUnknown
    @HelpCommand("ayuda|help")
    public void help(CommandHelp help) {
        help.showHelp();
    }

    @Default
    public void god(Player sender) {
        ItemStack x = sender.getInventory().getItemInMainHand();
        ItemStack y = sender.getInventory().getHelmet();

        if (sender.getInventory().getHelmet() != null) {
            Utils.send(sender, "&cYa tienes una casco");
            return;
        }

        if (Objects.equals(String.valueOf(x), "ItemStack{AIR x 0}")) {
            Utils.send(sender, "&cNo tienes ningun item en tu inventario");
            return;
        }
        sender.getInventory().setItem(sender.getInventory().getHeldItemSlot(), y);
        sender.getInventory().remove(x);
        sender.getInventory().setHelmet(x);
        Utils.send(sender, "&aHas cambiado tu casco");
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.hat.other")
    @CommandCompletion("@players")
    public void other(Player sender, String target) {
        Player targetPlayer = Bukkit.getPlayer(target);
        ItemStack x = targetPlayer.getInventory().getItemInMainHand();
        ItemStack y = targetPlayer.getInventory().getHelmet();

        if (targetPlayer.getInventory().getHelmet() != null) {
            Utils.send(sender, "&c" + targetPlayer.getName() + " ya tiene una casco");
            return;
        }

        if (Objects.equals(String.valueOf(x), "ItemStack{AIR x 0}")) {
            Utils.send(sender, "&c" + targetPlayer.getName() + " no tiene ningun item en su inventario");
            return;
        }
        targetPlayer.getInventory().setItem(targetPlayer.getInventory().getHeldItemSlot(), y);
        targetPlayer.getInventory().remove(x);
        targetPlayer.getInventory().setHelmet(x);
        Utils.send(sender, "&aHas cambiado el casco de " + targetPlayer.getName());
    }


}
