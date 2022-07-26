package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("reparar|repair|fix")
@CommandPermission("survivalclasic.repair")
public class RepairCommand extends BaseCommand {

    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getRepairCooldown(), TimeUnit.SECONDS);

    @Default
    public void repair(Player sender) {
        ItemStack item = sender.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            Utils.send(sender, "&cNo tienes nada en la mano");
            return;
        }
        Material material = sender.getInventory().getItemInMainHand().getType();
        ItemMeta itemMeta = item.getItemMeta();
        if(material.isBlock()) {
            Utils.send(sender, "&cNo se puede reparar");
            return;
        } else {
            if (itemMeta instanceof Damageable) {
                if (material.getMaxDurability() >= 1) {
                    Utils.send(sender, "&cYa esta reparado");
                    return;
                } else {
                    if (!cooldown.isCooldownOverRepair(sender.getUniqueId())) {
                        String cooldownTime = cooldown.getSecondsRemainingStringRepair(sender.getUniqueId());
                        Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                        return;
                    }
                    cooldown.addToCooldownRepair(sender.getUniqueId());

                    Damageable damageable = (Damageable) itemMeta;
                    damageable.setDamage(0);
                    item.setItemMeta(damageable);
                    Utils.send(sender, "&aReparado");
                    return;
                }
            } else {
                Utils.send(sender, "&cNo se puede reparar");
                return;
            }
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.repair.other")
    public void other(Player sender, Player target) {
        ItemStack item = target.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            Utils.send(sender, "&cNo tienes nada en la mano");
            return;
        }
        Material material = target.getInventory().getItemInMainHand().getType();
        ItemMeta itemMeta = item.getItemMeta();
        if(material.isBlock()) {
            Utils.send(sender, "&cNo se puede reparar");
            return;
        } else {
            if (itemMeta instanceof Damageable) {
                if (material.getMaxDurability() >= 1) {
                    Utils.send(sender, "&cYa esta reparado");
                    return;
                } else {
                    if (!cooldown.isCooldownOver(sender.getUniqueId())) {
                        String cooldownTime = cooldown.getSecondsRemainingString(sender.getUniqueId());
                        Utils.send(sender, messageFile.cooldown.replace("%time%", cooldownTime));
                        return;
                    }
                    cooldown.addToCooldown(sender.getUniqueId());

                    Damageable damageable = (Damageable) itemMeta;
                    damageable.setDamage(0);
                    item.setItemMeta(damageable);
                    Utils.send(sender, "&fReparado &b" + target.getName());
                    Utils.send(target, sender.getDisplayName() + " &fha reparado tu herramienta");
                    return;
                }
            } else {
                Utils.send(sender, "&cNo se puede reparar");
                return;
            }
        }
    }

}
