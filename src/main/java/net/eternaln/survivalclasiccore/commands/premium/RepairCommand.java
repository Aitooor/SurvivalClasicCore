package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

//TODO: Fix repair command
@CommandAlias("reparar|repair|fix")
@CommandPermission("survivalclasic.repair")
public class RepairCommand extends BaseCommand {

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown());

    @Default
    public void repair(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        ItemStack handItem = sender.getInventory().getItemInMainHand();
        ItemMeta meta = handItem.getItemMeta();
        if (handItem.getType() != Material.AIR && !handItem.getType().isBlock() && meta instanceof Damageable) {
            Damageable damageable = (Damageable)meta;
            String itemName = handItem.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
            this.repairItem(sender, handItem, damageable);
            sender.updateInventory();
            Utils.send(sender, "&fEl item &a" + itemName + " &fha sido reparado.");
            return;
        } else {
            Utils.send(sender, "&fEl item que tienes en la mano no es reparable.");
            return;
        }
    }

    @Subcommand("mano|hand")
    public void repairHand(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        ItemStack handItem = sender.getInventory().getItemInMainHand();
        ItemMeta meta = handItem.getItemMeta();
        if (handItem.getType() != Material.AIR && !handItem.getType().isBlock() && meta instanceof Damageable) {
            Damageable damageable = (Damageable)meta;
            String itemName = handItem.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
            this.repairItem(sender, handItem, damageable);
            sender.updateInventory();
            Utils.send(sender, "&fEl item &a" + itemName + " &fha sido reparado.");
            return;
        } else {
            Utils.send(sender, "&fEl item que tienes en la mano no es reparable.");
            return;
        }
    }

    @Subcommand("todo|all")
    @CommandPermission("survivalclasic.repair.all")
    public void repairAll(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        List<String> repaired = new ArrayList();
        this.repairItems(sender, sender.getInventory().getContents(), repaired);
        if (sender.hasPermission("survivalclasic.repair.armor")) {
            this.repairItems(sender, sender.getInventory().getArmorContents(), repaired);
        }

        if (repaired.isEmpty()) {
            Utils.send(sender, "&cNo se encontraron items para reparar");
            return;
        } else {
            sender.updateInventory();
            Utils.send(sender, "&aReparaste los siguientes items: " + String.join(", ", repaired));
            return;
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasic.repair.other")
    public void repairOther(Player sender, Player target) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        List<String> repaired = new ArrayList();
        this.repairItems(sender, target.getInventory().getContents(), repaired);
        if (sender.hasPermission("survivalclasic.repair.armor")) {
            this.repairItems(sender, target.getInventory().getArmorContents(), repaired);
        }

        if (repaired.isEmpty()) {
            Utils.send(sender, "&cNo se encontraron items para reparar");
            return;
        } else {
            target.updateInventory();
            Utils.send(sender, "&aReparaste los siguientes items: " + String.join(", ", repaired));
            Utils.send(target, "&fEl jugador &a" + sender.getName() + " &fte ha reparado tus items.");
            return;
        }
    }

    @Subcommand("othershand|otherhand|manootros|manootro")
    @CommandPermission("survivalclasic.repair.other")
    public void repairOtherHand(Player sender, Player target) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

        ItemStack handItem = target.getInventory().getItemInMainHand();
        ItemMeta meta = handItem.getItemMeta();
        if (handItem.getType() != Material.AIR && !handItem.getType().isBlock() && meta instanceof Damageable) {
            Damageable damageable = (Damageable)meta;
            String itemName = handItem.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
            this.repairItem(sender, handItem, damageable);
            target.updateInventory();
            Utils.send(sender, "&fEl item &a" + itemName + " &fha sido reparado.");
            Utils.send(target, "&fEl jugador &a" + sender.getName() + " &fte ha reparado tu item.");
            return;
        } else {
            Utils.send(sender, "&fEl item que tienes en la mano no es reparable.");
            return;
        }
    }

    private void repairItems(CommandSender sender, ItemStack[] items, List<String> repaired) {
        ItemStack[] var4 = items;
        int var5 = items.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            ItemStack item = var4[var6];
            ItemMeta meta = item.getItemMeta();
            if (item.getType() != Material.AIR && !item.getType().isBlock() && meta instanceof Damageable) {
                Damageable damageable = (Damageable)meta;
                String itemName = item.getType().toString().toLowerCase(Locale.ENGLISH).replace('_', ' ');
                if (this.repairItem(sender, item, damageable, true)) {
                    repaired.add(itemName);
                }
            }
        }

    }

    private boolean repairItem(CommandSender sender, ItemStack handItem, Damageable damageable) {
        return this.repairItem(sender, handItem, damageable, false);
    }

    private boolean repairItem(CommandSender sender, ItemStack handItem, Damageable damageable, boolean silent) {
        Material material = handItem.getType();
        if (!material.isBlock() && material.getMaxDurability() >= 1) {
            if (!damageable.hasDamage()) {
                if (!silent) {
                    Utils.send(sender, "&cYa esta arreglado");
                }

                return false;
            } else {
                damageable.setDamage(0);
                handItem.setItemMeta((ItemMeta)damageable);
                return true;
            }
        } else {
            if (!silent) {
                Utils.send(sender, "&cEste item no se puede reparar.");
            }

            return false;
        }
    }

}
