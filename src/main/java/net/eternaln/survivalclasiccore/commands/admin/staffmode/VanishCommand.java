package net.eternaln.survivalclasiccore.commands.admin.staffmode;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.Cooldown;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@CommandAlias("vanish|v|invisible")
@CommandPermission("survivalclasic.vanish")
public class VanishCommand extends BaseCommand {
    
    private Cooldown<UUID> cooldown = new Cooldown<>(SurvivalClasicCore.getConfiguration().getCmdCooldown(), TimeUnit.SECONDS);

    @Default
    public void vanish(Player sender) {
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        
        Staff staff = Staff.getStaff(sender.getUniqueId());
        if(!staff.isStaffMode()) {
            if (staff.isVanished()) {
                staff.disableVanish(true);
            } else {
                staff.enableVanish(true);
            }
        } else {
            Utils.send(sender, "&cNo puedes hacer eso mientras estás en StaffMode");
        }
    }

    @Subcommand("other|others|otros|otro")
    @CommandPermission("survivalclasic.vanish.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        if (!cooldown.isCooldownOver(sender.getUniqueId()) && !sender.hasPermission("survivalclasic.cooldown.bypass")) {
            String cooldownTime = cooldown.getFormattedRemainingString(sender.getUniqueId());
            Utils.send(sender, SurvivalClasicCore.getMessagesFile().cooldown.replace("%time%", cooldownTime));
            return;
        }
        cooldown.addToCooldown(sender.getUniqueId());
        
        Staff staff = Staff.getStaff(target.getUniqueId());
        if(!staff.isStaffMode()) {
            if (staff.isVanished()) {
                staff.disableVanish(true);
                Utils.send(sender, "&aHas desvinculado a &b" + target.getName() + " &ade la lista de invisibles");
            } else {
                staff.enableVanish(true);
                Utils.send(sender, "&aHas vinculado a &b" + target.getName() + " &aen la lista de invisibles");
            }
        } else {
            Utils.send(sender, "&cNo puedes hacer eso mientras " + target.getName() + " está en StaffMode");
        }
    }

}
