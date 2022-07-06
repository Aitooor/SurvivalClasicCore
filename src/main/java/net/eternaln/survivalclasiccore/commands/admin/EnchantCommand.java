package net.eternaln.survivalclasiccore.commands.admin;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.ItemMeta;

@CommandAlias("enchant|encantamiento|enchants|enchantments|encantamientos")
@CommandPermission("survivalclasiccore.enchant")
public class EnchantCommand extends BaseCommand {

    @Subcommand("add")
    @CommandPermission("survivalclasiccore.enchant.add")
    public void add(Player sender, String enchant, int level) {
        if (!(sender.getItemOnCursor() == null)){
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchant));
            if (enchantment == null){
                Utils.send(sender, "&cEl encantamiento no existe");
            } else {
                ItemMeta itemMeta = sender.getItemOnCursor().getItemMeta();
                itemMeta.addEnchant(enchantment, level, true);
                sender.getItemOnCursor().setItemMeta(itemMeta);
                Utils.send(sender, "&fHas añadido el encantamiento &b" + enchant + " &fcon nivel &b" + level);
            }
        } else {
            Utils.send(sender, "&cNo puedes usar este comando si no tienes nada en tu mano");
        }
    }

    @Subcommand("remove")
    @CommandPermission("survivalclasiccore.enchant.remove")
    public void remove(Player sender, String enchant) {
        if (!(sender.getItemOnCursor() == null)){
            Utils.send(sender, "&cNo puedes usar este comando si no tienes nada en tu mano");
        } else {
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchant));
            if (enchantment == null){
                Utils.send(sender, "&cEl encantamiento no existe");
            } else {
                ItemMeta itemMeta = sender.getItemOnCursor().getItemMeta();
                itemMeta.removeEnchant(enchantment);
                sender.getItemOnCursor().setItemMeta(itemMeta);
                Utils.send(sender, "&fHas eliminado el encantamiento &b" + enchant);
            }
        }
    }

}