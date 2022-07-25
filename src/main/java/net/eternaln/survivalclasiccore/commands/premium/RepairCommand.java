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

    private final Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getRepairCooldown());

    @Default
    public void repair(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }

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

}
