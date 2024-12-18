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

    private final MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getRepairCooldown(), TimeUnit.MINUTES);

    @Default
    public void repair(Player sender) {
        ItemStack item = sender.getInventory().getItemInMainHand();
        if (item == null || item.getType() == Material.AIR) {
            Utils.send(sender, "&cNo tienes nada en la mano");
            return;
        }
        Material material = sender.getInventory().getItemInMainHand().getType();
        ItemMeta itemMeta = item.getItemMeta();
        Damageable damageable = (Damageable) itemMeta;

        if(material.isBlock() && material.getMaxDurability() < 1) {
            Utils.send(sender, "&cNo se puede reparar");
        } else {
            if (itemMeta instanceof Damageable) {
                if (damageable.hasDamage()) {
                    if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass.repair")) {
                        String timeRemaining = cooldown.getFormattedRemainingString(sender.getUniqueId());
                        Utils.send(sender, messageFile.cooldown.replace("%time%", timeRemaining));
                        return;
                    }
                    cooldown.addToCooldown(sender.getUniqueId());

                    damageable.setDamage(0);
                    item.setItemMeta(damageable);
                    Utils.send(sender, "&aReparado");
                } else {
                    Utils.send(sender, "&cYa esta reparado");
                }
            } else {
                Utils.send(sender, "&cNo se puede reparar");
            }
        }
    }


}
