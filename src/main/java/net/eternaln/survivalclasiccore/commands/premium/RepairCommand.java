package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.CommandHelp;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

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
        ItemStack item = sender.getInventory().getItemInMainHand();
        if (item.getItemMeta() instanceof Damageable d) {
            d.setDamage(0);
            Utils.send(sender, "&aReparado correctamente.");
        } else
            Utils.send(sender, "&cEste item no se puede reparar.");
    }

    @Subcommand("todo|all|todos|all")
    @CommandPermission("survivalclasic.repair.all")
    public void all(Player sender) {
        if (!sender.hasPermission("survivalclasic.cooldown.bypass") && !cooldown.isCooledDown(sender.getUniqueId())) {
            long cooldownTime = cooldown.getSecondsRemaining(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", String.valueOf(cooldownTime)));
            return;
        }
        for (ItemStack items : sender.getInventory().getContents()) {
            if (items instanceof Damageable d)
                d.setDamage(0);
        }
        for (ItemStack items : sender.getInventory().getArmorContents()) {
            if (items instanceof Damageable d)
                d.setDamage(0);
        }
        Utils.send(sender, "&aReparado todo correctamente");
    }
}
