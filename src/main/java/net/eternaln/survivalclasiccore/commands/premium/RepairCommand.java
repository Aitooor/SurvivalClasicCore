package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.concurrent.TimeUnit;

@CommandAlias("reparar|repair|fix")
@CommandPermission("survivalclasic.repair")
public class RepairCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.repairCooldown;

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
                    if (cooldowns.getCooldownRepair(sender.getUniqueId()) == null) {
                        Damageable damageable = (Damageable) itemMeta;
                        damageable.setDamage(0);
                        item.setItemMeta(damageable);
                        Utils.send(sender, "&aReparado");
                        cooldowns.createRepair(sender.getUniqueId(), new Cooldown(TimeUnit.MINUTES.toMillis(cooldownConfig)));
                        return;
                    }
                    Cooldown cooldown = cooldowns.getOrCreateRepair(sender.getUniqueId(), TimeUnit.MINUTES.toMillis(cooldownConfig));
                    if (!cooldown.hasExpired()) {
                        Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toMinutes(cooldown.getRemaining()))));
                        return;
                    }
                    cooldown.stop();
                    cooldowns.createRepair(sender.getUniqueId(), new Cooldown(TimeUnit.MINUTES.toMillis(cooldownConfig)));
                    return;
                }
            } else {
                Utils.send(sender, "&cNo se puede reparar");
                return;
            }
        }
    }

}
