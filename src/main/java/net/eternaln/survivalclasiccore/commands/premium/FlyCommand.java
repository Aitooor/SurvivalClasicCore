package net.eternaln.survivalclasiccore.commands.premium;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import net.eternaln.survivalclasiccore.SurvivalClasicCore;
import net.eternaln.survivalclasiccore.data.configuration.Configuration;
import net.eternaln.survivalclasiccore.data.configuration.MessagesFile;
import net.eternaln.survivalclasiccore.managers.CooldownManager;
import net.eternaln.survivalclasiccore.objects.staff.Staff;
import net.eternaln.survivalclasiccore.utils.CooldownOld;
import net.eternaln.survivalclasiccore.utils.Utils;
import org.bukkit.entity.Player;

import java.util.concurrent.TimeUnit;

@CommandAlias("fly|volar|vuelo")
@CommandPermission("survivalclasic.fly")
public class FlyCommand extends BaseCommand {

    private Configuration config = SurvivalClasicCore.getInstance().getConfiguration();
    private MessagesFile messageFile = SurvivalClasicCore.getMessagesFile();
    private CooldownManager cooldowns = SurvivalClasicCore.getInstance().getCooldowns();
    private int cooldownConfig = config.cmdCooldown;

    @Default
    public void fly(Player sender) {
        Staff staff = Staff.getStaff(sender.getUniqueId());
        if (cooldowns.getCooldown(sender.getUniqueId()) == null) {
            if (!staff.isFlying()) {
                staff.enableFly(true);
            } else {
                staff.disableFly(true);
            }
            cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
            return;
        }
        CooldownOld cooldownOld = cooldowns.getOrCreate(sender.getUniqueId(), TimeUnit.SECONDS.toMillis(cooldownConfig));
        if (!cooldownOld.hasExpired()) {
            Utils.send(sender, messageFile.cooldown.replace("%time%", String.valueOf(TimeUnit.MILLISECONDS.toSeconds(cooldownOld.getRemaining()))));
            return;
        }
        cooldownOld.stop();
        cooldowns.create(sender.getUniqueId(), new CooldownOld(TimeUnit.SECONDS.toMillis(cooldownConfig)));
        if (!staff.isFlying()) {
            staff.enableFly(true);
        } else {
            staff.disableFly(true);
        }
    }

    @Subcommand("otros|others|other|otro")
    @CommandPermission("survivalclasic.fly.other")
    @CommandCompletion("@players")
    public void other(Player sender, Player target) {
        Staff staff = Staff.getStaff(target.getUniqueId());
        if (!staff.isFlying()) {
            staff.enableFly(true);
            Utils.send(sender, "&aHas activado el vuelo de &b" + target.getName());
        } else {
            staff.disableFly(true);
            Utils.send(sender, "&aHas desactivado el vuelo de &b" + target.getName());
        }
    }
}
